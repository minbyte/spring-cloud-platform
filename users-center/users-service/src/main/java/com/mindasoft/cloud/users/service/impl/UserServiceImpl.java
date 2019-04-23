package com.mindasoft.cloud.users.service.impl;

import com.mindasoft.cloud.commons.util.EmailUtils;
import com.mindasoft.cloud.commons.util.PhoneUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mindasoft.cloud.commons.util.PageUtils;
import com.mindasoft.cloud.commons.util.Query;

import com.mindasoft.cloud.users.dao.UserDao;
import com.mindasoft.cloud.users.entity.UserEntity;
import com.mindasoft.cloud.users.service.UserService;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<UserEntity> page = this.selectPage(
                new Query<UserEntity>(params).getPage(),
                new EntityWrapper<UserEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public UserEntity login(String account) {
        if(PhoneUtils.isPhone(account)){
            return this.selectOne(new EntityWrapper<UserEntity>().eq("mobile",account));
        }else if(EmailUtils.isEmail(account)){
            return this.selectOne(new EntityWrapper<UserEntity>().eq("email",account));
        }else{
            return this.selectOne(new EntityWrapper<UserEntity>().eq("username",account));
        }
    }

}
