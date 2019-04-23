package com.mindasoft.cloud.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 查询管理员
 * @author: min
 * @date: 2019/4/23 16:45
 * @version: 1.0.0
 */
public interface AdminDetailsService {

    public UserDetails loadAdminByUsername(String username) throws UsernameNotFoundException ;
}
