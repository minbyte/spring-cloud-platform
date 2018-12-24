package com.mindasoft.cloud.admins.feign;

import com.mindasoft.cloud.admins.constants.FeignClientInstances;
import com.mindasoft.cloud.admins.fallback.AdminFeignFallback;
import com.mindasoft.cloud.commons.util.R;
import com.mindasoft.cloud.models.LoginPerson;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: min
 * @date: 2018/12/21 9:25
 * @version: 1.0.0
 */
@FeignClient(value = FeignClientInstances.name,fallback = AdminFeignFallback.class)
public interface AdminFeign {

    @GetMapping(value ="/admin/login",params = "username")
    public R<LoginPerson> loginInfo(@RequestParam("username") String username);

}
