package com.mindasoft.cloud.security.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.mindasoft.cloud.security.entity.ClientDetailsEntity;
import com.mindasoft.cloud.security.service.ClientDetailsService;
import com.mindasoft.cloud.commons.util.PageUtils;
import com.mindasoft.cloud.commons.util.R;


/**
 * 客户端应用
 *
 * @author huangmin
 * @email hmiter@sina.com
 * @date 2018-12-27 12:57:15
 */
@RestController
@RequestMapping("clients")
@Api(tags = "客户端应用api")
public class ClientDetailsController {
    @Autowired()
    private ClientDetailsService clientDetailsService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('security:clientdetails:list')")
    @ApiOperation(value = "列表")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = clientDetailsService.queryPage(params);
        return R.ok().put(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('security:clientdetails:info')")
    @ApiOperation(value = "信息")
    public R info(@PathVariable("id") Integer id){
        ClientDetailsEntity clientDetails = clientDetailsService.selectById(id);
        return R.ok().put(clientDetails);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('security:clientdetails:save')")
    @ApiOperation(value = "保存")
    public R save(@RequestBody ClientDetailsEntity clientDetails){
        clientDetailsService.insert(clientDetails);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('security:clientdetails:update')")
    @ApiOperation(value = "修改")
    public R update(@RequestBody ClientDetailsEntity clientDetails){
        clientDetailsService.updateById(clientDetails);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('security:clientdetails:delete')")
    @ApiOperation(value = "删除")
    public R delete(@RequestBody Integer[] ids){
        clientDetailsService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

}
