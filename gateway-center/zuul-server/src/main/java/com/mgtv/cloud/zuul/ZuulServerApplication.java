package com.mgtv.cloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author: huangmin
 * @email: huangmin@mgtv.com
 * @date: 2018/8/14 17:48
 * @version: 1.0.0
 */
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication()
public class ZuulServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApplication.class, args);
    }
}
