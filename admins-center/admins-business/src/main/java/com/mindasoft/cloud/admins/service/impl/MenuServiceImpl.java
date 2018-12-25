package com.mindasoft.cloud.admins.service.impl;

import com.mindasoft.cloud.admins.constants.Constant;
import com.mindasoft.cloud.admins.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mindasoft.cloud.commons.util.PageUtils;
import com.mindasoft.cloud.commons.util.Query;

import com.mindasoft.cloud.admins.dao.MenuDao;
import com.mindasoft.cloud.admins.entity.MenuEntity;
import com.mindasoft.cloud.admins.service.MenuService;


@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuDao, MenuEntity> implements MenuService {

    @Autowired
    private AdminService adminService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MenuEntity> page = this.selectPage(
                new Query<MenuEntity>(params).getPage(),
                new EntityWrapper<MenuEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public List<MenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
        List<MenuEntity> menuList = queryListParentId(parentId);
        if(menuIdList == null){
            return menuList;
        }

        List<MenuEntity> userMenuList = new ArrayList<>();
        for(MenuEntity menu : menuList){
            if(menuIdList.contains(menu.getMenuId())){
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<MenuEntity> queryListParentId(Long parentId) {
        return baseMapper.queryListParentId(parentId);
    }

    @Override
    public List<MenuEntity> queryNotButtonList() {
        return baseMapper.queryNotButtonList();
    }

    @Override
    public List<MenuEntity> getAdminMenuList(Long adminId) {
        //系统管理员，拥有最高权限
        if(adminId == Constant.SUPER_ADMIN){
            return getAllMenuList(null);
        }

        //用户菜单列表
        List<Long> menuIdList = adminService.queryAllMenuId(adminId);
        return getAllMenuList(menuIdList);
    }

    @Override
    public void delete(Long menuId) {

    }

    /**
     * 获取所有菜单列表
     */
    private List<MenuEntity> getAllMenuList(List<Long> menuIdList){
        //查询根菜单列表
        List<MenuEntity> menuList = queryListParentId(0L, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    /**
     * 递归
     */
    private List<MenuEntity> getMenuTreeList(List<MenuEntity> menuList, List<Long> menuIdList){
        List<MenuEntity> subMenuList = new ArrayList<MenuEntity>();

        for(MenuEntity entity : menuList){
            //目录
            if(entity.getType() == Constant.MenuType.CATALOG.getValue()){
                entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }

        return subMenuList;
    }

}
