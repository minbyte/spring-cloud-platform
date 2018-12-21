package com.mindasoft.cloud.security.config;

import com.mindasoft.cloud.security.oauth2.RedisClientDetailsService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: min
 * @date: 2018/12/20 17:00
 * @version: 1.0.0
 */
@Configuration
public class ClientDetailsServiceConfig {

    @Resource
    private DataSource dataSource;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Bean
    @ConditionalOnProperty(prefix="security.oauth2.clientDetails.load",name="type" ,havingValue="inMemory" ,matchIfMissing=true)
    public InMemoryClientDetailsService inMemoryClientDetailsService(){
        InMemoryClientDetailsService clientDetailsService = new InMemoryClientDetailsService();

        Map<String, ClientDetails> clientDetailsStore = new HashMap();
        BaseClientDetails clientDetails = new BaseClientDetails();
        clientDetails.setClientId("console");
        clientDetails.setClientSecret("console");
        clientDetails.setScope(new ArrayList<String>(){{add("web");}});
        clientDetails.setAuthorizedGrantTypes(new ArrayList<String>(){{add("password");add("implicit");}});
        clientDetails.setAccessTokenValiditySeconds(3600);
        clientDetailsStore.put(clientDetails.getClientId(),clientDetails);
        clientDetailsService.setClientDetailsStore(clientDetailsStore);

        return clientDetailsService;
    }

    @Bean
    @ConditionalOnProperty(prefix="security.oauth2.clientDetails.load",name="type" ,havingValue="jdbc" ,matchIfMissing=false)
    public JdbcClientDetailsService jdbcClientDetailsService() {
        JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        return clientDetailsService;
    }

    @Bean // 声明 ClientDetails实现
    @ConditionalOnProperty(prefix = "security.oauth2.clientDetails.load", name = "type", havingValue = "redis", matchIfMissing=false)
    public RedisClientDetailsService redisClientDetailsService() {
        RedisClientDetailsService clientDetailsService = new RedisClientDetailsService(dataSource);
        clientDetailsService.setRedisTemplate(redisTemplate);
        return clientDetailsService;
    }
}
