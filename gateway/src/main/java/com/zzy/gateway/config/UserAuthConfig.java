package com.zzy.gateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author zzy
 * @desc
 */
@Component
public class UserAuthConfig implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().value();
        //需要拦截的路径
        /**
         * /user/*
         * 除去
         * /user/login
         * /user/register
         * /user/admin/login
         * /user/getUserInfo/*
         */
        if ("/user/login".equals(path)
            || "/user/register".equals(path)
            || "/user/admin/login".equals(path)){
                return chain.filter(exchange);
        }
        if (path.matches("/user/admin/(.*)")
                || path.equals("/user/allAuth")
                || path.equals("/user/allUser")
                || "/blog/admin/getAllBlog".equals(path)
                || "/resource/admin/getAllResource".equals(path)){
            boolean isAdmin = doCheckAdmin(exchange.getRequest());
            if (isAdmin){
                System.out.println(path+"放行");
                return chain.filter(exchange);
            }else {
                System.out.println(path+"拦截");
                exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
                return exchange.getResponse().setComplete();
            }
        }
        if (path.matches("/user/info/(.*)") || path.matches("/user/getUserInfo/(.*)") || path.matches("/user/queryDirById/(.*)")){
            return chain.filter(exchange);
        }
        if (path.matches("/user/(.*)")){
            boolean isUser = doCheckUser(exchange.getRequest());
            if (isUser){
                System.out.println(path+"放行");
                return chain.filter(exchange);
            }else {
                System.out.println(path+"拦截");
                exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
                return exchange.getResponse().setComplete();
            }
        }
        System.out.println(path+"放行");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
    private boolean doCheckAdmin(ServerHttpRequest request){
        String auth = request.getHeaders().getFirst("admin");
        if (!"".equals(auth) && auth != null){
            return true;
        }else {
            return false;
        }
    }
    private boolean doCheckUser(ServerHttpRequest request){
        String auth = request.getHeaders().getFirst("user");
        if (!"".equals(auth) && auth != null){
            return true;
        }else {
            return false;
        }
    }
}
