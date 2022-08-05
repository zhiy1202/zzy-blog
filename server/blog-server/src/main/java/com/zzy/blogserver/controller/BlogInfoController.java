package com.zzy.blogserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzy.blogserver.feign.UserClient;
import com.zzy.blogserver.service.IBlogInfoService;
import com.zzy.blogserver.service.IBlogService;
import com.zzy.common.entity.blog.Blog;
import com.zzy.common.entity.blog.BlogInfo;
import com.zzy.common.entity.common.ZZYParam;
import com.zzy.common.entity.common.ZZYResult;
import com.zzy.common.entity.user.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzy
 * @since 2022-07-07
 */
@RestController
@RequestMapping("/blog")
public class BlogInfoController {
    @Autowired
    private IBlogInfoService blogInfoService;
    @Autowired
    private IBlogService blogService;
    @Autowired
    private UserClient userClient;

    @Autowired
    private AmqpTemplate amqpTemplate;
    //点赞 (更新用户信息点赞量)
    @PutMapping("/changeBlogLike/{blogId}")
    public ZZYResult<Boolean> changeBlogLike(@PathVariable("blogId") Long blogId , HttpServletRequest request){
        BlogInfo blogInfo = blogInfoService.getOne(new QueryWrapper<BlogInfo>().eq("blog_id", blogId));
        blogInfo.setLikeCount(blogInfo.getLikeCount() + 1);
        //MQ 更新用户信息
        amqpTemplate.convertAndSend("user_exchange","user_info_like",request.getSession(false).getAttribute("userId"));


        return ZZYResult.getInstance(200,"更新博客点赞数成功",blogInfoService.updateById(blogInfo));
    }

    //观看量 (更新用户信息浏览量)
    @PutMapping("/changeBlogLook/{blogId}")
    public ZZYResult<Boolean> changeBlogLook(@PathVariable("blogId") Long blogId , HttpServletRequest request){
        BlogInfo blogInfo = blogInfoService.getOne(new QueryWrapper<BlogInfo>().eq("blog_id", blogId));
        blogInfo.setLookCount(blogInfo.getLookCount() + 1);
        //MQ 更新用户信息
        amqpTemplate.convertAndSend("user_exchange","user_info_look",request.getSession(false).getAttribute("userId"));

        return ZZYResult.getInstance(200,"修改博客观看量成功",blogInfoService.updateById(blogInfo));
    }
    //获取博客信息
    @GetMapping("/getBlogInfo/{blogId}")
    public ZZYResult<ZZYParam> getBlogInfo(@PathVariable("blogId") Long blogId){
        BlogInfo blogInfo = blogInfoService.getOne(new QueryWrapper<BlogInfo>().eq("blog_id", blogId));
        //查询blog
        Blog byId = blogService.getById(blogId);
        //查询用户
        ZZYResult<User> userZZYResult = userClient.info(byId.getUserId());
        String author = userZZYResult.getData().getUsername();
        ZZYParam param = new ZZYParam();
        param.setP1(blogInfo);
        param.setP2(author);
        return ZZYResult.getInstance(200,"博客信息获取成功",param);
    }
}
