package com.mindasoft.cloud.security.config;

import com.mindasoft.cloud.security.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerSecurityConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * @author: min
 * @date: 2018/12/20 9:27
 * @version: 1.0.0
 */
@Configuration
@ConditionalOnMissingBean(AuthorizationServerSecurityConfiguration.class)
@EnableConfigurationProperties(SecurityProperties.class)
@Import({TokenStoreConfig.class,SecurityHandlerConfig.class,ResourceServerConfiguration.class})
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private OAuth2AccessDeniedHandler oAuth2AccessDeniedHandler;

    @Autowired
    private OAuth2WebSecurityExpressionHandler expressionHandler;

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 配置安全资源的访问规则
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll() // options的请求无需认证
                .antMatchers(securityProperties.getAuth().getPaths()).authenticated() // 指定请求path需要认证
                .antMatchers(securityProperties.getIgnore().getUrls()).permitAll() // 指定请求path无需认证
                .anyRequest().authenticated() // 其他的请求path需要认证
                .and().csrf().disable()// 禁用 跨站请求伪造
                .headers().frameOptions().disable().cacheControl(); //允许使用iframe 嵌套，避免swagger-ui 不被加载的问题;
    }

    /**
     * 添加特定的资源服务器属性
     * @param resources
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore)
                .stateless(true) // 仅允许基于令牌的身份验证
                .authenticationEntryPoint(authenticationEntryPoint)
                .expressionHandler(expressionHandler)
                .accessDeniedHandler(oAuth2AccessDeniedHandler);
    }

}
