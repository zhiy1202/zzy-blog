package com.zzy.userserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author zzy
 * @desc 用户模块
 */
@SpringBootApplication
@EnableRedisHttpSession
@EnableDiscoveryClient
@EnableFeignClients
public class UserServerMain {
    public static void main(String[] args) {
        SpringApplication.run(UserServerMain.class,args);
    }
}
