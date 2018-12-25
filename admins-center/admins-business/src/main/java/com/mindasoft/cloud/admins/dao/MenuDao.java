package com.mindasoft.cloud.admins.dao;

import com.mindasoft.cloud.admins.entity.MenuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单管理
 * 
 * @author huangmin
 * @email hmiter@sina.com
 * @date 2018-12-24 10:01:16
 */
@Mapper
public interface MenuDao extends BaseMapper<MenuEntity> {

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<MenuEntity> queryListParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<MenuEntity> queryNotButtonList();
	
}
