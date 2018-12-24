package com.mindasoft.cloud.admins.dao;

import com.mindasoft.cloud.admins.entity.MenuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜单管理
 * 
 * @author huangmin
 * @email hmiter@sina.com
 * @date 2018-12-24 10:01:16
 */
@Mapper
public interface MenuDao extends BaseMapper<MenuEntity> {
	
}
