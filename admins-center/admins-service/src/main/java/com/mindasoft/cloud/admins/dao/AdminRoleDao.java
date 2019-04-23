package com.mindasoft.cloud.admins.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mindasoft.cloud.admins.entity.AdminRoleEntity;
import com.mindasoft.cloud.admins.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * 用户与角色对应关系
 * 
 * @author huangmin
 * @email hmiter@sina.com
 * @date 2018-12-24 10:01:16
 */
@Mapper
public interface AdminRoleDao extends BaseMapper<AdminRoleEntity> {

    Set<RoleEntity> selectByAdminId(Long adminId);

    List<AdminRoleEntity> selectByRoleId(Long roleId);

    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Long> queryRoleIdList(Long userId);


    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);
}
