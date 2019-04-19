package com.mindasoft.cloud.users.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mindasoft.cloud.users.entity.UserEntity;
import com.mindasoft.cloud.users.service.UserService;
import com.mindasoft.cloud.commons.util.PageUtils;
import com.mindasoft.cloud.commons.util.R;



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

    /**
     * 列表
     */
    @GetMapping("/list")
//    @PreAuthorize("hasAuthority('users:user:list')")
    @ApiOperation(value = "列表")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPage(params);
        return R.ok().put(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{userId}")
//    @PreAuthorize("hasAuthority('users:user:info')")
    @ApiOperation(value = "信息")
    public R info(@PathVariable("userId") Integer userId){
        UserEntity user = userService.selectById(userId);
        return R.ok().put(user);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
//    @PreAuthorize("hasAuthority('users:user:save')")
    @ApiOperation(value = "保存")
    public R save(@RequestBody UserEntity user){
        userService.insert(user);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
//    @PreAuthorize("hasAuthority('users:user:update')")
    @ApiOperation(value = "修改")
    public R update(@RequestBody UserEntity user){
        userService.updateById(user);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
//    @PreAuthorize("hasAuthority('users:user:delete')")
    @ApiOperation(value = "删除")
    public R delete(@RequestBody Integer[] userIds){
        userService.deleteBatchIds(Arrays.asList(userIds));
        return R.ok();
    }

}
