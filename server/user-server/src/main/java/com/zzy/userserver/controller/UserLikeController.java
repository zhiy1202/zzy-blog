package com.zzy.userserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zzy.common.entity.blog.Blog;
import com.zzy.common.entity.common.ZZYParam;
import com.zzy.common.entity.common.ZZYResult;
import com.zzy.common.entity.user.User;
import com.zzy.common.entity.user.UserInfo;
import com.zzy.common.entity.user.UserLike;
import com.zzy.userserver.feign.BlogClient;
import com.zzy.userserver.service.IUserInfoService;
import com.zzy.userserver.service.IUserLikeService;
import com.zzy.userserver.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
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
public class UserLikeController {
    @Autowired
    private IUserLikeService userLikeService;
    @Autowired
    private BlogClient blogClient;
    @Autowired
    private IUserService userService;
    @Autowired
    private IUserInfoService userInfoService;
    //添加用户喜欢
    @PostMapping("/addLike/{blogId}")
    public ZZYResult<Boolean> addLike(@PathVariable("blogId") Long blogId , HttpServletRequest request){
        Long userId = (Long) request.getSession(false).getAttribute("userId");
        UserInfo userInfo = userInfoService.getOne(new QueryWrapper<UserInfo>().eq("user_id",userId));
        UserLike userLike = userLikeService.getOne(new QueryWrapper<UserLike>().eq("user_id",userId).eq("blog_id",blogId));
        if (userLike == null){
            userLikeService.save(new UserLike(userId,blogId));
        }else {
            UpdateWrapper<UserLike> wrapper = new UpdateWrapper<>();
            wrapper.set("create_time", LocalDateTime.now())
                    .eq("like_id",userLike.getLikeId());
            userLikeService.update(wrapper);
        }
        //更新user表
        UpdateWrapper<UserInfo> wrapper = new UpdateWrapper<>();
        wrapper.set("like_count",userInfo.getLikeCount()+1);

        return ZZYResult.getInstance(200,"添加用户喜欢执行",userInfoService.update(wrapper));
    }

    //删除用户喜欢
    @DeleteMapping("/delLike/{likeId}")
    public ZZYResult<Boolean> delLike(@PathVariable("likeId") Long likeId){
        return ZZYResult.getInstance(200,"执行删除用户喜欢",userLikeService.removeById(likeId));
    }

    //分页查询用户喜欢
    @GetMapping("/queryLike")
    public ZZYResult<List<ZZYParam>> queryLike(HttpServletRequest request){
        List<ZZYParam> list = new ArrayList<>();
        //获取用户所有的like 作者信息 文章信息
        Long userId = (Long) request.getSession(false).getAttribute("userId");
        //获取所有like
        List<UserLike> userLikes = userLikeService.list(new QueryWrapper<UserLike>().eq("user_id", userId).orderByDesc("create_time"));
        //获取所有作者文章信息
        for (UserLike userLike : userLikes) {
            //作者id
            Long authorId = userLike.getUserId();
            User user = userService.getById(authorId);
            //文章id
            Long blogId = userLike.getBlogId();
            ZZYResult<Blog> blogZZYResult = blogClient.blogDetail(blogId);
            ZZYParam param = new ZZYParam();
            param.setP1(user);
            param.setP2(blogZZYResult.getData());
            param.setP3(userLike);
            list.add(param);
        }
        return ZZYResult.getInstance(200,"分页查询like执行", list);
    }


}
