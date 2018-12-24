package com.mindasoft.cloud.admins.dao;

import com.mindasoft.cloud.admins.entity.AdminEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理员表
 * 
 * @author huangmin
 * @email hmiter@sina.com
 * @date 2018-12-24 10:01:16
 */
@Mapper
public interface AdminDao extends BaseMapper<AdminEntity> {
	
}
