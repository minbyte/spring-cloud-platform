package com.mindasoft.cloud.admins.service;

import com.baomidou.mybatisplus.service.IService;
import com.mindasoft.cloud.commons.util.PageUtils;
import com.mindasoft.cloud.admins.entity.DictEntity;

import java.util.Map;

/**
 * 字典表
 *
 * @author huangmin
 * @email hmiter@sina.com
 * @date 2018-12-28 13:35:34
 */
public interface DictService extends IService<DictEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

