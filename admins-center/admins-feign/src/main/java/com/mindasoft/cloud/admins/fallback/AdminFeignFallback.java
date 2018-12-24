package com.mindasoft.cloud.admins.fallback;

import com.mindasoft.cloud.admins.feign.AdminFeign;
import com.mindasoft.cloud.commons.util.R;
import org.springframework.stereotype.Service;

/**
 * @author: min
 * @date: 2018/12/21 9:25
 * @version: 1.0.0
 */
@Service
public class AdminFeignFallback implements AdminFeign {

    @Override
    public R info(Long adminId) {
        return R.fail();
    }
}
