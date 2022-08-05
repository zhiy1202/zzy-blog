package com.zzy.resource.feign;

import com.zzy.common.entity.common.ZZYResult;
import com.zzy.common.entity.user.User;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * @author zzy
 * @desc openfeign 调用
 */
@Service
@FeignClient("user-server")
public interface UserClient {
    @GetMapping("/user/info/{userId}")
    ZZYResult<User> info(@PathVariable("userId") Long userId);
    @PutMapping("/user/changeHead")
    ZZYResult<Boolean> changeHead(@RequestParam("userId") Long userId, @RequestParam("headPath")String headPath);
}
