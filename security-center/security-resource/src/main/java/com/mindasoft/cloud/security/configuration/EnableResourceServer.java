package com.mindasoft.cloud.security.configuration;

import com.mindasoft.cloud.security.config.ResourceServerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author: min
 * @date: 2019/4/19 17:51
 * @version: 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ResourceServerConfig.class)
public @interface EnableResourceServer {

}
