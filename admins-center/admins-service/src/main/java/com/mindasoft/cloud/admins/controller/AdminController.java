package com.mindasoft.cloud.admins.controller;

import com.mindasoft.cloud.admins.entity.AdminEntity;
import com.mindasoft.cloud.admins.param.LoginParam;
import com.mindasoft.cloud.admins.service.AdminService;
import com.mindasoft.cloud.commons.util.PageUtils;
import com.mindasoft.cloud.commons.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 管理员接口
 * @author: min
 * @date: 2018/12/17 13:55
 * @version: 1.0.0
 */
@RestController
@RequestMapping("admin")
@Api(tags = "管理员api")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    @ApiOperation(value = "登陆")
    public R login(@RequestBody LoginParam param){
        if("admin".equals(param.getUsername()) && "admin".equals(param.getPassword())){
            return R.ok().put(new HashMap<String,String>(){{put("token","admin");}});
        }else{
            return R.fail("登录失败");
        }
    }

    @PostMapping("/logout")
    @ApiOperation(value = "登出")
    public R logout(String token){
        return R.ok();
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('admins:admin:list')")
    @ApiOperation(value = "列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true),
            @ApiImplicitParam(name = "limit",value = "分页结束位置", required = true)
    })
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = adminService.queryPage(params);
        return R.ok().put(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{adminId}")
    @PreAuthorize("hasAuthority('admins:admin:info')")
    @ApiOperation(value = "信息")
    public R info(@PathVariable("adminId") Long adminId){
        AdminEntity admin = adminService.selectById(adminId);
        return R.ok().put(admin);
    }

    /**
     * 信息
     */
    @GetMapping("/info")
    @ApiOperation(value = "通过token获取信息")
    public R info(String token){
        HashMap map = new HashMap();
        map.put("name","admin");
        map.put("avatar","https://img.mgtv.com/imgotv-member/user/avt.jpg");
        map.put("roles", new ArrayList<String>(){{
            add("admin");
        }});
        return R.ok().put(map);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('admins:admin:save')")
    @ApiOperation(value = "保存")
    public R save(@RequestBody AdminEntity admin){
        adminService.insert(admin);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('admins:admin:update')")
    @ApiOperation(value = "修改")
    public R update(@RequestBody AdminEntity admin){
        adminService.updateById(admin);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('admins:admin:delete')")
    @ApiOperation(value = "删除")
    public R delete(@RequestBody Long[] adminIds){
        adminService.deleteBatchIds(Arrays.asList(adminIds));
        return R.ok();
    }

}
