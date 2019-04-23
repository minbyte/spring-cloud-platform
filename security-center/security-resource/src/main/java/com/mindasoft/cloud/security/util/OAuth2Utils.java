package com.mindasoft.cloud.security.util;

import com.mindasoft.cloud.models.admins.LoginAdmin;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Enumeration;
import java.util.LinkedHashMap;

/**
 * @author: min
 * @date: 2018/12/21 10:58
 * @version: 1.0.0
 */
@Slf4j
public class OAuth2Utils {

    private static final String BASIC_ = "Basic ";

    /**
     * 获取当前上下文授权信息
     * @return
     */
    public static Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication;
        }
        throw  new AuthenticationServiceException("authentication not found");
    }

    /**
     * 获取当前上下文token的信息
     * @return
     */
    public static OAuth2AuthenticationDetails getDetails(){
        Authentication authentication = getAuthentication();
        if(authentication == null){
            return null;
        }
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        return details;
    }

    public static LoginAdmin getLoginAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2Authentication) {
            OAuth2Authentication oAuth2Auth = (OAuth2Authentication) authentication;
            authentication = oAuth2Auth.getUserAuthentication();
            if (authentication instanceof UsernamePasswordAuthenticationToken || authentication instanceof PreAuthenticatedAuthenticationToken){
                LinkedHashMap authenticationToken = (LinkedHashMap) authentication.getDetails();
                LinkedHashMap principal = (LinkedHashMap)authenticationToken.get("principal");
                LoginAdmin loginPerson = new LoginAdmin();
                try {
                    BeanUtils.populate(loginPerson,principal);
                    loginPerson.setEnabled((Boolean) principal.get("enabled"));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                return loginPerson ;
            }
        }
        return null;
    }

    /**
     * 获取当前登入用户账号
     * @return
     */
    public static String getUsername(){
        return getAuthentication().getName();
    }

    /**
     * 获取当前登录管理员ID
     * @return
     */
    public static Long getAdminId(){
        return getLoginAdmin().getAdminId();
    }

    /**
     *  获取当前登入用户的访问accessToken
     * @return
     */
    public static String getAccessToken(){
        if(getDetails() == null){
            return null;
        }
        return getDetails().getTokenValue();
    }

    public static String extractToken(HttpServletRequest request) {
        String token = extractHeaderToken(request);
        if (token == null) {
            token = request.getParameter(OAuth2AccessToken.ACCESS_TOKEN);
            if (token == null) {
                log.debug("Token not found in request parameters.  Not an OAuth2 request.");
            }
        }
        return token;
    }

    /**
     * 解析head中的token
     * @param request
     * @return
     */
    private static String extractHeaderToken(HttpServletRequest request) {
        Enumeration<String> headers = request.getHeaders("Authorization");
        while (headers.hasMoreElements()) {
            String value = headers.nextElement();
            if ((value.toLowerCase().startsWith(OAuth2AccessToken.BEARER_TYPE.toLowerCase()))) {
                String authHeaderValue = value.substring(OAuth2AccessToken.BEARER_TYPE.length()).trim();
                int commaIndex = authHeaderValue.indexOf(',');
                if (commaIndex > 0) {
                    authHeaderValue = authHeaderValue.substring(0, commaIndex);
                }
                return authHeaderValue;
            }
        }
        return null;
    }

    /**
     * *从header 请求中的clientId:clientSecret
     */
    public static String[] extractClient(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith(BASIC_)) {
            throw new UnapprovedClientAuthenticationException("请求头中client信息为空");
        }
        return extractHeaderClient(header);
    }

    /**
     * 从header 请求中的clientId:clientSecret
     *
     * @param header header中的参数
     */
    public static String[] extractHeaderClient(String header) {
        byte[] base64Client = header.substring(BASIC_.length()).getBytes(StandardCharsets.UTF_8);
        byte[] decoded = Base64.getDecoder().decode(base64Client);
        String clientStr = new String(decoded, StandardCharsets.UTF_8);
        String[] clientArr = clientStr.split(":");
        if (clientArr.length != 2) {
            throw new RuntimeException("Invalid basic authentication token");
        }
        return clientArr;
    }
}
