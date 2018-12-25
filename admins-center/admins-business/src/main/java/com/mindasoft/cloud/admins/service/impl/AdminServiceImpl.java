package com.mindasoft.cloud.admins.service.impl;

import com.mindasoft.cloud.admins.dao.AdminRoleDao;
import com.mindasoft.cloud.admins.dao.MenuDao;
import com.mindasoft.cloud.admins.dao.RoleMenuDao;
import com.mindasoft.cloud.admins.entity.MenuEntity;
import com.mindasoft.cloud.admins.entity.RoleEntity;
import com.mindasoft.cloud.models.LoginPerson;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mindasoft.cloud.commons.util.PageUtils;
import com.mindasoft.cloud.commons.util.Query;

import com.mindasoft.cloud.admins.dao.AdminDao;
import com.mindasoft.cloud.admins.entity.AdminEntity;
import com.mindasoft.cloud.admins.service.AdminService;
import org.springframework.util.CollectionUtils;


@Service("adminService")
public class AdminServiceImpl extends ServiceImpl<AdminDao, AdminEntity> implements AdminService {

    @Autowired
    private AdminRoleDao adminRoleDao;
    @Autowired
    private RoleMenuDao roleMenuDao;
    @Autowired
    private MenuDao menuDao;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AdminEntity> page = this.selectPage(
                new Query<AdminEntity>(params).getPage(),
                new EntityWrapper<AdminEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public LoginPerson getLoginPerson(String username) {
        AdminEntity adminEntity = this.selectOne(new EntityWrapper<AdminEntity>().eq("username",username));
        if (adminEntity != null) {
            LoginPerson loginPerson = new LoginPerson();
            BeanUtils.copyProperties(adminEntity, loginPerson);

            if(1 == adminEntity.getAdminId()){
                loginPerson.setRoles(new HashSet<String>(){{add("admin");}});// 设置角色集合

                List<MenuEntity> menuEntityList = menuDao.selectByMap(null);
                Set<String> permissions = menuEntityList.parallelStream().map(m -> m.getPerms()).collect(Collectors.toSet());
                permissions.remove(null);
                loginPerson.setPermissions(permissions);// 设置权限集合
                return loginPerson;
            }

            Set<RoleEntity> sysRoles = adminRoleDao.selectByAdminId(adminEntity.getAdminId());
            if (!CollectionUtils.isEmpty(sysRoles)) {
                Set<String> set = sysRoles.parallelStream().map(role -> role.getRoleId()+"").collect(Collectors.toSet());
                loginPerson.setRoles(set);// 设置角色集合
            }

            if (!CollectionUtils.isEmpty(sysRoles)) {
                Set<Long> roleIds = sysRoles.parallelStream().map(r -> r.getRoleId()).collect(Collectors.toSet());
                Set<MenuEntity> menuEntitySet = roleMenuDao.selectByRoleId(roleIds);
                if (!CollectionUtils.isEmpty(menuEntitySet)) {
                    Set<String> permissions = menuEntitySet.parallelStream().map(p -> p.getPerms()).collect(Collectors.toSet());
                    permissions.remove(null);
                    loginPerson.setPermissions(permissions);// 设置权限集合
                }
            }
            return loginPerson;
        }
        return null;
    }
}
