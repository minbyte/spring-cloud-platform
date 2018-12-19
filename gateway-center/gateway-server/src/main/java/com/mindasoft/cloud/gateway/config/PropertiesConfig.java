package com.mindasoft.cloud.gateway.config;

import com.mindasoft.cloud.gateway.util.FilterIgnoreProperties;
import com.mindasoft.cloud.gateway.util.FilterSecretProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: min
 * @date: 2018/10/18 14:39
 * @version: 1.0.0
 */
@Configuration
public class PropertiesConfig {

    @Bean
    public FilterIgnoreProperties filterIgnoreProperties() {
        return new FilterIgnoreProperties();
    }

    @Bean
    public FilterSecretProperties filterSecretProperties() {
        return new FilterSecretProperties();
    }
}
