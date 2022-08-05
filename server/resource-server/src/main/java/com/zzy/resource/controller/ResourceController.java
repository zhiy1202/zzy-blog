package com.zzy.resource.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzy.common.entity.common.ZZYResult;
import com.zzy.common.entity.resource.Resource;
import com.zzy.common.entity.resource.ResourceInfo;
import com.zzy.common.entity.user.User;
import com.zzy.resource.config.FileConfig;
import com.zzy.resource.feign.UserClient;
import com.zzy.resource.service.IResourceInfoService;
import com.zzy.resource.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
public class ResourceController {
    @Autowired
    private FileConfig fileConfig;
    @Autowired
    private UserClient userClient;
    @Autowired
    private IResourceService resourceService;

    //生成文件存储磁盘位置
    public String dirIsExists(String username) throws FileNotFoundException {
        //获取类路径
        String path = ResourceUtils.getURL("classpath:").getPath();
        File file = new File(path);
        //获取target路径
        String classPathParent = file.getParentFile().getPath();
        //存储目录
        return classPathParent+"\\static\\"+username+"\\";
    }
    //删除磁盘文件
    public Boolean delFile(String path){
        return FileUtil.del(path);
    }

    //获取文件目录
    //上传 博客所需图片
    //上次头像
    @PostMapping("/uploadHead")
    public ZZYResult<Boolean> uploadHead(MultipartFile file ,HttpServletRequest request){
        Long userId = (Long) request.getSession(false).getAttribute("userId");
        User user = userClient.info(userId).getData();
        ZZYResult<String> upload = upload(file, 1, user.getUsername() + "头像", 0, request);
        System.out.println(upload.getData());
        ZZYResult<Boolean> booleanZZYResult = userClient.changeHead(userId, upload.getData());
        return booleanZZYResult;
    }

    //上传
    @PostMapping("/upload")
    public ZZYResult<String> upload(MultipartFile file ,Integer rtId, String title , Integer privateId , HttpServletRequest request){
        Long userId = (Long) request.getSession(false).getAttribute("userId");
        Resource resource = new Resource();
        resource.setUserId(userId);
        resource.setRtId(rtId);
        resource.setTitle(title);
        resource.setPrivateId(privateId);

        //获取文件后缀
        String originalFilename = file.getOriginalFilename();
        String[] split = originalFilename.split("\\.");
        String fileFix = split[split.length-1];

        //生成文件名
        String filePre = IdUtil.fastSimpleUUID();
        String fileName = filePre +"."+ fileFix;

        String dirPath = null;
        String username = null;
        try {
            ZZYResult<User> info = userClient.info(userId);
            username = info.getData().getUsername();
            dirPath = dirIsExists(username);
            File fileDir = new File(dirPath);
            if (!fileDir.exists()){
                fileDir.mkdirs();
            }
            //存储文件地址
            dirPath += fileName;
            File uploadFile = new File(dirPath);
            file.transferTo(uploadFile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件写入失败");
        }

        //网络地址
        String remotePath = fileConfig.getRemotePath(username);
        remotePath += fileName;

        //写入数据库
        resource.setRemotePath(remotePath);
        boolean save = resourceService.save(resource);

        return ZZYResult.getInstance(200,"上传文件成功",remotePath);
    }

    //删除文件
    @DeleteMapping("/delResource/{resourceId}")
    public ZZYResult<Boolean> delResource(@PathVariable("resourceId") Long resourceId , HttpServletRequest request){
        Boolean isDel = false;

        Long userId = (Long) request.getSession(false).getAttribute("userId");
        //openfeign 获取用户信息
        ZZYResult<User> info = userClient.info(userId);
        String username = info.getData().getUsername();

        //查询资源文件名
        Resource resource = resourceService.getById(resourceId);
        String[] split = resource.getRemotePath().split("/");
        String filename = split[split.length - 1];
        try {
            String fileDir = dirIsExists(username);
            fileDir += filename;

//            删除磁盘文件
            Boolean delFile = delFile(fileDir);
            if (delFile){
                isDel = resourceService.removeById(resourceId);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return ZZYResult.getInstance(200,"删除资源成功",isDel);
    }

    //分类查询所有资源
    @GetMapping("/queryRes/{rtId}")
    public ZZYResult<List<Resource>> queryRes(@PathVariable("rtId") Integer rtId){
        List<Resource> list = resourceService.list(new QueryWrapper<Resource>().eq("rt_id", rtId).eq("private_id",1).orderByDesc("create_time"));
        return ZZYResult.getInstance(200,"分类查询所有资源",list);
    }

    //搜索资源
    @GetMapping("/queryByTitle/{title}")
    public ZZYResult<List<Resource>> queryByTitle(@PathVariable("title") String title){
        return ZZYResult.getInstance(200,"搜索关键字资源成功",
                resourceService.list(new QueryWrapper<Resource>().like("title",title).eq("private_id",1).orderByDesc("create_time")));
    }
    @GetMapping("/getUserResource/{rtId}")
    public ZZYResult<List<Resource>> getUserResource(@PathVariable("rtId") Long rtId,HttpServletRequest request){
        Long userId = (Long) request.getSession(false).getAttribute("userId");
        QueryWrapper<Resource> wrapper = new QueryWrapper<>();
        wrapper.eq("rt_id",rtId)
                .eq("user_id",userId);
        return ZZYResult.getInstance(200,"用户资源查询成功",resourceService.list(wrapper));
    }

    //查询所有资源
    @GetMapping("/admin/getAllResource")
    public ZZYResult<List<Resource>> getAllResource(){
        return ZZYResult.getInstance(200,"所有资源",resourceService.list());
    }

    //删除资源
    @DeleteMapping("/admin/delResource/{resourceId}")
    public ZZYResult<Boolean> delResource(@PathVariable("resourceId") Long resourceId){
        try {
            Resource resource = resourceService.getById(resourceId);
            String[] strings = resource.getRemotePath().split("/");
            String fileName = strings[strings.length -1];

            Long userId = resource.getUserId();
            User data = userClient.info(userId).getData();
            String username = data.getUsername();

            String pre = dirIsExists(username);
            pre+=fileName;
            delFile(pre);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("最终执行");
            resourceService.removeById(resourceId);
        return ZZYResult.getInstance(200,"删除成功",true);
        }

    }

}
