package com.mindasoft.cloud.admins.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mindasoft.cloud.admins.entity.DictEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 字典表
 * 
 * @author huangmin
 * @email hmiter@sina.com
 * @date 2018-12-28 13:35:34
 */
@Mapper
public interface DictDao extends BaseMapper<DictEntity> {
	
}
