package com.mindasoft.cloud.admins.controller;

import com.mindasoft.cloud.admins.constants.Constant;
import com.mindasoft.cloud.admins.entity.MenuEntity;
import com.mindasoft.cloud.admins.service.MenuService;
import com.mindasoft.cloud.commons.exception.BaseException;
import com.mindasoft.cloud.commons.util.R;
import com.mindasoft.cloud.security.util.OAuth2Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;



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
     * 导航菜单
     */
    @GetMapping("/nav")
    public R nav(){
        List<MenuEntity> menuList = menuService.getAdminMenuList(OAuth2Utils.getAdminId());
        return R.ok().put( menuList);
    }

    /**
     * 选择菜单(添加、修改菜单)
     */
    @GetMapping("/select")
    @PreAuthorize("hasAuthority('admins:menu:view')")
    public R select(){
        //查询列表数据
        List<MenuEntity> menuList = menuService.queryNotButtonList();

        //添加顶级菜单
        MenuEntity root = new MenuEntity();
        root.setMenuId(0L);
        root.setName("一级菜单");
        root.setParentId(-1L);
        root.setOpen(true);
        menuList.add(root);

        return R.ok().put(menuList);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('admins:menu:list')")
    @ApiOperation(value = "列表")
    public R list(){
        List<MenuEntity> menuList = menuService.selectList(null);
        for(MenuEntity menuEntity : menuList){
            MenuEntity parentMenuEntity = menuService.selectById(menuEntity.getParentId());
            if(parentMenuEntity != null){
                menuEntity.setParentName(parentMenuEntity.getName());
            }
        }
        return R.ok().put(menuList);
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
        //数据校验
        verifyForm(menu);

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
        //数据校验
        verifyForm(menu);

        menuService.updateById(menu);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{menuId}")
    @PreAuthorize("hasAuthority('admins:menu:delete')")
    @ApiOperation(value = "删除")
    public R delete(@PathVariable("menuId") long menuId){
        if(menuId < 100){
            return R.fail("系统菜单，不能删除");
        }

        //判断是否有子菜单或按钮
        List<MenuEntity> menuList = menuService.queryListParentId(menuId);
        if(menuList.size() > 0){
            return R.fail("请先删除子菜单或按钮");
        }
        menuService.delete(menuId);
        return R.ok();
    }

    /**
     * 验证参数是否正确
     */
    private void verifyForm(MenuEntity menu){
        if(StringUtils.isBlank(menu.getName())){
            throw new BaseException("菜单名称不能为空");
        }

        if(menu.getParentId() == null){
            throw new BaseException("上级菜单不能为空");
        }

        //菜单
        if(menu.getType() == Constant.MenuType.MENU.getValue()){
            if(StringUtils.isBlank(menu.getUrl())){
                throw new BaseException("菜单URL不能为空");
            }
        }

        //上级菜单类型
        int parentType = Constant.MenuType.CATALOG.getValue();
        if(menu.getParentId() != 0){
            MenuEntity parentMenu = menuService.selectById(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if(menu.getType() == Constant.MenuType.CATALOG.getValue() ||
                menu.getType() == Constant.MenuType.MENU.getValue()){
            if(parentType != Constant.MenuType.CATALOG.getValue()){
                throw new BaseException("上级菜单只能为目录类型");
            }
            return ;
        }

        //按钮
        if(menu.getType() == Constant.MenuType.BUTTON.getValue()){
            if(parentType != Constant.MenuType.MENU.getValue()){
                throw new BaseException("上级菜单只能为菜单类型");
            }
            return ;
        }
    }

}
