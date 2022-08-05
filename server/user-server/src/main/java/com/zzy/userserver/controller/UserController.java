package com.zzy.userserver.controller;

import cn.hutool.captcha.generator.RandomGenerator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zzy.common.UserAuth;
import com.zzy.common.entity.common.ZZYResult;
import com.zzy.common.entity.user.User;
import com.zzy.userserver.service.IUserService;
import com.zzy.userserver.util.ZZYMailUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
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
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private AmqpTemplate amqpTemplate;

    //用户名是否存在
    @GetMapping("/userIsExits")
    public ZZYResult<Boolean> userIsExits(String username){
        User user = userService.getOne(new QueryWrapper<User>().eq("username", username));
        return ZZYResult.getInstance(200,"查询用户是否存在",user == null ? false : true);
    }
    //邮箱是否存在
    @GetMapping("/emailIsExits")
    public ZZYResult<Boolean> emailIsExits(String email){
        User user = userService.getOne(new QueryWrapper<User>().eq("email", email));
        return ZZYResult.getInstance(200,"查询邮箱是否注册",user == null ? false : true);
    }
    //发送注册验证码
    @PostMapping("/sendEmail")
    public void sendEmail(String email){
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 6);
        String code = randomGenerator.generate();
        //放入redis
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set(email,code);
        //发送邮件
        SimpleMailMessage message = ZZYMailUtil.getSimpleMailMessage(email, "ZZY 信息资源共享网验证码", code);
        mailSender.send(message);
    }

    //注册 (注册成功 创建userinfo 表 声明 用户状态 和 用户权限)
    @PostMapping("/register")
    public ZZYResult<Boolean> register(User user,String authCode){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String code = ops.get(user.getEmail());
        if (!code.equals(authCode)){
            return ZZYResult.getInstance(403, "验证码错误", false);
        }
        boolean save = userService.save(user);
        User one = userService.getOne(new QueryWrapper<User>().eq("email", user.getEmail()));
        //异步创建
        amqpTemplate.convertAndSend("user_exchange","user_info_key",one.getUserId());

        return ZZYResult.getInstance(200, "用户注册成功", save);
    }

    //登录 (邮箱/用户名)
    @PostMapping("/login")
    public ZZYResult<Boolean> login(String account, String password , HttpServletRequest request , HttpServletResponse response){
        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .eq("username", account)
                .eq("password", password)
                .eq("state",1)
                .or()
                .eq("email", account)
                .eq("password", password)
                .eq("state",1);
        User one = userService.getOne(wrapper);
        if (one == null){
            return ZZYResult.getInstance(403,"账号或密码错误",false);
        }
        one.setLoginTime(LocalDateTime.now());
        userService.updateById(one);
        request.getSession().setAttribute("userId",one.getUserId());
        return ZZYResult.getInstance(200,"欢迎"+one.getUsername(),true);
    }
    //通过邮箱修改密码(忘记密码)
    @PutMapping("/changePwd")
    public ZZYResult<Boolean> changePwd(String email,String authCode , String password){
        String s = redisTemplate.opsForValue().get(email);
        if (s != null){
            if (!s.equals(authCode)){
                return ZZYResult.getInstance(403,"验证码错误",false);
            }
            //修改密码
            UpdateWrapper<User> wrapper = new UpdateWrapper<>();
            wrapper.set("password",password).eq("email",email);
            userService.update(wrapper);
            return ZZYResult.getInstance(200,"修改密码成功",true);
        }
        return ZZYResult.getInstance(405,"验证码不能为空",false);
    }

    //修改
    @PutMapping("/updateInfo")
    public ZZYResult<Boolean> updateInfo(HttpServletRequest request,User user){
        Long userId = (Long) request.getSession(false).getAttribute("userId");

        UpdateWrapper<User> wrapper = new UpdateWrapper<User>()
                .set("username", user.getUsername())
                .set("password", user.getPassword())
                .set("birthday", user.getBirthday())
                .set("address", user.getAddress())
                .set("intro", user.getIntro())
                .eq("user_id", userId);
        return ZZYResult.getInstance(200,"用户信息修改成功",userService.update(wrapper));
    }
    //修改头像
    @PutMapping("/changeHead")
    public ZZYResult<Boolean> changeHead(@RequestParam("userId") Long userId,@RequestParam("headPath") String headPath){
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("head_img",headPath).eq("user_id",userId);
        return ZZYResult.getInstance(200,"修改头像成功",userService.update(wrapper));
    }
    //通过用户id 获取信息
    @GetMapping("/info/{userId}")
    public ZZYResult<User> info(@PathVariable("userId") Long userId){
        return ZZYResult.getInstance(200,"用户信息",userService.getById(userId));
    }
    //获取个人信息
    @GetMapping("/getself")
    public ZZYResult<User> getSelf(HttpServletRequest request){
        Long userId = (Long) request.getSession(false).getAttribute("userId");
        return ZZYResult.getInstance(200,"个人信息",userService.getById(userId));
    }

    //删除用户
    @DeleteMapping("/delUser/{userId}")
    public ZZYResult<Boolean> delUserId(@PathVariable("userId") Long userId){
        return ZZYResult.getInstance(200,"用户删除成功",userService.removeById(userId));
    }
    //获取所有用户
    @GetMapping("/allUser")
    public ZZYResult<List<User>> allUser(){
        List<User> list = userService.list();
        return ZZYResult.getInstance(200,"所有用户",list);
    }

    //admin登录 userAuth = 2
    @PostMapping("/admin/login")
    public ZZYResult<Boolean> adminLogin(String username , String password , HttpServletRequest request){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username).eq("password",password).eq("auth", UserAuth.adminAuth)
                .or()
                .eq("username",username).eq("password",password).eq("auth", UserAuth.god);
        User one = userService.getOne(wrapper);
        if (one != null){
            request.getSession().setAttribute("admin",one.getUserId());
            return ZZYResult.getInstance(200,"管理员登录成功",true);
        }
        return ZZYResult.getInstance(403,"无权限",false);
    }
    //修改权限
    @PutMapping("/admin/updateAuth/{userId}")
    public ZZYResult<Boolean> upAuth(@PathVariable("userId") Long userId, Integer auth, HttpServletRequest request){
        Long adminId = (Long) request.getSession(false).getAttribute("admin");
        User admin = userService.getById(adminId);
        User user = userService.getById(userId);
        if (admin.getAuth() > user.getAuth()){
            user.setAuth(auth);
            boolean update = userService.updateById(user);
            return ZZYResult.getInstance(200,"用户权限提升成功",update);
        }else {
            return ZZYResult.getInstance(200,"用户权限提升不足",false);
        }
    }

    //修改用户状态
    @PutMapping("/admin/updateState/{userId}")
    public ZZYResult<Boolean> updateState(@PathVariable("userId") Long userId,int state , HttpServletRequest request){
        Long adminId = (Long) request.getSession(false).getAttribute("admin");
        User admin = userService.getById(adminId);
        User user = userService.getById(userId);
        if (admin.getAuth() > user.getAuth()){
            user.setState(state);
            boolean update = userService.updateById(user);
            return ZZYResult.getInstance(200,"用户状态修改成功",update);
        }
        return ZZYResult.getInstance(200,"用户状态修改失败",false);
    }

    //todo 用户封禁
}
