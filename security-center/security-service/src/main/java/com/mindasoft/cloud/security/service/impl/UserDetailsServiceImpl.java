package com.mindasoft.cloud.security.service.impl;

import com.mindasoft.cloud.models.users.LoginUser;
import com.mindasoft.cloud.users.feign.UserFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * 用户信息加载服务
 * @author: hmiter
 * @email: hmiter@sina.com
 * @date: 2018/6/14 9:52
 * @version: 1.0.0
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(this.getClass()) ;

	@Resource
	private PasswordEncoder passwordEncoder ;

	@Resource
	private UserFeign userFeign;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LoginUser loginUser = userFeign.login(username);
		if(null == loginUser){
			logger.warn("用户不存在");
			throw new AuthenticationCredentialsNotFoundException("用户不存在");
		}else if(!loginUser.isEnabled()) {
			logger.warn("用户已禁用");
			throw new DisabledException("用户已禁用");
		}
		return loginUser;
	}

}
