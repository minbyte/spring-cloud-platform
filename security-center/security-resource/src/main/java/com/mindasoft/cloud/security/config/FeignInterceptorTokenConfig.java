package com.mindasoft.cloud.security.config;

import com.mindasoft.cloud.security.util.OAuth2Utils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

/**
 * fegin 服务之间权限调用，不加会报401错误
 * @author: min
 * @date: 2018/12/21 10:55
 * @version: 1.0.0
 */
@ConditionalOnBean({ FeignClientsConfiguration.class})
public class FeignInterceptorTokenConfig implements RequestInterceptor {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String AUTHORIZATION_HEADER = "Authorization";

    /**
     * 使用feign client访问别的微服务时，将access_token放入参数或者header ，Authorization:Bearer xxx
     * 或者url?access_token=xxx
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        String accessToken = OAuth2Utils.getAccessToken();
        if(accessToken != null){
            logger.debug("RequestInterceptorConfig accessToken :" +accessToken);
            requestTemplate.header(AUTHORIZATION_HEADER,String.format("%s %s", OAuth2AccessToken.BEARER_TYPE,accessToken));
        }
    }

}