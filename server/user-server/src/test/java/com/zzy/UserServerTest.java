package com.zzy;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

/**
 * @author zzy
 * @desc
 */
@SpringBootTest
public class UserServerTest {
    @Test
    public void MybatisCodeGen(){
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/gradution-user?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true",
                        "root",
                        "333")
                        .globalConfig(builder -> {
                            builder.author("zzy") // 设置作者
                                    .enableSwagger() // 开启 swagger 模式
                                    .fileOverride() // 覆盖已生成文件
                                    .outputDir("E:\\code\\java\\dev\\graduation\\server\\user-server\\src\\main\\java"); // 指定输出目录
                        })
                        .packageConfig(builder -> {
                            builder.parent("com") // 设置父包名
                                    .moduleName("zzy") // 设置父包模块名
                                    .pathInfo(Collections.singletonMap(OutputFile.xml, "E:\\code\\java\\dev\\graduation\\server\\user-server\\src\\main\\resources\\mapper\\"));
                        })
                        .strategyConfig(builder -> {
                            builder.addInclude("t_agree_friend","t_state","t_user","t_user_auth","t_user_dir","t_user_friend","t_user_history","t_user_info","t_user_like","t_user_state")
                                    .addTablePrefix("t_", "c_")
                                    .enableCapitalMode()
                                    .entityBuilder()
                                    .enableLombok()
                                    .logicDeleteColumnName("is_del")
                                    .logicDeletePropertyName("isDel")
                                    .mapperBuilder()
                                    .superClass(BaseMapper.class)
                                    .enableMapperAnnotation();
                        })
                        .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                        .execute();
    }
}
