package com.mindasoft.cloud.security.oauth2;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2RequestAuthenticator;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

/**
 * @author: min
 * @date: 2018/12/21 10:55
 * @version: 1.0.0
 */
@Configuration
public class RequestInterceptorConfig implements RequestInterceptor {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String BEARER_TOKEN_TYPE = "Bearer";

    @Autowired
    private OAuth2RestTemplate oAuth2RestTemplate;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String accessToken = OAuth2Utils.getAccessToken();
        if(accessToken == null){
            accessToken =oAuth2RestTemplate.getAccessToken().getValue();
        }
        logger.debug("RequestInterceptorConfig accessToken :" +accessToken);
        requestTemplate.header(AUTHORIZATION_HEADER,String.format("%s %s",BEARER_TOKEN_TYPE,accessToken));
    }

    @Bean
    public OAuth2RestTemplate oauth2RestTemplate() {
        ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
        resourceDetails.setClientId("app");
        resourceDetails.setClientSecret("app");
        resourceDetails.setId("security-service");
        resourceDetails.setAccessTokenUri("http://localhost:7010/security/oauth/token");
        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(resourceDetails);
        oAuth2RestTemplate.setAuthenticator(new DefaultOAuth2RequestAuthenticator());
        return oAuth2RestTemplate;
    }
}