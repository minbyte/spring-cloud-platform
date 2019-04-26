package com.mindasoft.cloud.security.config;

import com.mindasoft.cloud.security.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.AntPathMatcher;

/**
 * @author: min
 * @date: 2019/4/19 17:38
 * @version: 1.0.0
 */
@Configuration
@EnableResourceServer
@EnableConfigurationProperties(SecurityProperties.class)
@Import(TokenStoreConfig.class)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //允许使用iframe 嵌套，避免swagger-ui 不被加载的问题
        http.headers().frameOptions().disable()
            .and().requestMatcher(request -> {
                // 判断请求来源，允许匹配的请求不需要HttpSecurity保护

                if (request.getParameter(OAuth2AccessToken.ACCESS_TOKEN) != null) {
                    // 请求参数中包含access_token参数
                    return true;
                }

                String auth = request.getHeader("Authorization");
                if (auth != null && auth.startsWith(OAuth2AccessToken.BEARER_TYPE)) {
                    // 头部的Authorization值以Bearer开头
                        return true;
                }

                if (antPathMatcher.match("/clients/**",  request.getRequestURI())) {
                    return true;
                }

                return false;
            }).authorizeRequests()
            .antMatchers(securityProperties.getIgnore().getUrls()).permitAll()
            .anyRequest().authenticated();
    }
}
