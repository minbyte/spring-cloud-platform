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

import com.mindasoft.cloud.admins.entity.MenuEntity;
import com.mindasoft.cloud.admins.service.MenuService;
import com.mindasoft.cloud.commons.util.PageUtils;
import com.mindasoft.cloud.commons.util.R;



/**
 * 菜单管理
 *
 * @author huangmin
 * @email hmiter@sina.com
 * @date 2018-12-24 13:02:21
 */
@RestController
@RequestMapping("menu")
@Api(tags = "菜单管理api")
public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('admins:menu:list')")
    @ApiOperation(value = "列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true),
            @ApiImplicitParam(name = "limit",value = "分页结束位置", required = true)
    })
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = menuService.queryPage(params);
        return R.ok().put(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{menuId}")
    @PreAuthorize("hasAuthority('admins:menu:info')")
    @ApiOperation(value = "信息")
    public R info(@PathVariable("menuId") Long menuId){
        MenuEntity menu = menuService.selectById(menuId);
        return R.ok().put(menu);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('admins:menu:save')")
    @ApiOperation(value = "保存")
    public R save(@RequestBody MenuEntity menu){
        menuService.insert(menu);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('admins:menu:update')")
    @ApiOperation(value = "修改")
    public R update(@RequestBody MenuEntity menu){
        menuService.updateById(menu);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('admins:menu:delete')")
    @ApiOperation(value = "删除")
    public R delete(@RequestBody Long[] menuIds){
        menuService.deleteBatchIds(Arrays.asList(menuIds));
        return R.ok();
    }

}
