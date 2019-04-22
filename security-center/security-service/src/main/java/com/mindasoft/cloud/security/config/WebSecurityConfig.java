package com.mindasoft.cloud.security.config;

import com.mindasoft.cloud.security.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.annotation.Resource;

/**
 * 在WebSecurityConfigurerAdapter不拦截oauth要开放的资源
 *
 * @author: huangmin
 * @email: huangmin@mgtv.com
 * @date: 2018/6/13 18:48
 * @version: 1.0.0
 *
 *  使用表达式时间方法级别的安全性 4个注解可用
 *  @PreAuthorize 在方法调用之前,基于表达式的计算结果来限制对方法的访问
 *  @PostAuthorize 允许方法调用,但是如果表达式计算结果为false,将抛出一个安全性异常
 *  @PostFilter 允许方法调用,但必须按照表达式来过滤方法的结果
 *  @PreFilter 允许方法调用,但必须在进入方法之前过滤输入值
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Resource
    private LogoutHandler logoutHandler;

    @Autowired(required = false)
    private AuthenticationEntryPoint authenticationEntryPoint;


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 全局用户信息
     */
    @Autowired
    public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {// @formatter:off
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    /**
     * 设置登录链接、登出链接、登陆成功处理器、登录失败处理器等校验规则
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers(securityProperties.getIgnore().getUrls()).permitAll()
                .anyRequest().authenticated()
//            .and().httpBasic() // 弹对话框输入账号
            .and().formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/user/login")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
            .and().logout()
                .logoutUrl("/oauth/remove/token")
                .logoutSuccessUrl("/login.html")
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                .addLogoutHandler(logoutHandler)
                .clearAuthentication(true)
            .and().csrf().disable()// 禁用 跨站请求伪造
            .headers().frameOptions().disable().cacheControl(); //允许使用iframe 嵌套，避免swagger-ui 不被加载的问题;

        // 基于密码 等模式可以无session,不支持授权码模式
        if (authenticationEntryPoint != null) {
            http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        } else {
            // 授权码模式单独处理，需要session的支持，此模式可以支持所有oauth2的认证
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
        }

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/images/**");
    }



}
