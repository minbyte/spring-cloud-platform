package com.mindasoft.cloud.admins.service;

import com.baomidou.mybatisplus.service.IService;
import com.mindasoft.cloud.commons.util.PageUtils;
import com.mindasoft.cloud.admins.entity.AdminEntity;

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
}

