package com.mindasoft.cloud.admins.service;

import com.baomidou.mybatisplus.service.IService;
import com.mindasoft.cloud.commons.util.PageUtils;
import com.mindasoft.cloud.admins.entity.AdminEntity;
import com.mindasoft.cloud.models.LoginPerson;

import java.util.List;
import java.util.Map;

/**
 * 管理员表
 *
 * @author huangmin
 * @email hmiter@sina.com
 * @date 2018-12-24 10:01:16
 */
public interface AdminService extends IService<AdminEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据用户名，查询系统用户
     * @param username
     * @return
     */
    LoginPerson getLoginPerson(String username);


    /**
     * 查询用户的所有权限
     * @param adminId  管理员ID
     */
    List<String> queryAllPerms(Long adminId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long adminId);

    /**
     * 修改密码
     * @param adminId      管理员ID
     * @param password     原密码
     * @param newPassword  新密码
     */
    boolean updatePassword(Long adminId, String password, String newPassword);
}

