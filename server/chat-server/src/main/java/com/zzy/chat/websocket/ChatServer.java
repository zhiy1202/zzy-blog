package com.zzy.chat.websocket;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.zzy.chat.config.SessionManage;
import com.zzy.chat.feign.UserClient;
import com.zzy.chat.service.IUserChatService;
import com.zzy.common.entity.chat.ChatMessage;
import com.zzy.common.entity.chat.UserChat;
import com.zzy.common.entity.common.ZZYParam;
import com.zzy.common.entity.common.ZZYResult;
import com.zzy.common.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.net.URI;
import java.time.LocalDateTime;


/**
 * @author zzy
 * @desc 聊天室
 */
@Component
public class ChatServer extends TextWebSocketHandler {
    @Autowired
    private IUserChatService userChatService;
    @Autowired
    private UserClient userClient;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("连接开始");
        URI uri = session.getUri();
        String query = uri.getQuery();
        String[] split = query.split("=");
        String userId = split[split.length-1];

        if (SessionManage.getSession(userId) == null){
            SessionManage.setSession(userId,session);
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String s = message.getPayload();
        System.out.println(s);
        ChatMessage chatMessage = JSONUtil.toBean(s, ChatMessage.class);
        System.out.println(chatMessage);
        //1.需要得到 发送者信息 2.发送的内容 3.时间 4.唯一id
        /**
         * 1. uuid
         * 2. userId 的个人信息
         * 3. friendId
         * 4. 消息
         * 5. 时间
         */
        ZZYParam param = new ZZYParam();
        String uuid = IdUtil.fastSimpleUUID();
        User user = userClient.info(chatMessage.getUserId()).getData();
        User friend = userClient.info(chatMessage.getFriendId()).getData();
        LocalDateTime localDateTime = LocalDateTime.now();
        param.setP1(uuid);
        param.setP2(user);
        param.setP3(friend);
        param.setP4(chatMessage.getMessage());
        param.setP5(localDateTime);
        String jsonStr = JSONUtil.toJsonStr(param);
        System.out.println(jsonStr);
        WebSocketSession session1 = SessionManage.getSession(String.valueOf(chatMessage.getFriendId()));
        if (session1 != null) {
            //正常发送
            TextMessage textMessage = new TextMessage(jsonStr);
            session1.sendMessage(textMessage);
        }
        //用户不在线 存入数据库
        userChatService.save(new UserChat(chatMessage.getUserId(), chatMessage.getFriendId(), chatMessage.getUserId(), chatMessage.getMessage()));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        URI uri = session.getUri();
        String query = uri.getQuery();
        String[] split = query.split("=");
        String userId = split[split.length-1];

        SessionManage.removeSession(userId);
    }
}
