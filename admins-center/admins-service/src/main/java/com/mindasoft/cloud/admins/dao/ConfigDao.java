package com.mindasoft.cloud.admins.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mindasoft.cloud.admins.entity.ConfigEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 参数配置
 * 
 * @author huangmin
 * @email hmiter@sina.com
 * @date 2018-12-28 13:35:34
 */
@Mapper
public interface ConfigDao extends BaseMapper<ConfigEntity> {
	
}
