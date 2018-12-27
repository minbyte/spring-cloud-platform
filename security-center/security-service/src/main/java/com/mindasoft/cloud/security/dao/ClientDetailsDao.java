package com.mindasoft.cloud.security.dao;

import com.mindasoft.cloud.security.entity.ClientDetailsEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户端应用
 * 
 * @author huangmin
 * @email hmiter@sina.com
 * @date 2018-12-27 12:57:15
 */
@Mapper
public interface ClientDetailsDao extends BaseMapper<ClientDetailsEntity> {
	
}
