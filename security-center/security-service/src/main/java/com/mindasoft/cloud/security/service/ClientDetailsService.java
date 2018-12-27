package com.mindasoft.cloud.security.service;

import com.baomidou.mybatisplus.service.IService;
import com.mindasoft.cloud.commons.util.PageUtils;
import com.mindasoft.cloud.security.entity.ClientDetailsEntity;

import java.util.Map;

/**
 * 客户端应用
 *
 * @author huangmin
 * @email hmiter@sina.com
 * @date 2018-12-27 12:57:15
 */
public interface ClientDetailsService extends IService<ClientDetailsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

