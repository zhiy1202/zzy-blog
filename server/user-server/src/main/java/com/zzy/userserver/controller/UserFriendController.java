package com.zzy.userserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzy.common.entity.common.ZZYParam;
import com.zzy.common.entity.common.ZZYResult;
import com.zzy.common.entity.user.User;
import com.zzy.common.entity.user.UserFriend;
import com.zzy.userserver.service.IUserFriendService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

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
public class UserFriendController {

    @Autowired
    private IUserFriendService userFriendService;
    @Autowired
    private UserController userController;

    //删除好友
    @DeleteMapping("/del/{ufId}")
    public ZZYResult<Boolean> delUfId(@PathVariable("ufId") Long ufId){
        return ZZYResult.getInstance(200,"用户执行删除",userFriendService.removeById(ufId));
    }
    //得到所有好友的信息
    @GetMapping("/getFriend/{userId}")
    public ZZYResult<List<ZZYParam>> getFriends(@PathVariable("userId") Long userId){
        List<UserFriend> list = userFriendService.list(new QueryWrapper<UserFriend>()
                .eq("user_one", userId)
                .or()
                .eq("user_two", userId));
        List<ZZYParam> params = new ArrayList<>();
        for (UserFriend userFriend : list) {
            ZZYParam param = new ZZYParam();
            if (userFriend.getUserOne() != userId){
                param.setP1(userController.info(userFriend.getUserOne()).getData());
            }else {
                param.setP1(userController.info(userFriend.getUserTwo()).getData());
            }
            param.setP2(userFriend);
            params.add(param);
        }
        return ZZYResult.getInstance(200,
                "所有好友id查询成功",
                params);
    }
    //聊天室 好友列表
    @GetMapping("/getFriendNav/{userId}")
    public ZZYResult<List<User>> getFriendNav(@PathVariable("userId") Long userId){
        List<UserFriend> list = userFriendService.list(new QueryWrapper<UserFriend>()
                .eq("user_one", userId)
                .or()
                .eq("user_two", userId));
        List<User> params = new ArrayList<>();
        for (UserFriend userFriend : list) {
            if (userFriend.getUserOne() != userId){
                params.add(userController.info(userFriend.getUserOne()).getData());
            }else {
                params.add(userController.info(userFriend.getUserTwo()).getData());
            }
        }
        return ZZYResult.getInstance(200,
                "所有好友id查询成功",
                params);
    }

}
