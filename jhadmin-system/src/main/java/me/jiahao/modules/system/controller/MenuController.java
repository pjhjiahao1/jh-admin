package me.jiahao.modules.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.jiahao.exception.R;
import me.jiahao.modules.system.entity.MenuEntity;
import me.jiahao.modules.system.service.MenuService;
import me.jiahao.utils.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : panjiahao
 * @date : 14:31 2020/9/22
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menus")
public class MenuController {

    private final MenuService menuService;

    /*
    **
    * @Description: 构建菜单
    * @Param: []
    * @return: me.jiahao.exception.R
    * @Author: panjiahao
    * @Date: 2020/10/8
    */
    @GetMapping(value = "/build")
    public R buildMenus(){
        // 获取当前用户的菜单列表
        List<MenuEntity> menuList = menuService.findByUser(SecurityUtils.getCurrentUserName());
        List<MenuEntity> menuTree = menuService.buildTree(menuList);
        Object o = menuService.buildMenus(menuTree);
        return R.success(o);
    }

    /*
     **
     * @Description: 构建菜单树
     * @Param: []
     * @return: me.jiahao.exception.R
     * @Author: panjiahao
     * @Date: 2020/10/8
     */
    @GetMapping(value = "/tree")
    public R buildMenusTree(@RequestParam(value = "rolecode",required = true) String rolecode){
        if (StringUtils.isBlank(rolecode)) {
            return R.error("角色编码丢失!");
        }
        // 获取所有菜单列表
        List<MenuEntity> menuList = menuService.findByRole(null);
        // 角色对应菜单
        List<MenuEntity> roleMenuList = menuService.findByRole(rolecode);
        for (MenuEntity roleMenu : roleMenuList) {
            String muneName = roleMenu.getMenuName();
            for (MenuEntity m : menuList) {
                if (m == null)
                    continue;
                if (muneName.equals(m.getMenuName())) {
                    // 表示有权限
                    m.setAuthMenu(true);
                    break;
                }
            }
        }
        List<MenuEntity> menuTree = menuService.buildTree(menuList);
        Object o = menuService.buildMenusTree(menuTree);
        return R.success(o);
    }
}
