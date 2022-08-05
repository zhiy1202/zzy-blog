package com.zzy.resource.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zzy
 * @desc 文件路径配置
 */
@Data
@Component
@EnableConfigurationProperties({FileConfig.class})
@ConfigurationProperties(prefix = "zzy.file-config")
public class FileConfig {
    private String protocol;
    private String host;
    private String port;
    public String getRemotePath(String username){
        return this.protocol+"://"+host+":"+port+"/static/"+username+"/";
    }
}
