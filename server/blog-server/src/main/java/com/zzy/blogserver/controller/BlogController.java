package com.zzy.blogserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzy.blogserver.service.IBlogInfoService;
import com.zzy.blogserver.service.IBlogService;
import com.zzy.blogserver.service.ICommentService;
import com.zzy.common.PageConfig;
import com.zzy.common.entity.blog.Blog;
import com.zzy.common.entity.blog.BlogInfo;
import com.zzy.common.entity.blog.Comment;
import com.zzy.common.entity.common.ZZYResult;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
public class BlogController {

    @Autowired
    private IBlogService blogService;

    @Autowired
    private IBlogInfoService blogInfoService;
    @Autowired
    private ICommentService commentService;

    @Autowired
    private AmqpTemplate amqpTemplate;


    //新增博客 (更新用户信息 博客数)
    @PostMapping("/addBlog")
    public ZZYResult<Boolean> addBlog(HttpServletRequest request , Blog blog){
        Long userId = (Long) request.getSession(false).getAttribute("userId");
        blog.setUserId(userId);
        boolean save = blogService.save(blog);

        //使用mq 更新用户总博客数据
        amqpTemplate.convertAndSend("user_exchange","user_info_blog",userId);

        return ZZYResult.getInstance(200,"博客新增成功",save);
    }

    //修改内容
    @PutMapping("/updateBlog/{blogId}")
    public ZZYResult<Boolean> updateBlog(@PathVariable("blogId") Long blogId , Blog blog){
        blog.setBlogId(blogId);
        return ZZYResult.getInstance(200,"博客修改成功",blogService.updateById(blog));
    }

    //通过userId 获取所有blog
    @GetMapping("/allBlogsByUserId")
    public ZZYResult<List<Blog>> allBlogsByUserId(HttpServletRequest request){
        Long userId = (Long) request.getSession(false).getAttribute("userId");
        return ZZYResult.getInstance(200,"所有用户blogs",blogService.list(new QueryWrapper<Blog>().eq("user_id",userId).orderByDesc("create_time")));
    }

    //分页查询博客
    @GetMapping("/queryBlog/{pageIndex}")
    public ZZYResult<List<Blog>> queryBlog(@PathVariable("pageIndex") Integer pageIndex){
        Page<Blog> page = new Page<>(pageIndex, PageConfig.pageSize);
        List<Blog> blogList = blogService.page(page,new QueryWrapper<Blog>().orderByDesc("create_time")).getRecords();
        return ZZYResult.getInstance(200,"分页查询博客成功",blogList);
    }

    //通过系统分类查询博客
    @GetMapping("/queryType/{typeId}/{pageIndex}")
    public ZZYResult<List<Blog>> queryType(@PathVariable("typeId") Integer typeId , @PathVariable("pageIndex") Integer pageIndex){
        Page<Blog> page = new Page<>(pageIndex,PageConfig.pageSize);
        List<Blog> blogList = blogService.page(page, new QueryWrapper<Blog>().eq("type_id", typeId).orderByDesc("create_time")).getRecords();
        return ZZYResult.getInstance(200,"系统分类查询成功",blogList);
    }
    //通过用户目录进行分类
    @GetMapping("/queryDir/{userDir}/{pageIndex}")
    public ZZYResult<List<Blog>> queryDir(@PathVariable("userDir") Integer userDir , @PathVariable("pageIndex") Integer pageIndex){
        Page<Blog> page = new Page<>(pageIndex,PageConfig.pageSize);
        List<Blog> userDirs = blogService.page(page, new QueryWrapper<Blog>().eq("user_dir", userDir).orderByDesc("create_time")).getRecords();
        return ZZYResult.getInstance(200,"通过用户目录进行分类",userDirs);
    }

    //通过博客id 查询详细内容
    @GetMapping("/blogDetail/{blogId}")
    public ZZYResult<Blog> blogDetail(@PathVariable("blogId") Long blogId){
        BlogInfo blogInfo = blogInfoService.getOne(new QueryWrapper<BlogInfo>().eq("blog_id",blogId));

        //新建blogInfo 表
        if (blogInfo == null){
            blogInfoService.save(new BlogInfo(blogId,1L,0L));
        }else {
            UpdateWrapper<BlogInfo> wrapper = new UpdateWrapper<>();
            wrapper.set("look_count",blogInfo.getLookCount()+1)
                    .eq("blog_id",blogId);
            blogInfoService.update(wrapper);
        }
        //MQ 异步更新 用户总观看量
//        amqpTemplate.convertAndSend("user_exchange","user_info_look",userId);
        return ZZYResult.getInstance(200,"查询博客详细信息成功",blogService.getById(blogId));
    }
    //通过id 删除博客
    @DeleteMapping("/delBlog/{blogId}")
    public ZZYResult<Boolean> delBlog(@PathVariable("blogId") Long blogId , HttpServletRequest request){
        Long userId = (Long) request.getSession(false).getAttribute("userId");
        //使用mq 更新数据
        amqpTemplate.convertAndSend("user_exchange","user_info_blog2",userId);
        //删除所有评论
        QueryWrapper<Comment> wrapper = new QueryWrapper();
        wrapper.eq("blog_id",blogId);
        commentService.remove(wrapper);
        return ZZYResult.getInstance(200,"删除博客成功",blogService.removeById(blogId));
    }

    //通过id 删除用户目录博客
    @PutMapping("/delBlogDir/{blogId}")
    public ZZYResult<Boolean> delBlogDir(@PathVariable("blogId") Long blogId){
        int dir = 0;
        UpdateWrapper<Blog> wrapper = new UpdateWrapper<>();
        wrapper.set("user_dir",dir).eq("blog_id",blogId);
        boolean update = blogService.update(wrapper);
        return ZZYResult.getInstance(200,"从用户目录删除执行",update);
    }
    //获得所有博客数量
    @GetMapping("/admin/blogCount")
    public ZZYResult<Integer> blogCount(){
        return ZZYResult.getInstance(200,"博客数量",blogService.count());
    }
    //获取所有blog
    @GetMapping("/admin/getAllBlog")
    public ZZYResult<List<Blog>> getAllBlog(){
        return ZZYResult.getInstance(200,"所有博客",blogService.list(new QueryWrapper<Blog>().orderByDesc("create_time")));
    }
}
