package com.zzy.userserver.handler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzy.common.entity.user.UserInfo;
import com.zzy.userserver.service.IUserInfoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zzy
 * @desc 接收消息
 */
@Component
public class MQHandler {
    @Autowired
    private IUserInfoService userInfoService;

    //通过用户id 查询用户
    public UserInfo getUserInfoById(Long userId){
        return userInfoService.getOne(new QueryWrapper<UserInfo>().eq("user_id",userId));
    }
    //创建t_user_info 表
    @RabbitListener(queues = "update_user_info")
    public void insertUserInfo(Long message){
        System.out.println(message);
        System.out.println("用户T_user_info 创建");
        UserInfo info = new UserInfo(message, 0L, 0L, 0L, 0L);
        userInfoService.save(info);
        System.out.println("用户 info 表创建成功");
    }

    //修改用户博客数量
    @RabbitListener(queues = "user_info_blog")
    public void updateUserBlogCount(Long userId){
        System.out.println("博客数量开始修改");
        UserInfo userInfo = getUserInfoById(userId);
        userInfo.setBlogCount(userInfo.getBlogCount()+1);
        userInfoService.updateById(userInfo);
        System.out.println("博客数量修改完毕");
    }
    @RabbitListener(queues = "user_info_blog2")
    public void updateUserBlogCount2(Long userId){
        System.out.println("博客数量开始修改");
        UserInfo userInfo = getUserInfoById(userId);
        userInfo.setBlogCount(userInfo.getBlogCount()-1);
        userInfoService.updateById(userInfo);
        System.out.println("博客数量修改完毕");
    }
    //修改用户访问量
    @RabbitListener(queues = "user_info_look")
    public void updateUserLookCount(Long userId){
        System.out.println("用户访问数量开始修改");
        UserInfo userInfo = getUserInfoById(userId);
        userInfo.setLookCount(userInfo.getLookCount()+1);
        userInfoService.updateById(userInfo);
        System.out.println("用户访问数量修改完毕");
    }
    //修改用户点赞数
    @RabbitListener(queues = "user_info_like")
    public void updateUserLikeCount(Long userId){
        System.out.println("用户点赞数量开始修改");
        UserInfo userInfo = getUserInfoById(userId);
        userInfo.setLikeCount(userInfo.getLikeCount()+1);
        userInfoService.updateById(userInfo);
        System.out.println("用户点赞数量修改完毕");
    }

    //修改用户评论数
    @RabbitListener(queues = "user_info_comm")
    public void updateUserCommCount(Long userId){
        System.out.println("用户评论数量开始修改");
        UserInfo userInfo = getUserInfoById(userId);
        userInfo.setCommCount(userInfo.getCommCount()+1);
        userInfoService.updateById(userInfo);
        System.out.println("用户评论数量修改完毕");
    }
}
