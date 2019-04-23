package com.mindasoft.cloud.admins.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mindasoft.cloud.admins.dao.AdminRoleDao;
import com.mindasoft.cloud.admins.entity.AdminRoleEntity;
import com.mindasoft.cloud.admins.service.AdminRoleService;
import com.mindasoft.cloud.commons.util.MapUtils;
import com.mindasoft.cloud.commons.util.PageUtils;
import com.mindasoft.cloud.commons.util.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("adminRoleService")
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleDao, AdminRoleEntity> implements AdminRoleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AdminRoleEntity> page = this.selectPage(
                new Query<AdminRoleEntity>(params).getPage(),
                new EntityWrapper<AdminRoleEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public void saveOrUpdate(Long adminId, List<Long> roleIdList) {
        //先删除用户与角色关系
        this.deleteByMap(new MapUtils().put("admin_id", adminId));

        if(roleIdList == null || roleIdList.size() == 0){
            return ;
        }

        //保存用户与角色关系
        List<AdminRoleEntity> list = new ArrayList<>(roleIdList.size());
        for(Long roleId : roleIdList){
            AdminRoleEntity sysUserRoleEntity = new AdminRoleEntity();
            sysUserRoleEntity.setAdminId(adminId);
            sysUserRoleEntity.setRoleId(roleId);

            list.add(sysUserRoleEntity);
        }
        this.insertBatch(list);
    }

    @Override
    public List<Long> queryRoleIdList(Long adminId) {
        return baseMapper.queryRoleIdList(adminId);
    }

    @Override
    public int deleteBatch(Long[] roleIds) {
        return baseMapper.deleteBatch(roleIds);
    }

}
