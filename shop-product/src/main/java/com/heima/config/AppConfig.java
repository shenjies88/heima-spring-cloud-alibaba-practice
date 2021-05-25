package com.heima.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author shenjies88
 * @since 2021/5/25-10:32 上午
 */
@Data
@ConfigurationProperties(prefix = "app-config")
@RefreshScope
@Component
public class AppConfig {

    private String name;
}
