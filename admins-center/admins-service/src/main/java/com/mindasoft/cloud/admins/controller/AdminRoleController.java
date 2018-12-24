package com.mindasoft.cloud.admins.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.mindasoft.cloud.admins.entity.AdminRoleEntity;
import com.mindasoft.cloud.admins.service.AdminRoleService;
import com.mindasoft.cloud.commons.util.PageUtils;
import com.mindasoft.cloud.commons.util.R;



/**
 * 用户与角色对应关系
 *
 * @author huangmin
 * @email hmiter@sina.com
 * @date 2018-12-24 13:02:21
 */
@RestController
@RequestMapping("adminrole")
@Api(tags = "用户与角色对应关系api")
public class AdminRoleController {
    @Autowired
    private AdminRoleService adminRoleService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('admins:adminrole:list')")
    @ApiOperation(value = "列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true),
            @ApiImplicitParam(name = "limit",value = "分页结束位置", required = true)
    })
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = adminRoleService.queryPage(params);
        return R.ok().put(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('admins:adminrole:info')")
    @ApiOperation(value = "信息")
    public R info(@PathVariable("id") Long id){
        AdminRoleEntity adminRole = adminRoleService.selectById(id);
        return R.ok().put(adminRole);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('admins:adminrole:save')")
    @ApiOperation(value = "保存")
    public R save(@RequestBody AdminRoleEntity adminRole){
        adminRoleService.insert(adminRole);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('admins:adminrole:update')")
    @ApiOperation(value = "修改")
    public R update(@RequestBody AdminRoleEntity adminRole){
        adminRoleService.updateById(adminRole);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('admins:adminrole:delete')")
    @ApiOperation(value = "删除")
    public R delete(@RequestBody Long[] ids){
        adminRoleService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

}
