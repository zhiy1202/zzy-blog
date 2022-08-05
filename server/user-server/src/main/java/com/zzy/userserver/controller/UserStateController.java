package com.zzy.userserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zzy.common.entity.common.ZZYResult;
import com.zzy.common.entity.user.User;
import com.zzy.common.entity.user.UserState;
import com.zzy.userserver.service.IUserService;
import com.zzy.userserver.service.IUserStateService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
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
public class UserStateController {
    @Autowired
    private IUserStateService userStateService;


    //修改用户状态 0 正常 1 封号 2 拉黑

    /**
     *
     * @param request
     * @param userState 获取前端 传来的账号处理state 唯一id 账号处理时间
     * @return
     */

    @PostMapping("/updateState")
    public ZZYResult<Boolean> updateState(HttpServletRequest request , UserState userState){
//        插入user_state 数据
        Long userId = (Long) request.getSession(false).getAttribute("userId");
        QueryWrapper<UserState> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        UserState state = userStateService.getOne(queryWrapper);
        if (state != null){
            UpdateWrapper<UserState> wrapper = new UpdateWrapper<>();
            wrapper.set("state_id",userState.getStateId())
                    .set("intro",userState.getIntro())
                    .set("continue_time",userState.getContinueTime())
                    .eq("user_id",userId);
            return ZZYResult.getInstance(200,"修改状态执行",userStateService.update(userState,wrapper));
        }
        userState.setUserId(userId);
        boolean isOk = userStateService.save(userState);

        return ZZYResult.getInstance(200,"修改用户状态执行",isOk);
    }



}
