package com.mindasoft.cloud.admins.service.impl;

import com.mindasoft.cloud.admins.constants.Constant;
import com.mindasoft.cloud.admins.dao.AdminRoleDao;
import com.mindasoft.cloud.admins.dao.MenuDao;
import com.mindasoft.cloud.admins.dao.RoleMenuDao;
import com.mindasoft.cloud.admins.entity.MenuEntity;
import com.mindasoft.cloud.admins.entity.RoleEntity;
import com.mindasoft.cloud.admins.service.AdminRoleService;
import com.mindasoft.cloud.admins.service.RoleService;
import com.mindasoft.cloud.commons.exception.BaseException;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


@Service("adminService")
public class AdminServiceImpl extends ServiceImpl<AdminDao, AdminEntity> implements AdminService {

    @Autowired
    private AdminRoleDao adminRoleDao;
    @Autowired
    private RoleMenuDao roleMenuDao;
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private AdminRoleService adminRoleService;
    @Autowired
    private RoleService roleService;


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

    @Override
    public List<String> queryAllPerms(Long adminId) {
        return baseMapper.queryAllPerms(adminId);
    }

    @Override
    public List<Long> queryAllMenuId(Long adminId) {
        return baseMapper.queryAllMenuId(adminId);
    }

    @Override
    @Transactional
    public void save(AdminEntity admin) {
        //sha256加密
//        String salt = RandomStringUtils.randomAlphanumeric(20);
//        user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
//        user.setSalt(salt);
        this.insert(admin);

        //检查角色是否越权
        checkRole(admin);

        //保存用户与角色关系
        adminRoleService.saveOrUpdate(admin.getAdminId(), admin.getRoleIdList());
    }

    @Override
    @Transactional
    public void update(AdminEntity admin) {
        if(StringUtils.isBlank(admin.getPassword())){
            admin.setPassword(null);
        }else{
//            admin.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
        }
        this.updateById(admin);

        //检查角色是否越权
        checkRole(admin);

        //保存用户与角色关系
        adminRoleService.saveOrUpdate(admin.getAdminId(), admin.getRoleIdList());
    }

    @Override
    public boolean updatePassword(Long adminId, String password, String newPassword) {
        return false;
    }

    /**
     * 检查角色是否越权
     */
    private void checkRole(AdminEntity admin){
        if(admin.getRoleIdList() == null || admin.getRoleIdList().size() == 0){
            return;
        }
        //如果不是超级管理员，则需要判断用户的角色是否自己创建
        if(admin.getCreateAdminId() == Constant.SUPER_ADMIN){
            return ;
        }

        //查询用户创建的角色列表
        List<Long> roleIdList = roleService.queryRoleIdList(admin.getCreateAdminId());

        //判断是否越权
        if(!roleIdList.containsAll(admin.getRoleIdList())){
            throw new BaseException("新增用户所选角色，不是本人创建");
        }
    }
}
