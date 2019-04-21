package com.mindasoft.cloud.security.config;

import com.mindasoft.cloud.security.oauth2.RedisAuthorizationCodeServices;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * AuthorizationCode 存储服务，
 * 默认InMemoryAuthorizationCodeServices {@link org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices}
 * @see org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer#authorizationCodeServices()
 * @author: min
 * @date: 2019/4/21 11:50
 * @version: 1.0.0
 */
@Configuration
public class AuthorizationCodeConfig {

    @Resource
    private DataSource dataSource;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Bean
    @ConditionalOnProperty(prefix="security.oauth2.code",name="storeType" ,havingValue="jdbc" ,matchIfMissing=false)
    public JdbcAuthorizationCodeServices jdbcAuthorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    @Bean
    @ConditionalOnProperty(prefix = "security.oauth2.code", name = "storeType", havingValue = "redis", matchIfMissing=false)
    public RedisAuthorizationCodeServices redisAuthorizationCodeServices() {
        RedisAuthorizationCodeServices redisAuthorizationCodeServices = new RedisAuthorizationCodeServices();
        redisAuthorizationCodeServices.setRedisTemplate(redisTemplate);
        return redisAuthorizationCodeServices;
    }
}
