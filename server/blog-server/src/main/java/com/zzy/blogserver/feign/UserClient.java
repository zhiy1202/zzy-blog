package com.zzy.blogserver.feign;

import com.zzy.common.entity.common.ZZYResult;
import com.zzy.common.entity.user.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zzy
 * @desc user-server 服务远程调用
 */
@Service
@FeignClient(value = "user-server")
public interface UserClient {
    @PutMapping("/changeBlogCount")
    ZZYResult<Boolean> changeBlogCount(HttpServletRequest request);

    //修改(所有资源 博客)浏览量
    @PutMapping("/changeLookCount")
    ZZYResult<Boolean> changeLookCount(HttpServletRequest request);

    //修改点赞量
    @PutMapping("/changeLikeCount")
    ZZYResult<Boolean> changeLikeCount(HttpServletRequest request);

    //修改评论数量
    @PutMapping("/changeCommCount")
    ZZYResult<Boolean> changeCommCount(HttpServletRequest request);

    @GetMapping("/user/info/{userId}")
    public ZZYResult<User> info(@PathVariable("userId") Long userId);
}
