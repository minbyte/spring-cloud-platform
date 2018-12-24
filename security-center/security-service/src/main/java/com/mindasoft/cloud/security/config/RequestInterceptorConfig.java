package com.mindasoft.cloud.security.config;

import com.mindasoft.cloud.commons.util.OAuth2Utils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * fegin 服务之间权限调用，不加会报401错误
 * @author: min
 * @date: 2018/12/21 10:55
 * @version: 1.0.0
 */
@Configuration
public class RequestInterceptorConfig implements RequestInterceptor {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String BEARER_TOKEN_TYPE = "Bearer";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String accessToken = OAuth2Utils.getAccessToken();
        if(accessToken != null){
            logger.debug("RequestInterceptorConfig accessToken :" +accessToken);
            requestTemplate.header(AUTHORIZATION_HEADER,String.format("%s %s",BEARER_TOKEN_TYPE,accessToken));
        }
    }

}