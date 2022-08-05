package com.zzy.userserver.controller;

import com.zzy.common.entity.common.ZZYResult;
import com.zzy.common.entity.user.State;
import com.zzy.common.entity.user.UserState;
import com.zzy.userserver.service.IStateService;
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
public class StateController {
    @Autowired
    private IStateService stateService;
    //添加新的用户状态
    @PostMapping("/addState")
    public ZZYResult<Boolean> addState(String stateName){
        return ZZYResult.getInstance(200,"添加状态成功",stateService.save(new State(stateName)));
    }

    //删除用户状态
    @DeleteMapping("/delState/{stateId}")
    public ZZYResult<Boolean> delState(@PathVariable("stateId") Integer stateId){
        return ZZYResult.getInstance(200,"删除状态成功",stateService.removeById(stateId));
    }
    @GetMapping("/admin/allState")
    public ZZYResult<List<State>> allState(){
        return ZZYResult.getInstance(200,"获取所有状态成功",stateService.list());
    }

}
