package com.zzy.blogserver.controller;

import com.zzy.blogserver.service.IBlogTypeService;
import com.zzy.common.entity.blog.BlogType;
import com.zzy.common.entity.common.ZZYResult;
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
 * @since 2022-07-07
 */
@RestController
@RequestMapping("/blog")
public class BlogTypeController {

    @Autowired
    private IBlogTypeService blogTypeService;
    //新增博客类型
    @PostMapping("/addBlogType")
    public ZZYResult<Boolean> addBlogType(BlogType blogType){
        return ZZYResult.getInstance(200,"新建系统分类成功",blogTypeService.save(blogType));
    }

    //删除博客类型
    @DeleteMapping("/delBlogType/{typeId}")
    public ZZYResult<Boolean> deleteBlogType(@PathVariable("typeId") Integer typeId){
        return ZZYResult.getInstance(200,"删除系统分类成功",blogTypeService.removeById(typeId));
    }

    //查找所有博客类型
    @GetMapping("/getBlogTypes")
    public ZZYResult<List<BlogType>> getTypes(){
        return ZZYResult.getInstance(200,"查询系统分类成功",blogTypeService.list());
    }
}
