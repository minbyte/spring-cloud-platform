package com.mindasoft.cloud.security.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mindasoft.cloud.commons.util.PageUtils;
import com.mindasoft.cloud.commons.util.Query;

import com.mindasoft.cloud.security.dao.ClientDetailsDao;
import com.mindasoft.cloud.security.entity.ClientDetailsEntity;
import com.mindasoft.cloud.security.service.ClientDetailsService;


@Service("clientDetailsServiceImpl")
public class ClientDetailsServiceImpl extends ServiceImpl<ClientDetailsDao, ClientDetailsEntity> implements ClientDetailsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ClientDetailsEntity> page = this.selectPage(
                new Query<ClientDetailsEntity>(params).getPage(),
                new EntityWrapper<ClientDetailsEntity>()
        );
        return new PageUtils(page);
    }

}
