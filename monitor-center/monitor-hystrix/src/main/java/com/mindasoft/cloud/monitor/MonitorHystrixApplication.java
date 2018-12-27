package com.mindasoft.cloud.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * 在
 * @author: huangmin
 * @email: huangmin@mgtv.com
 * @date: 2018/8/13 15:56
 * @version: 1.0.0
 */
@EnableTurbine   //开启Hystrix,提供把多个hystrix.stream的内容聚合为一个数据源供Dashboard展示
@EnableHystrixDashboard // 开启HystrixDashboard展示
@SpringCloudApplication
public class MonitorHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorHystrixApplication.class, args);
    }

}
