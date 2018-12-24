package com.mindasoft.cloud.admins.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mindasoft.cloud.commons.util.PageUtils;
import com.mindasoft.cloud.commons.util.Query;

import com.mindasoft.cloud.admins.dao.RoleMenuDao;
import com.mindasoft.cloud.admins.entity.RoleMenuEntity;
import com.mindasoft.cloud.admins.service.RoleMenuService;


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

}
