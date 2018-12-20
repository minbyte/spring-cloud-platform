package com.mindasoft.cloud.oauth.config;

import com.mindasoft.cloud.oauth.oauth2.DefaultUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

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

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DefaultUserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {// @formatter:off
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    /**
     * 设置登录链接、登出链接、登陆成功处理器、登录失败处理器等校验规则
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
            .anonymous().disable()
            .cors();

        http.authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS).permitAll()
            .anyRequest().authenticated();

        http.formLogin().and()
            .httpBasic();

//        super.configure(http);
    }

    /**
     * 设置无需验证请求路径
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
