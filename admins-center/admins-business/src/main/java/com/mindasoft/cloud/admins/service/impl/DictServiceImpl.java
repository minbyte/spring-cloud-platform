package com.mindasoft.cloud.admins.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mindasoft.cloud.commons.util.PageUtils;
import com.mindasoft.cloud.commons.util.Query;

import com.mindasoft.cloud.admins.dao.DictDao;
import com.mindasoft.cloud.admins.entity.DictEntity;
import com.mindasoft.cloud.admins.service.DictService;


@Service("dictService")
public class DictServiceImpl extends ServiceImpl<DictDao, DictEntity> implements DictService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DictEntity> page = this.selectPage(
                new Query<DictEntity>(params).getPage(),
                new EntityWrapper<DictEntity>()
        );
        return new PageUtils(page);
    }

}
