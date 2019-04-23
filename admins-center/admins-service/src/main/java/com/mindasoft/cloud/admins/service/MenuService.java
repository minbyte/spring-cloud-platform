package com.mindasoft.cloud.admins.service;

import com.baomidou.mybatisplus.service.IService;
import com.mindasoft.cloud.admins.entity.MenuEntity;
import com.mindasoft.cloud.commons.util.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 菜单管理
 *
 * @author huangmin
 * @email hmiter@sina.com
 * @date 2018-12-24 10:01:16
 */
public interface MenuService extends IService<MenuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     * @param menuIdList  用户菜单ID
     */
    List<MenuEntity> queryListParentId(Long parentId, List<Long> menuIdList);

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<MenuEntity> queryListParentId(Long parentId);


    /**
     * 获取不包含按钮的菜单列表
     */
    List<MenuEntity> queryNotButtonList();

    /**
     * 获取用户菜单列表
     */
    List<MenuEntity> getAdminMenuList(Long adminId);

    /**
     * 删除
     */
    void delete(Long menuId);
}

