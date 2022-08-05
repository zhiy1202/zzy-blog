package com.zzy.blogserver.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.zzy.common.entity.handler.MybatisDateHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zzy
 * @desc mybatis plus 配置类
 */
@Configuration
public class MybatisPlusConfig {
    // mybatisPlus 自动填充功能
    @Bean
    public MetaObjectHandler metaObjectHandler(){
        return new MybatisDateHandler();
    }

    //mybatisPlus 分页插件
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
        return interceptor;
    }
}
