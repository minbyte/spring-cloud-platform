package com.mindasoft.cloud.admins.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.mindasoft.cloud.admins.entity.ConfigEntity;
import com.mindasoft.cloud.admins.service.ConfigService;
import com.mindasoft.cloud.commons.util.PageUtils;
import com.mindasoft.cloud.commons.util.R;



/**
 * 参数配置
 *
 * @author huangmin
 * @email hmiter@sina.com
 * @date 2018-12-28 13:35:34
 */
@RestController
@RequestMapping("config")
@Api(tags = "参数配置api")
public class ConfigController {
    @Autowired
    private ConfigService configService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('admins:config:list')")
    @ApiOperation(value = "列表")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = configService.queryPage(params);
        return R.ok().put(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('admins:config:info')")
    @ApiOperation(value = "信息")
    public R info(@PathVariable("id") Long id){
        ConfigEntity config = configService.selectById(id);
        return R.ok().put(config);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('admins:config:save')")
    @ApiOperation(value = "保存")
    public R save(@RequestBody ConfigEntity config){
        configService.insert(config);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('admins:config:update')")
    @ApiOperation(value = "修改")
    public R update(@RequestBody ConfigEntity config){
        configService.updateById(config);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('admins:config:delete')")
    @ApiOperation(value = "删除")
    public R delete(@RequestBody Long[] ids){
        configService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

}
