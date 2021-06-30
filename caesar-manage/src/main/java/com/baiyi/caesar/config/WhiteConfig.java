package com.baiyi.caesar.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 白名单配置
 * @Author baiyi
 * @Date 2021/6/1 11:36 上午
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "white", ignoreInvalidFields = true)
public class WhiteConfig {

     private List<String> urls;
}
