package com.mindasoft.cloud.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * 项目在生产环境中，每个服务的访问量都不通，有些服务的访问量比较大，有时候有些服务挂了，不能继续服务，需要重启的时候，我们并不知道，
 * 所以这时候就需要使用hystrix-turbine-dashboard做一个监控，监控所有的微服务，可以看到这个接口实时访问量，和健康状况。
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
