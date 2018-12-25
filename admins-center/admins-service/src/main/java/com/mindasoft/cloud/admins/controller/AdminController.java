package com.mindasoft.cloud.admins.controller;

import com.mindasoft.cloud.admins.entity.AdminEntity;
import com.mindasoft.cloud.admins.service.AdminRoleService;
import com.mindasoft.cloud.admins.service.AdminService;
import com.mindasoft.cloud.commons.util.OAuth2Utils;
import com.mindasoft.cloud.commons.util.PageUtils;
import com.mindasoft.cloud.commons.util.R;
import com.mindasoft.cloud.commons.validator.ValidatorUtils;
import com.mindasoft.cloud.commons.validator.group.AddGroup;
import com.mindasoft.cloud.commons.validator.group.UpdateGroup;
import com.mindasoft.cloud.models.LoginPerson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

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
    @Autowired
    private AdminRoleService adminRoleService;

    @GetMapping(value = "/login", params = "username")
    @ApiOperation(value = "登陆")
    public R<LoginPerson> login(String username){
        LoginPerson loginPerson = adminService.getLoginPerson(username);
        if(null != loginPerson){
            return R.ok().put(loginPerson);
        }
        return R.ok();
    }

    @PostMapping("/logout")
    @ApiOperation(value = "登出")
    public R logout(String token){
        return R.ok();
    }

    /**
     * 通过获取信息
     */
    @GetMapping("/current")
    @ApiOperation(value = "获取当前用户信息")
    public R<LoginPerson> current(){
        return R.ok().put(OAuth2Utils.getLoginPerson());
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
        if(null != admin){
            //获取用户所属的角色列表
            List<Long> roleIdList = adminRoleService.queryRoleIdList(adminId);
            admin.setRoleIdList(roleIdList);
        }

        return R.ok().put(admin);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('admins:admin:save')")
    @ApiOperation(value = "保存")
    public R save(@RequestBody AdminEntity admin){
        ValidatorUtils.validateEntity(admin, AddGroup.class);
        admin.setCreateAdminId(OAuth2Utils.getId());
        adminService.save(admin);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('admins:admin:update')")
    @ApiOperation(value = "修改")
    public R update(@RequestBody AdminEntity admin){
        ValidatorUtils.validateEntity(admin, UpdateGroup.class);
        admin.setCreateAdminId(OAuth2Utils.getId());
        adminService.update(admin);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('admins:admin:delete')")
    @ApiOperation(value = "删除")
    public R delete(@RequestBody Long[] adminIds){
        if(ArrayUtils.contains(adminIds, 1L)){
            return R.fail("系统管理员不能删除");
        }
        if(ArrayUtils.contains(adminIds, OAuth2Utils.getId())){
            return R.fail("当前用户不能删除");
        }
        adminService.deleteBatchIds(Arrays.asList(adminIds));
        return R.ok();
    }

}
