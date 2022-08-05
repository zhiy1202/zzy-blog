package com.zzy.userserver.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zzy
 * @desc RabbitMq 添加通道
 */
@Configuration
@EnableRabbit
public class RabbitConfig {
    @Bean
    public Exchange userExchange(){
        return ExchangeBuilder.directExchange("user_exchange").build();
    }
    //新建 user_info 表
    @Bean
    public Queue userInfo(){
        return new Queue("update_user_info");
    }
    @Bean
    public Binding userInfoBinding(Queue userInfo,Exchange userExchange){
        return BindingBuilder.bind(userInfo).to(userExchange).with("user_info_key").noargs();
    }
    //用户博客数量
    @Bean
    public Queue userBlogCount(){
        return new Queue("user_info_blog");
    }
    @Bean
    public Binding userInfoBlogBinding(Queue userBlogCount,Exchange userExchange){
        return BindingBuilder.bind(userBlogCount).to(userExchange).with("user_info_blog").noargs();
    }
    //减少数量
    @Bean
    public Queue userBlogCount2(){
        return new Queue("user_info_blog2");
    }
    @Bean
    public Binding userInfoBlogBinding2(Queue userBlogCount2,Exchange userExchange){
        return BindingBuilder.bind(userBlogCount2).to(userExchange).with("user_info_blog2").noargs();
    }
    //用户访问量
    @Bean
    public Queue userLookCount(){
        return new Queue("user_info_look");
    }
    @Bean
    public Binding userInfoLookBinding(Queue userLookCount,Exchange userExchange){
        return BindingBuilder.bind(userLookCount).to(userExchange).with("user_info_look").noargs();
    }
    //用户点赞数
    @Bean
    public Queue userLikeCount(){
        return new Queue("user_info_like");
    }
    @Bean
    public Binding userInfoLikeBinding(Queue userLikeCount,Exchange userExchange){
        return BindingBuilder.bind(userLikeCount).to(userExchange).with("user_info_like").noargs();
    }
    //用户评论数
    @Bean
    public Queue userCommCount(){
        return new Queue("user_info_comm");
    }
    @Bean
    public Binding userInfoCommBinding(Queue userCommCount,Exchange userExchange){
        return BindingBuilder.bind(userCommCount).to(userExchange).with("user_info_comm").noargs();
    }









}
