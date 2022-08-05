package com.zzy.userserver.controller;

import com.zzy.common.entity.common.ZZYResult;
import com.zzy.common.entity.user.UserAuth;
import com.zzy.userserver.service.IUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

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
public class UserAuthController {

    @Autowired
    private IUserAuthService userAuthService;
    //添加用户权限
    @PostMapping("/addAuth")
    public ZZYResult<Boolean> addAuth(UserAuth userAuth){
        return ZZYResult.getInstance(200,"添加用户权限成功",userAuthService.save(userAuth));
    }
    //删除用户权限
    @DeleteMapping("/delAuth/{uaId}")
    public ZZYResult<Boolean> delAuth(@PathVariable("uaId") Integer uaId){
        return ZZYResult.getInstance(200,"删除用户权限成功",userAuthService.removeById(uaId));
    }
    //获取所有权限
    @GetMapping("/allAuth")
    public ZZYResult<List<UserAuth>> allAuth(){
        return ZZYResult.getInstance(200,"获取所有权限",userAuthService.list());
    }

}
