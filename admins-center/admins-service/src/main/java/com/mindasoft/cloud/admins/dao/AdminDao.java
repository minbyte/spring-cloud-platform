package com.mindasoft.cloud.admins.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mindasoft.cloud.admins.entity.AdminEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 管理员表
 * 
 * @author huangmin
 * @email hmiter@sina.com
 * @date 2018-12-24 10:01:16
 */
@Mapper
public interface AdminDao extends BaseMapper<AdminEntity> {

    /**
     * 查询用户的所有权限
     * @param adminId  用户ID
     */
    List<String> queryAllPerms(Long adminId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long adminId);


}
