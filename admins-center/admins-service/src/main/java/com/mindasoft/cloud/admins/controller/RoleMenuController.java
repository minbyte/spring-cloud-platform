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

import com.mindasoft.cloud.admins.entity.RoleMenuEntity;
import com.mindasoft.cloud.admins.service.RoleMenuService;
import com.mindasoft.cloud.commons.util.PageUtils;
import com.mindasoft.cloud.commons.util.R;



/**
 * 角色与菜单对应关系
 *
 * @author huangmin
 * @email hmiter@sina.com
 * @date 2018-12-24 13:02:21
 */
@RestController
@RequestMapping("rolemenu")
@Api(tags = "角色与菜单对应关系api")
public class RoleMenuController {
    @Autowired
    private RoleMenuService roleMenuService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('admins:rolemenu:list')")
    @ApiOperation(value = "列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true),
            @ApiImplicitParam(name = "limit",value = "分页结束位置", required = true)
    })
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = roleMenuService.queryPage(params);
        return R.ok().put(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('admins:rolemenu:info')")
    @ApiOperation(value = "信息")
    public R info(@PathVariable("id") Long id){
        RoleMenuEntity roleMenu = roleMenuService.selectById(id);
        return R.ok().put(roleMenu);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('admins:rolemenu:save')")
    @ApiOperation(value = "保存")
    public R save(@RequestBody RoleMenuEntity roleMenu){
        roleMenuService.insert(roleMenu);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('admins:rolemenu:update')")
    @ApiOperation(value = "修改")
    public R update(@RequestBody RoleMenuEntity roleMenu){
        roleMenuService.updateById(roleMenu);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('admins:rolemenu:delete')")
    @ApiOperation(value = "删除")
    public R delete(@RequestBody Long[] ids){
        roleMenuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

}
