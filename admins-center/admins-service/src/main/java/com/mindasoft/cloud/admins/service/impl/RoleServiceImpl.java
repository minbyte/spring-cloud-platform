package com.mindasoft.cloud.admins.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mindasoft.cloud.admins.constants.Constant;
import com.mindasoft.cloud.admins.dao.RoleDao;
import com.mindasoft.cloud.admins.entity.RoleEntity;
import com.mindasoft.cloud.admins.service.AdminRoleService;
import com.mindasoft.cloud.admins.service.AdminService;
import com.mindasoft.cloud.admins.service.RoleMenuService;
import com.mindasoft.cloud.admins.service.RoleService;
import com.mindasoft.cloud.commons.exception.BaseException;
import com.mindasoft.cloud.commons.util.PageUtils;
import com.mindasoft.cloud.commons.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleEntity> implements RoleService {
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminRoleService adminRoleService;
    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<RoleEntity> page = this.selectPage(
                new Query<RoleEntity>(params).getPage(),
                new EntityWrapper<RoleEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public void save(RoleEntity role) {
        this.insert(role);

        //检查权限是否越权
        checkPrems(role);

        //保存角色与菜单关系
        roleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    public void update(RoleEntity role) {
        this.updateById(role);

        //检查权限是否越权
        checkPrems(role);

        //更新角色与菜单关系
        roleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    public void deleteBatch(Long[] roleIds) {
        //删除角色
        this.deleteBatchIds(Arrays.asList(roleIds));

        //删除角色与菜单关联
        roleMenuService.deleteBatch(roleIds);

        //删除角色与用户关联
//        adminRoleService.deleteBatch(roleIds);
    }

    @Override
    public List<Long> queryRoleIdList(Long createAdminId) {
        return baseMapper.queryRoleIdList(createAdminId);
    }

    /**
     * 检查权限是否越权
     */
    private void checkPrems(RoleEntity role){
        //如果不是超级管理员，则需要判断角色的权限是否超过自己的权限
        if(role.getCreateAdminId() == Constant.SUPER_ADMIN){
            return ;
        }

        //查询用户所拥有的菜单列表
        List<Long> menuIdList = adminService.queryAllMenuId(role.getCreateAdminId());

        //判断是否越权
        if(!menuIdList.containsAll(role.getMenuIdList())){
            throw new BaseException("新增角色的权限，已超出你的权限范围");
        }
    }

}
