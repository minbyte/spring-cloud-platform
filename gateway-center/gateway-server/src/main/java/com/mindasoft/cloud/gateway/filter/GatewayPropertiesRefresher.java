//package com.mindasoft.cloud.gateway.filter;
//
//import com.ctrip.framework.apollo.model.ConfigChangeEvent;
//import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
//import com.mindasoft.cloud.gateway.util.SpringContextUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
//import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
//import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.context.ApplicationEventPublisherAware;
//import org.springframework.stereotype.Component;
//
//Apollo配置中心动态文件刷新
//@Component
//public class GatewayPropertiesRefresher implements ApplicationEventPublisherAware {
//
//    private static final Logger logger = LoggerFactory.getLogger(GatewayPropertiesRefresher.class);
//
//    @Autowired
//    private RouteDefinitionWriter     routeDefinitionWriter;
//    private ApplicationEventPublisher publisher;
//
//    @ApolloConfigChangeListener
//    public void onChange(ConfigChangeEvent changeEvent) {
//        SpringContextUtils.applicationContext.publishEvent(new EnvironmentChangeEvent(changeEvent.changedKeys()));
//
//        boolean propertiesChanged = false;
//        for (String changedKey : changeEvent.changedKeys()) {
//            if (changedKey.startsWith("spring.cloud.gateway.")) {
//                propertiesChanged = true;
//                break;
//            }
//        }
//        if (propertiesChanged) {
//            logger.info("Refreshing Gateway properties!");
//            this.publisher.publishEvent(new RefreshRoutesEvent(this));
//        }
//    }
//
//    @Override
//    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
//        this.publisher = applicationEventPublisher;
//    }
//}