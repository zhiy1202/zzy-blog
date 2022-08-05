package com.zzy.resource;

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
public class ResourceTest {
    @Test
    public void ff1(){
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/gradution-resource?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true",
                "root",
                "333")
                .globalConfig(builder -> {
                    builder.author("zzy") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("E:\\code\\java\\dev\\graduation\\server\\resource-server\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.zzy") // 设置父包名
                            .moduleName("resource") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "E:\\code\\java\\dev\\graduation\\server\\resource-server\\src\\main\\resources\\mapper\\"))
                            .pathInfo(Collections.singletonMap(OutputFile.entity,"E:\\code\\java\\dev\\graduation\\common\\src\\main\\java\\com\\zzy\\common\\entity\\resource\\"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude("t_resource","t_resource_info","t_resource_type") // 设置需要生成的表名
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
