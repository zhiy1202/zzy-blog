package com.zzy.userserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzy.common.PageConfig;
import com.zzy.common.entity.blog.Blog;
import com.zzy.common.entity.common.ZZYParam;
import com.zzy.common.entity.common.ZZYResult;
import com.zzy.common.entity.user.User;
import com.zzy.common.entity.user.UserHistory;
import com.zzy.userserver.feign.BlogClient;
import com.zzy.userserver.service.IUserHistoryService;
import com.zzy.userserver.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

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
 * @since 2022-07-02
 */
@RestController
@RequestMapping("/user")
public class UserHistoryController {
    @Autowired
    private IUserHistoryService historyService;
    @Autowired
    private IUserService userService;
    @Autowired
    private BlogClient blogClient;
    //添加历史记录
    @PostMapping("/addHistory/{blogId}")
    public ZZYResult<Boolean> addHistory(@PathVariable("blogId") Long blogId, HttpServletRequest request){
        Long userId = (Long) request.getSession(false).getAttribute("userId");
        UserHistory userHistory = historyService.getOne(new QueryWrapper<UserHistory>().eq("user_id",userId).eq("blog_id",blogId));
        Boolean isOk = true;
        if (userHistory == null){
            isOk = historyService.save(new UserHistory(userId,blogId));
        }else {
            UpdateWrapper<UserHistory> wrapper = new UpdateWrapper<>();
            wrapper.set("create_time", LocalDateTime.now())
                    .eq("ht_id",userHistory.getHtId());
            isOk = historyService.update(wrapper);
        }
        return ZZYResult.getInstance(200,"执行添加历史记录",isOk);
    }

    //删除历史记录
    @DeleteMapping("/delHistory/{htId}")
    public ZZYResult<Boolean> delHistory(@PathVariable("htId") Long htId){
        return ZZYResult.getInstance(200,"执行删除历史记录",historyService.removeById(htId));
    }

    //查询历史记录 (倒序)
    @GetMapping("/queryHistory")
    public ZZYResult<List<ZZYParam>> queryHistory(HttpServletRequest request){
        List<ZZYParam> params = new ArrayList<>();
        Long userId = (Long) request.getSession(false).getAttribute("userId");
        QueryWrapper<UserHistory> wrapper = new QueryWrapper<UserHistory>().eq("user_id", userId).orderByDesc("create_time");
        List<UserHistory> userHistories = historyService.list(wrapper);

        for (UserHistory userHistory : userHistories) {
            ZZYParam param = new ZZYParam();
            //得到作者id
            Long authorId = userHistory.getUserId();
            User user = userService.getById(authorId);
            param.setP1(user);
            Long blogId = userHistory.getBlogId();
            ZZYResult<Blog> blogZZYResult = blogClient.blogDetail(blogId);
            param.setP2(blogZZYResult.getData());
            param.setP3(userHistory);
            params.add(param);

        }
        return ZZYResult.getInstance(200,"执行查询历史记录", params);
    }

}
