package com.mindasoft.cloud.admins.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mindasoft.cloud.commons.util.PageUtils;
import com.mindasoft.cloud.commons.util.Query;

import com.mindasoft.cloud.admins.dao.AdminRoleDao;
import com.mindasoft.cloud.admins.entity.AdminRoleEntity;
import com.mindasoft.cloud.admins.service.AdminRoleService;


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

}
