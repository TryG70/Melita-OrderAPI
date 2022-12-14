package com.melita.ordertakingapi.configuration;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "env-values")
@RefreshScope
public class EnvironmentConfig {

    private String authToken;
}
