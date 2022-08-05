package com.zzy.chat.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zzy.common.entity.handler.MybatisDateHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zzy
 * @desc
 */
@Configuration
public class MybatisPlusConfig {
    @Bean
    public MetaObjectHandler metaObjectHandler(){
        return new MybatisDateHandler();
    }
}
