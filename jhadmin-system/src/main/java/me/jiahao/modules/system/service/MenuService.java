package me.jiahao.modules.system.service;

import me.jiahao.modules.system.entity.MenuEntity;

import java.util.List;

/**
 * @author : panjiahao
 * @date : 15:50 2020/9/22
 */
public interface MenuService {

    /**
     * 根据当前用户获取菜单
     * @param currentUserName /
     * @return /
     */
    List<MenuEntity> findByUser( String currentUserName);

    /**
     * 根据当前角色获取菜单
     * @param role /
     * @return /
     */
    List<MenuEntity> findByRole( String role);

    /**
     * 构建菜单树
     * @param MenuEntity 原始数据
     * @return /
     */
    List<MenuEntity> buildTree(List<MenuEntity> MenuEntity);

    /**
     * 构建菜单树
     * @param menuDtos /
     * @return /
     */
    Object buildMenus(List<MenuEntity> menuDtos);

    /**
     * 构建菜单树
     * @param menuDtos /
     * @return /
     */
    Object buildMenusTree(List<MenuEntity> menuDtos);
}
