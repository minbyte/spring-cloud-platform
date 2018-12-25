package com.mindasoft.cloud.admins.service;

import com.baomidou.mybatisplus.service.IService;
import com.mindasoft.cloud.commons.util.PageUtils;
import com.mindasoft.cloud.admins.entity.RoleEntity;

import java.util.List;
import java.util.Map;

/**
 * 角色
 *
 * @author huangmin
 * @email hmiter@sina.com
 * @date 2018-12-24 10:01:16
 */
public interface RoleService extends IService<RoleEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void save(RoleEntity role);

    void update(RoleEntity role);

    void deleteBatch(Long[] roleIds);

    /**
     * 查询管理员创建的角色ID列表
     */
    List<Long> queryRoleIdList(Long createAdminId);
}

