package com.mindasoft.cloud.commons.validator;

import com.mindasoft.cloud.commons.exception.BaseException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 * @author huangmin
 * @email huangmin@sina.com
 * @date 2017-03-23 15:50
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new BaseException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new BaseException(message);
        }
    }
}
