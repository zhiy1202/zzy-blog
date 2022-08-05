package com.zzy.userserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzy.common.entity.common.ZZYParam;
import com.zzy.common.entity.common.ZZYResult;
import com.zzy.common.entity.user.User;
import com.zzy.common.entity.user.UserInfo;
import com.zzy.userserver.service.IUserInfoService;
import com.zzy.userserver.service.IUserService;
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
 * @since 2022-07-02
 */
@RestController
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private IUserService userService;
    @Autowired
    private AmqpTemplate amqpTemplate;

    //在用户新建时 创建t_user_info 默认值都为0
    //修改 博客数量
    @PutMapping("/changeBlogCount")
    public ZZYResult<Boolean> changeBlogCount(HttpServletRequest request){
        Long userId = (Long) request.getSession(false).getAttribute("userId");
        amqpTemplate.convertAndSend("user_exchange","user_info_blog",userId);
        return ZZYResult.getInstance(200,"博客数量已更新",true);
    }

    //修改(所有资源 博客)浏览量
    @PutMapping("/changeLookCount")
    public ZZYResult<Boolean> changeLookCount(HttpServletRequest request){
        Long userId = (Long) request.getSession(false).getAttribute("userId");
        amqpTemplate.convertAndSend("user_exchange","user_info_look",userId);
        return ZZYResult.getInstance(200,"用户访问已更新",true);
    }

    //修改点赞量
    @PutMapping("/changeLikeCount")
    public ZZYResult<Boolean> changeLikeCount(HttpServletRequest request){
        Long userId = (Long) request.getSession(false).getAttribute("userId");
        amqpTemplate.convertAndSend("user_exchange","user_info_like",userId);
        return ZZYResult.getInstance(200,"用户点赞数已更新",true);
    }

    //修改评论数量
    @PutMapping("/changeCommCount")
    public ZZYResult<Boolean> changeCommCount(HttpServletRequest request){
        Long userId = (Long) request.getSession(false).getAttribute("userId");
        amqpTemplate.convertAndSend("user_exchange","user_info_comm",userId);
        return ZZYResult.getInstance(200,"用户评论数已更新",true);
    }

    //获取userInfo
    @GetMapping("/getUserInfo/{userId}")
    public ZZYResult<ZZYParam> getUserInfo(@PathVariable("userId") Long userId){
        UserInfo userInfo = userInfoService.getOne(new QueryWrapper<UserInfo>().eq("user_id", userId));
        User byId = userService.getById(userId);
        ZZYParam param = new ZZYParam();
        param.setP1(userInfo);
        param.setP2(byId);
        return ZZYResult.getInstance(200,"用户信息查询成功",param);

    }


}
