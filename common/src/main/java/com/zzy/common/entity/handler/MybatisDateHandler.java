package com.zzy.common.entity.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zzy.common.UserAuth;
import com.zzy.common.UserState;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author zzy
 * @desc
 */
@Component
public class MybatisDateHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("loginTime", LocalDateTime.now(),metaObject);
        this.setFieldValByName("registerTime",LocalDateTime.now(),metaObject);
        this.setFieldValByName("state", UserState.normal,metaObject);
        this.setFieldValByName("auth", UserAuth.userAuth,metaObject);
        this.setFieldValByName("createTime", LocalDateTime.now(),metaObject);
        this.setFieldValByName("blogCount", 0L,metaObject);
//        this.setFieldValByName("userDir", 0L,metaObject);
        this.setFieldValByName("isDel", 0,metaObject);
        this.setFieldValByName("downloadCount", 0,metaObject);
        this.setFieldValByName("headImg","http://localhost:7003/static/zzy/ed6f59f6d3b34b5e82c5fbe356b2f454.jpg",metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("loginTime",LocalDateTime.now(),metaObject);
    }
}
