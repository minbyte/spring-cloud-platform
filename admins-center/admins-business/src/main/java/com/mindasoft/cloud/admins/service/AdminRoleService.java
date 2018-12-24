package com.mindasoft.cloud.admins.service;

import com.baomidou.mybatisplus.service.IService;
import com.mindasoft.cloud.commons.util.PageUtils;
import com.mindasoft.cloud.admins.entity.AdminRoleEntity;

import java.util.Map;

/**
 * 用户与角色对应关系
 *
 * @author huangmin
 * @email hmiter@sina.com
 * @date 2018-12-24 10:01:16
 */
public interface AdminRoleService extends IService<AdminRoleEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

