package com.mindasoft.cloud.security.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 认证配置
 *
 * @author zlt
 */
@Setter
@Getter
public class AuthProperties {
    /**
     * 要认证的url
     */
    private String[] paths = {};

    /**
     * 是否开启url权限验证
     */
    private boolean enabled = false;
}
