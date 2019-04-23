package com.mindasoft.cloud.users.fallback;

import com.mindasoft.cloud.models.users.LoginUser;
import com.mindasoft.cloud.users.feign.UserFeign;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: min
 * @date: 2019/4/23 9:19
 * @version: 1.0.0
 */
@Slf4j
@Component
public class UserServiceFallbackFactory implements FallbackFactory<UserFeign> {

    @Override
    public UserFeign create(Throwable throwable) {
        return new UserFeign() {
            @Override
            public LoginUser login(String account) {
                log.error("通过账号查询用户异常:{}", account, throwable);
                return null;
            }
        };
    }
}
