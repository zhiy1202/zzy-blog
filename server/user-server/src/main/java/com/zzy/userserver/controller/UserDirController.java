package com.zzy.userserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzy.common.entity.common.ZZYResult;
import com.zzy.common.entity.user.UserDir;
import com.zzy.userserver.service.IUserDirService;
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
public class UserDirController {
    @Autowired
    private IUserDirService userDirService;
    //新建目录
    @PostMapping("/addDir")
    public ZZYResult<Boolean> addDir(HttpServletRequest request , String dirName){
        Long userId = (Long) request.getSession(false).getAttribute("userId");
        UserDir dir = new UserDir(userId,dirName);
        return ZZYResult.getInstance(200,"添加用户成功",userDirService.save(dir));

    }
    //删除目录
    @DeleteMapping("/delDir/{dirId}")
    public ZZYResult<Boolean> dilDir(@PathVariable("dirId") Integer dilDir){
        return ZZYResult.getInstance(200,"删除目录成功",userDirService.removeById(dilDir));
    }
    //查询所有目录
    @GetMapping("/queryDirs")
    public ZZYResult<List<UserDir>> queryDirs(HttpServletRequest request){
        Long userId = (Long) request.getSession(false).getAttribute("userId");
        List<UserDir> userDirs = userDirService.list(new QueryWrapper<UserDir>().eq("user_id", userId));
        return ZZYResult.getInstance(200,"查询用户所有目录成功",userDirs);
    }
    @GetMapping("/queryDirById/{userId}")
    public ZZYResult<List<UserDir>> queryDirsById(@PathVariable("userId") Long userId){
        return ZZYResult.getInstance(200,"用户目录获取成功",userDirService.list(new QueryWrapper<UserDir>().eq("user_id",userId)));
    }

}
