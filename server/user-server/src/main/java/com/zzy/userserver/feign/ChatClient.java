package com.zzy.userserver.feign;

import com.zzy.common.entity.chat.UserChat;
import com.zzy.common.entity.common.ZZYResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zzy
 * @desc
 */
@FeignClient("chat-server")
public interface ChatClient {
    @GetMapping("/getChat/{friendId}")
    ZZYResult<List<UserChat>> getChatByUser(@PathVariable("friendId") Long friendId , HttpServletRequest request);
}
