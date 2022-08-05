package com.zzy.chat.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzy.chat.feign.UserClient;
import com.zzy.chat.service.IUserChatService;
import com.zzy.common.entity.chat.UserChat;
import com.zzy.common.entity.common.ZZYParam;
import com.zzy.common.entity.common.ZZYResult;
import com.zzy.common.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzy
 * @since 2022-07-09
 */
@RestController
@RequestMapping("/chat")
public class UserChatController {
    @Autowired
    private IUserChatService userChatService;
    @Autowired
    private UserClient userClient;
    //获取两个用户之间的聊天记录
    @GetMapping("/getChat/{friendId}")
    public ZZYResult<List<UserChat>> getChatByUser(@PathVariable("friendId") Long friendId , HttpServletRequest request) {
        Long userId = (Long) request.getSession(false).getAttribute("userId");
        QueryWrapper<UserChat> wrapper = new QueryWrapper<>();
        wrapper.eq("self_id",userId)
                .eq("friend_id",friendId)
                .or()
                .eq("self_id",friendId)
                .eq("friend_id",userId);
        //获取好友信息
        User friend = userClient.info(userId).getData();
        List<UserChat> list = userChatService.list(wrapper);
        return ZZYResult.getInstance(200,"聊天记录查询成功",list);
    }
    //获取两个用户之间的最后一条消息
    @GetMapping("/lastMes/{friendId}")
    public ZZYResult<ZZYParam> lastMes(@PathVariable("friendId") Long friendId,HttpServletRequest request){
        Long userId = (Long) request.getSession(false).getAttribute("userId");
        User friend = userClient.info(friendId).getData();
        QueryWrapper<UserChat> wrapper = new QueryWrapper<>();
        wrapper.eq("self_id",userId)
                .eq("friend_id",friendId)
                .or()
                .eq("friend_id",userId)
                .eq("self_id",friendId)
                .orderByDesc("create_time")
                .last("limit 1");
        List<UserChat> message = userChatService.list(wrapper);
        ZZYParam param = new ZZYParam();
        param.setP1(friend);
        if (message.size() != 0){
            param.setP2(message.get(0));
            return ZZYResult.getInstance(200,"好友最近消息",param);
        }
        UserChat userChat = new UserChat();
        userChat.setSelfId(userId);
        userChat.setFriendId(friendId);
        userChat.setSendId(userId);
        userChat.setContent("我们已经是好友了,快来聊天吧");
        userChat.setCreateTime(LocalDateTime.now());
        param.setP2(userChat);
        return ZZYResult.getInstance(200,"好友最近消息",param);

    }
    //获取所有好友
    @GetMapping("/allFriend")
    public ZZYResult<List<ZZYParam>> allFriend(HttpServletRequest request){
        List<ZZYParam> params = new ArrayList<>();
        Long userId = (Long) request.getSession(false).getAttribute("userId");
        List<User> friendList = userClient.getFriendNav(userId).getData();
        for (User friend : friendList) {
            ZZYParam param = lastMes(friend.getUserId(), request).getData();
            params.add(param);
        }
        return ZZYResult.getInstance(200,"聊天目录查询成功",params);
    }

}
