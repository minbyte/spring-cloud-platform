package com.mindasoft.cloud.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *
 * [/oauth/authorize] {@link org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint}
 * [/oauth/token] {@link org.springframework.security.oauth2.provider.endpoint.TokenEndpoint}
 * [/oauth/check_token] {@link org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint}
 * [/oauth/confirm_access] {@link org.springframework.security.oauth2.provider.endpoint.WhitelabelApprovalEndpoint}
 * [/oauth/token_key] {@link org.springframework.security.oauth2.provider.endpoint.TokenKeyEndpoint}
 * [/oauth/error] {@link org.springframework.security.oauth2.provider.endpoint.WhitelabelErrorEndpoint}
 *
 *
 * @author: min
 * @date: 2018/12/17 11:10
 * @version: 1.0.0
 */
@EnableCircuitBreaker // 监控
@EnableDiscoveryClient // 服务发现
@EnableFeignClients({"com.mindasoft.cloud.admins","com.mindasoft.cloud.users"}) // 指定加载哪些Feign
@SpringBootApplication(scanBasePackages = {"com.mindasoft.cloud.*"})
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }
}
