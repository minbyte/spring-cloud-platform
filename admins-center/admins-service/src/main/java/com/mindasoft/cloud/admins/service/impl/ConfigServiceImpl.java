package com.mindasoft.cloud.admins.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mindasoft.cloud.admins.dao.ConfigDao;
import com.mindasoft.cloud.admins.entity.ConfigEntity;
import com.mindasoft.cloud.admins.service.ConfigService;
import com.mindasoft.cloud.commons.util.PageUtils;
import com.mindasoft.cloud.commons.util.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("configService")
public class ConfigServiceImpl extends ServiceImpl<ConfigDao, ConfigEntity> implements ConfigService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ConfigEntity> page = this.selectPage(
                new Query<ConfigEntity>(params).getPage(),
                new EntityWrapper<ConfigEntity>()
        );
        return new PageUtils(page);
    }

}
