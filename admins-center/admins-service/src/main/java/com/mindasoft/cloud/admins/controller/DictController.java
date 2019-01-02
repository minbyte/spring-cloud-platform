package com.mindasoft.cloud.admins.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.mindasoft.cloud.admins.entity.DictEntity;
import com.mindasoft.cloud.admins.service.DictService;
import com.mindasoft.cloud.commons.util.PageUtils;
import com.mindasoft.cloud.commons.util.R;



/**
 * 字典表
 *
 * @author huangmin
 * @email hmiter@sina.com
 * @date 2018-12-28 13:35:34
 */
@RestController
@RequestMapping("dict")
@Api(tags = "字典表api")
public class DictController {
    @Autowired
    private DictService dictService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('admins:dict:list')")
    @ApiOperation(value = "列表")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = dictService.queryPage(params);
        return R.ok().put(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('admins:dict:info')")
    @ApiOperation(value = "信息")
    public R info(@PathVariable("id") Long id){
        DictEntity dict = dictService.selectById(id);
        return R.ok().put(dict);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('admins:dict:save')")
    @ApiOperation(value = "保存")
    public R save(@RequestBody DictEntity dict){
        dictService.insert(dict);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('admins:dict:update')")
    @ApiOperation(value = "修改")
    public R update(@RequestBody DictEntity dict){
        dictService.updateById(dict);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('admins:dict:delete')")
    @ApiOperation(value = "删除")
    public R delete(@RequestBody Long[] ids){
        dictService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

}
