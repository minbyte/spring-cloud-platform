package com.mindasoft.cloud.admins.service;

import com.baomidou.mybatisplus.service.IService;
import com.mindasoft.cloud.commons.util.PageUtils;
import com.mindasoft.cloud.admins.entity.AdminRoleEntity;

import java.util.List;
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

    void saveOrUpdate(Long adminId, List<Long> roleIdList);
    /**
     * 根据管理员ID，获取角色ID列表
     */
    List<Long> queryRoleIdList(Long adminId);

    /**
     * 根据角色ID数组，批量删除管理员
     */
    int deleteBatch(Long[] roleIds);
}

