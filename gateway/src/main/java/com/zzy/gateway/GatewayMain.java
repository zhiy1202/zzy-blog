package com.zzy.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zzy
 * @desc
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayMain {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMain.class,args);
    }
}
