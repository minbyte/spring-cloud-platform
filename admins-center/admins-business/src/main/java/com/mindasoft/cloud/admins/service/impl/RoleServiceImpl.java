package com.mindasoft.cloud.admins.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mindasoft.cloud.commons.util.PageUtils;
import com.mindasoft.cloud.commons.util.Query;

import com.mindasoft.cloud.admins.dao.RoleDao;
import com.mindasoft.cloud.admins.entity.RoleEntity;
import com.mindasoft.cloud.admins.service.RoleService;


@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleEntity> implements RoleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<RoleEntity> page = this.selectPage(
                new Query<RoleEntity>(params).getPage(),
                new EntityWrapper<RoleEntity>()
        );
        return new PageUtils(page);
    }

}
