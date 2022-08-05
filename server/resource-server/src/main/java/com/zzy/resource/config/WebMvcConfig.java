package com.zzy.resource.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * @author zzy
 * @desc MVC 配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @SneakyThrows
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path = null;
        //获取类路径
        path = ResourceUtils.getURL("classpath:").getPath();
        System.out.println(path);
        File file = new File(path);
        //获取target路径
        String classPathParent = file.getParentFile().getPath();
        System.out.println(classPathParent);

        String resourcePath = classPathParent+"\\static\\";
        registry.addResourceHandler("/static/**").addResourceLocations("file:"+resourcePath);
    }
}
