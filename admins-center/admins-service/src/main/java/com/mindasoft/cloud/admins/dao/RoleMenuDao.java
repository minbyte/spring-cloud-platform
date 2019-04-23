package com.mindasoft.cloud.admins.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mindasoft.cloud.admins.entity.MenuEntity;
import com.mindasoft.cloud.admins.entity.RoleMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 角色与菜单对应关系
 * 
 * @author huangmin
 * @email hmiter@sina.com
 * @date 2018-12-24 10:01:16
 */
@Mapper
public interface RoleMenuDao extends BaseMapper<RoleMenuEntity> {

    Set<MenuEntity> selectByRoleId(@Param("roleIds") Set<Long> roleIds);

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Long> queryMenuIdList(Long roleId);

    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);
	
}
