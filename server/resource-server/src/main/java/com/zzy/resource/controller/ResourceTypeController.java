package com.zzy.resource.controller;

import com.zzy.common.entity.common.ZZYResult;
import com.zzy.common.entity.resource.ResourceType;
import com.zzy.resource.service.IResourceTypeService;
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
 * @since 2022-07-08
 */
@RestController
@RequestMapping("/resource")
public class ResourceTypeController {
    @Autowired
    private IResourceTypeService resourceTypeService;
    //增加分类
    @PostMapping("/addResType")
    public ZZYResult<Boolean> addResType(String typeName){
        return ZZYResult.getInstance(200,"新增资源类型成功",resourceTypeService.save(new ResourceType(typeName)));
    }

    //删除分类
    @DeleteMapping("/delResType/{rtId}")
    public ZZYResult<Boolean> delResType(@PathVariable("rtId") Integer rtId){
        return ZZYResult.getInstance(200,"删除资源类型成功",resourceTypeService.removeById(rtId));
    }

    //得到所有分类
    @GetMapping("/getResTypes")
    public ZZYResult<List<ResourceType>> getResTypes(){
        return ZZYResult.getInstance(200,"获取所有资源类型成功",resourceTypeService.list());
    }

}
