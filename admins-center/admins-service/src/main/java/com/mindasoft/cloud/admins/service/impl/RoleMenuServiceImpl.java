package com.mindasoft.cloud.admins.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mindasoft.cloud.admins.dao.RoleMenuDao;
import com.mindasoft.cloud.admins.entity.RoleMenuEntity;
import com.mindasoft.cloud.admins.service.RoleMenuService;
import com.mindasoft.cloud.commons.util.PageUtils;
import com.mindasoft.cloud.commons.util.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("roleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuDao, RoleMenuEntity> implements RoleMenuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<RoleMenuEntity> page = this.selectPage(
                new Query<RoleMenuEntity>(params).getPage(),
                new EntityWrapper<RoleMenuEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
        //先删除角色与菜单关系
        deleteBatch(new Long[]{roleId});

        if(menuIdList.size() == 0){
            return ;
        }

        //保存角色与菜单关系
        List<RoleMenuEntity> list = new ArrayList<>(menuIdList.size());
        for(Long menuId : menuIdList){
            RoleMenuEntity roleMenuEntity = new RoleMenuEntity();
            roleMenuEntity.setMenuId(menuId);
            roleMenuEntity.setRoleId(roleId);

            list.add(roleMenuEntity);
        }
        this.insertBatch(list);
    }

    @Override
    public List<Long> queryMenuIdList(Long roleId) {
        return baseMapper.queryMenuIdList(roleId);
    }

    @Override
    public int deleteBatch(Long[] roleIds){
        return baseMapper.deleteBatch(roleIds);
    }

}
