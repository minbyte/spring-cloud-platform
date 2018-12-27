package com.mindasoft.cloud.users.service;

import com.baomidou.mybatisplus.service.IService;
import com.mindasoft.cloud.commons.util.PageUtils;
import com.mindasoft.cloud.users.entity.UserEntity;

import java.util.Map;

/**
 * 用户表
 *
 * @author huangmin
 * @email hmiter@sina.com
 * @date 2018-12-27 14:47:13
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

