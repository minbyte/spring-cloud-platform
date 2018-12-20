package com.mindasoft.cloud.oauth.oauth2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
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
public class DefaultUserDetailsService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(this.getClass()) ;

	@Resource
	private PasswordEncoder passwordEncoder ;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("页面输入的用户名："+username);
		String password = passwordEncoder.encode("admin") ;
		System.out.println("#########" + password);

		//模拟从数据库中查询得到的
		User user = new User(username,password,	AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER,ROLE_ADMIN"));
		return user;
	}

}
