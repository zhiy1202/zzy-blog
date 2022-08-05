package com.zzy.userserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzy.common.entity.common.ZZYParam;
import com.zzy.common.entity.common.ZZYResult;
import com.zzy.common.entity.user.AgreeFriend;
import com.zzy.common.entity.user.User;
import com.zzy.common.entity.user.UserFriend;
import com.zzy.userserver.service.IAgreeFriendService;
import com.zzy.userserver.service.IUserFriendService;
import com.zzy.userserver.service.IUserService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
public class AgreeFriendController {
    @Autowired
    private IAgreeFriendService agreeFriendService;
    @Autowired
    private IUserFriendService userFriendService;
    @Autowired
    private IUserService userService;

    //判断好友是否添加
    public boolean isAddFriend(Long userId,Long friendId){
        QueryWrapper<UserFriend> wrapper = new QueryWrapper<>();
        wrapper.eq("user_one",userId)
                .eq("user_two",friendId)
                .or()
                .eq("user_two",userId)
                .eq("user_one",friendId);
        UserFriend friend = userFriendService.getOne(wrapper);
        QueryWrapper<AgreeFriend> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("send_id",userId)
                .eq("agree_id",friendId);
        AgreeFriend agreeFriend = agreeFriendService.getOne(wrapper1);
        if (friend != null && agreeFriend == null){
            return false;
        }else {
            return true;
        }
    }

    //发送好友请求
    @PostMapping("/sendFriend/{fId}")
    public ZZYResult<Boolean> sendFriend(@PathVariable("fId") Long fId , HttpServletRequest request){
        Long userId = (Long) request.getSession(false).getAttribute("userId");
        //判断是否添加好友
        if (isAddFriend(userId,fId)){
            AgreeFriend agreeFriend = new AgreeFriend(userId,fId);
            boolean save = agreeFriendService.save(agreeFriend);
            return ZZYResult.getInstance(200,"好友发送成功",save);
        }
        return ZZYResult.getInstance(200,"好友以存在",false);
    }
    //添加好友成功 (删除记录 插入好友表)
    @DeleteMapping("/addFriend/{afId}")
    public ZZYResult<Boolean> addFriend(@PathVariable("afId") Long afId){
        AgreeFriend agreeFriend = agreeFriendService.getById(afId);
        System.out.println(agreeFriend.getSendId());

        //好友添加
        boolean save = userFriendService.save(new UserFriend(agreeFriend.getSendId(), agreeFriend.getAgreeId()));
        agreeFriendService.removeById(afId);
        return ZZYResult.getInstance(200,"好友正在添加",save);
    }
    //删除好友申请
    @DeleteMapping("/delAgreeFriend/{afId}")
    public ZZYResult<Boolean> delAgreeFriend(@PathVariable("afId") Long afId){
        return ZZYResult.getInstance(200,"申请列表删除成功",agreeFriendService.removeById(afId));
    }
    //获取所有好友
    @GetMapping("/getAllAgreeFriend")
    public ZZYResult<List<ZZYParam>> getAllAgreeFriend(HttpServletRequest request){
        //获取申请人信息(sendId) agreeId = this.userId
        List<ZZYParam> params = new ArrayList<>();
        Long userId = (Long) request.getSession(false).getAttribute("userId");
        QueryWrapper<AgreeFriend> wrapper = new QueryWrapper<>();
        wrapper.eq("agree_id",userId);
        List<AgreeFriend> agreeFriends = agreeFriendService.list(wrapper);
        for (AgreeFriend agreeFriend : agreeFriends) {
            ZZYParam param = new ZZYParam();
            param.setP1(agreeFriend);
            User sendUser = userService.getById(agreeFriend.getSendId());
            param.setP2(sendUser);
            params.add(param);
        }
        return ZZYResult.getInstance(200,"获取用户所有申请列表成功",params);
    }

}
