package com.zzy.blogserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzy.blogserver.service.IBlogService;
import com.zzy.blogserver.service.ICommentService;
import com.zzy.common.entity.blog.Blog;
import com.zzy.common.entity.blog.Comment;
import com.zzy.common.entity.common.ZZYParam;
import com.zzy.common.entity.common.ZZYResult;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
public class CommentController {
    @Autowired
    private ICommentService commentService;
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private IBlogService blogService;
    // 添加评论 (修改用户信息表评论数)
    @PostMapping("/addBlogComm/{blogId}")
    public ZZYResult<Boolean> addBlogComm(@PathVariable("blogId") Long blogId , HttpServletRequest request , Comment comment){
        Long userId = (Long) request.getSession(false).getAttribute("userId");
        comment.setUserId(userId);
        comment.setBlogId(blogId);

        //MQ 更新用户信息表
        amqpTemplate.convertAndSend("user_exchange","user_info_comm",userId);

        return ZZYResult.getInstance(200,"添加评论成功",commentService.save(comment));
    }
    //查询博客评论
    @GetMapping("/queryComm/{blogId}")
    public ZZYResult<List<Comment>> queryComm(@PathVariable("blogId") Long blogId){
        return ZZYResult.getInstance(200,"查询评论成功",commentService.list(new QueryWrapper<Comment>().eq("blog_id",blogId).orderByDesc("create_time")));
    }
    //获取用户所有的评论
    @GetMapping("/allCommByUser")
    public ZZYResult<List<ZZYParam>> allCommByUser(HttpServletRequest request){
        Long userId = (Long) request.getSession(false).getAttribute("userId");
        List<ZZYParam> zzyParamList = new ArrayList<>();
        //去除重复博客
        List<Comment> blogIds = commentService.list(new QueryWrapper<Comment>().select("distinct blog_id").eq("user_id", userId));

        //获取评论过的所有blog
        for (Comment comment : blogIds) {
            Blog blog = blogService.getById(comment.getBlogId());
            List<Comment> commentList = commentService.list(new QueryWrapper<Comment>()
                    .eq("blog_id", comment.getBlogId())
                    .eq("user_id",userId));
            ZZYParam param = new ZZYParam();
            param.setP1(blog);
            param.setP2(commentList);
            zzyParamList.add(param);
        }

        //获取blog中的所有评论
        return ZZYResult.getInstance(200,"用户评论查询成功",zzyParamList);
    }
    //删除博客评论
    @DeleteMapping("/deleteCommById/{commId}")
    public ZZYResult<Boolean> deleteCommByCommId(@PathVariable("commId") Long commId){
            return ZZYResult.getInstance(200,"删除评论成功",commentService.removeById(commId));
    }
}
