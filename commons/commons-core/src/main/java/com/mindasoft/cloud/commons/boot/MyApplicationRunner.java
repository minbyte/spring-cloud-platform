package com.mindasoft.cloud.commons.boot;

import com.mindasoft.cloud.commons.util.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author: huangmin
 * @email: huangmin@mgtv.com
 * @date: 2018/8/13 10:00
 * @version: 1.0.0
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Value("${spring.application.name}")
    private String app_name;

    @Override
    public void run(ApplicationArguments applicationArguments) {
        System.out.println("#################################################################################");
        System.out.println("###########       " + app_name +"项目" + DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN)+ " 启动完成" +"          ###########");
        System.out.println("#################################################################################");
    }

}