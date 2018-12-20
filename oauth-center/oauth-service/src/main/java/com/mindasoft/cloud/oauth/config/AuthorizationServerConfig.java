package com.mindasoft.cloud.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;


/**
 * @author: min
 * @date: 2018/10/22 18:55
 * @version: 1.0.0
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * client客户端的信息配置，配置客户端详情信息(内存或JDBC来实现)
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory() // 使用in-memory存储
                .withClient("console") // client_id
                .secret("console") // client_secret
                .scopes("web")  // 允许的授权范围
                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit") // 该client允许的授权类型 "password", "authorization_code", "refresh_token", "implicit"
                .accessTokenValiditySeconds(3600);// 1 hour
//
//                .and()
//                .withClient("app") // client_id
//                .secret("app_android") // client_secret
//                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit") // 该client允许的授权类型 "password", "authorization_code", "refresh_token", "implicit"
//                .scopes("app");
    }

    /**
     * 声明授权和token的端点以及token的服务的一些配置信息，比如采用什么存储方式、token的有效期等
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager);
    }

    /**
     * 声明安全约束，哪些允许访问，哪些不允许访问
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //curl -i -X POST -H "Accept: application/json" -u "client_1:123456" http://localhost:5000/oauth/check_token?token=a1478d56-ebb8-4f21-b4b6-8a9602df24ec
        security.tokenKeyAccess("permitAll()")  // url:/oauth/token_key,exposes public key for token verification if using JWT tokens 如果使用JWT令牌，则公开用于令牌验证的公钥
                .checkTokenAccess("isAuthenticated()") // url:/oauth/check_token allow check token 允许检查令牌
                .allowFormAuthenticationForClients();
    }



    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

}
