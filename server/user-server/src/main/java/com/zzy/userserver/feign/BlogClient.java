package com.zzy.userserver.feign;

import com.zzy.common.entity.blog.Blog;
import com.zzy.common.entity.common.ZZYResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zzy
 * @desc
 */
@Service
@FeignClient("blog-server")
public interface BlogClient {
    @GetMapping("/blog/blogDetail/{blogId}")
        ZZYResult<Blog> blogDetail(@PathVariable("blogId") Long blogId);
}
