package com.mindasoft.cloud.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin.server.internal.EnableZipkinServer;

/**
 * 在使用微服务的时候，我们发现，有时候排错不好排查，所以就给大家整个这个链路追踪，很方便知道是哪一个服务调用哪一个服务出现了问题。因为有些项目可能服务比较多
 * @author: huangmin
 * @email: huangmin@mgtv.com
 * @date: 2018/8/14 11:27
 * @version: 1.0.0
 */

@EnableDiscoveryClient
@SpringBootApplication
@EnableZipkinServer
public class MonitorZipkinApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorZipkinApplication.class, args);
    }
}


