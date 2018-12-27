package com.mindasoft.cloud.users.dao;

import com.mindasoft.cloud.users.entity.UserEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表
 * 
 * @author huangmin
 * @email hmiter@sina.com
 * @date 2018-12-27 14:47:13
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}
