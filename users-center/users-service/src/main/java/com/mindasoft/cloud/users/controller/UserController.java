package com.mindasoft.cloud.users.controller;

import java.util.Arrays;
import java.util.Map;

import com.mindasoft.cloud.commons.util.IPUtils;
import com.mindasoft.cloud.models.users.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.mindasoft.cloud.users.entity.UserEntity;
import com.mindasoft.cloud.users.service.UserService;
import com.mindasoft.cloud.commons.util.PageUtils;
import com.mindasoft.cloud.commons.util.R;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 用户表
 *
 * @author huangmin
 * @email hmiter@sina.com
 * @date 2018-12-27 14:47:13
 */
@RestController
@RequestMapping("user")
@Api(tags = "用户表api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public LoginUser login(@RequestParam("account") String account, HttpServletRequest request){
        if(!IPUtils.isLAN(request)){
            return null;
        }
        UserEntity userEntity = userService.login(account);
        if(null == userEntity){
            return null;
        }
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(userEntity,loginUser);
        loginUser.setEnabled(true);
        return loginUser;
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('users:user:list')")
    @ApiOperation(value = "列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", paramType="query",dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "limit", value = "每页数目",paramType="query" ,dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "sidx", value = "排序字段", paramType="query" ),
            @ApiImplicitParam(name = "order", value = "排序方式",  paramType="query" )
    })
    public R list(@ApiIgnore @RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPage(params);
        return R.ok().put(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{userId}")
    @PreAuthorize("hasAuthority('users:user:info')")
    @ApiOperation(value = "信息")
    public R info(@PathVariable("userId") Long userId){
        UserEntity user = userService.selectById(userId);
        return R.ok().put(user);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('users:user:save')")
    @ApiOperation(value = "保存")
    public R save(@RequestBody UserEntity user){
        userService.insert(user);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('users:user:update')")
    @ApiOperation(value = "修改")
    public R update(@RequestBody UserEntity user){
        userService.updateById(user);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('users:user:delete')")
    @ApiOperation(value = "删除")
    public R delete(@RequestBody Long[] userIds){
        userService.deleteBatchIds(Arrays.asList(userIds));
        return R.ok();
    }

}
