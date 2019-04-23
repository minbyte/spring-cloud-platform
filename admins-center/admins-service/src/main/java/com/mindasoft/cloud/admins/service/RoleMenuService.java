package com.mindasoft.cloud.admins.service;

import com.baomidou.mybatisplus.service.IService;
import com.mindasoft.cloud.admins.entity.RoleMenuEntity;
import com.mindasoft.cloud.commons.util.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 角色与菜单对应关系
 *
 * @author huangmin
 * @email hmiter@sina.com
 * @date 2018-12-24 10:01:16
 */
public interface RoleMenuService extends IService<RoleMenuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveOrUpdate(Long roleId, List<Long> menuIdList);

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Long> queryMenuIdList(Long roleId);

    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);
}

