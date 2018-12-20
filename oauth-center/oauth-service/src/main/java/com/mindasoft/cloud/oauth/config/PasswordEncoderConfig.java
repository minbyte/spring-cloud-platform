package com.mindasoft.cloud.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

/**
 * @author: min
 * @date: 2018/12/20 16:09
 * @version: 1.0.0
 */
@Configuration
public class PasswordEncoderConfig {

    /**
     * NoOpPasswordEncoder 不可用
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder (){
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return Objects.equals(charSequence.toString(),s);
            }
        };
    }

//    @Bean
//    public PasswordEncoder passwordEncoder (){
//        return new BCryptPasswordEncoder();
//    }
}
