package com.mindasoft.cloud.admins.dao;

import com.mindasoft.cloud.admins.entity.AdminRoleEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
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
}
