package com.mindasoft.cloud.users.feign;

import com.mindasoft.cloud.models.users.LoginUser;
import com.mindasoft.cloud.users.constants.FeignClientInstances;
import com.mindasoft.cloud.users.fallback.UserServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: min
 * @date: 2019/4/22 15:29
 * @version: 1.0.0
 */
@FeignClient(value = FeignClientInstances.name, fallbackFactory = UserServiceFallbackFactory.class, decode404 = true)
public interface UserFeign {

    @GetMapping(value ="/user/login",params = "account")
    LoginUser login(@RequestParam("account") String account);
}
