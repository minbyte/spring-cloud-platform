package com.mindasoft.cloud.commons.util;

import com.mindasoft.cloud.models.LoginPerson;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: min
 * @date: 2018/12/21 10:58
 * @version: 1.0.0
 */
public class OAuth2Utils {

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

    public static LoginPerson getLoginPerson() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2Authentication) {
            OAuth2Authentication oAuth2Auth = (OAuth2Authentication) authentication;
            authentication = oAuth2Auth.getUserAuthentication();
            if (authentication instanceof UsernamePasswordAuthenticationToken || authentication instanceof PreAuthenticatedAuthenticationToken){
                LinkedHashMap authenticationToken = (LinkedHashMap) authentication.getDetails();
                LinkedHashMap principal = (LinkedHashMap)authenticationToken.get("principal");
                LoginPerson loginPerson = new LoginPerson();
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
     * 获取当前登录用户的ID
     * @return
     */
    public static Long getId(){
        return getLoginPerson().getAdminId();
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
}
