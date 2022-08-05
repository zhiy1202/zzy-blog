package com.zzy.chat;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author zzy
 * @desc
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableRabbit
@EnableRedisHttpSession
@EnableFeignClients
public class ChatServerMain {
    public static void main(String[] args) {
        SpringApplication.run(ChatServerMain.class, args);
    }
}
