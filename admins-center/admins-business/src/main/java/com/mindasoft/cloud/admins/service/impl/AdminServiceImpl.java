package com.mindasoft.cloud.admins.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mindasoft.cloud.commons.util.PageUtils;
import com.mindasoft.cloud.commons.util.Query;

import com.mindasoft.cloud.admins.dao.AdminDao;
import com.mindasoft.cloud.admins.entity.AdminEntity;
import com.mindasoft.cloud.admins.service.AdminService;


@Service("adminService")
public class AdminServiceImpl extends ServiceImpl<AdminDao, AdminEntity> implements AdminService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AdminEntity> page = this.selectPage(
                new Query<AdminEntity>(params).getPage(),
                new EntityWrapper<AdminEntity>()
        );
        return new PageUtils(page);
    }

}
