package com.mindasoft.cloud.security.service.impl;

import com.mindasoft.cloud.admins.feign.AdminFeign;
import com.mindasoft.cloud.models.admins.LoginAdmin;
import com.mindasoft.cloud.models.users.LoginUser;
import com.mindasoft.cloud.security.service.AdminDetailsService;
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
 * 管理员信息加载服务
 * @author: hmiter
 * @email: hmiter@sina.com
 * @date: 2019/4/23 16:47
 * @version: 1.0.0
 */
@Component
public class AdminDetailsServiceImpl implements AdminDetailsService {

	private Logger logger = LoggerFactory.getLogger(this.getClass()) ;

	@Resource
	private AdminFeign adminFeign;

	@Override
	public UserDetails loadAdminByUsername(String username) throws UsernameNotFoundException {
		LoginAdmin loginAdmin = adminFeign.login(username);
		if(null == loginAdmin){
			logger.warn("管理员不存在");
			throw new AuthenticationCredentialsNotFoundException("管理员不存在");
		}else if(!loginAdmin.isEnabled()) {
			logger.warn("管理员已禁用");
			throw new DisabledException("管理员已禁用");
		}
		return loginAdmin;
	}
}
