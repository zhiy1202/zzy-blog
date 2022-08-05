package com.zzy.chat.feign;

import com.zzy.common.entity.common.ZZYParam;
import com.zzy.common.entity.common.ZZYResult;
import com.zzy.common.entity.user.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author zzy
 * @desc
 */
@Service
@FeignClient("user-server")
public interface UserClient {
    @GetMapping("/user/info/{userId}")
    ZZYResult<User> info(@PathVariable("userId") Long userId);
    @GetMapping("/user/getFriendNav/{userId}")
    ZZYResult<List<User>> getFriendNav(@PathVariable("userId") Long userId);
}
