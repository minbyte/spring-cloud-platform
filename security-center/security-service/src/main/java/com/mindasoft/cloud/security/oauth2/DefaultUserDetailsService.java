package com.mindasoft.cloud.security.oauth2;

import com.alibaba.fastjson.JSON;
import com.mindasoft.cloud.admins.feign.AdminFeign;
import com.mindasoft.cloud.commons.util.R;
import com.mindasoft.cloud.models.LoginPerson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.DisabledException;
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

	@Resource
	private AdminFeign adminFeign;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		R<LoginPerson> res = adminFeign.loginInfo(username);
		System.out.println(JSON.toJSONString(res));
		if(res.isOk()){
			return res.getData();
		}else{
			if (res.getData() == null) {
				throw new AuthenticationCredentialsNotFoundException("用户不存在");
			} else if (!res.getData().isEnabled()) {
				throw new DisabledException("用户已作废");
			}
		}
		return null;
	}

}
