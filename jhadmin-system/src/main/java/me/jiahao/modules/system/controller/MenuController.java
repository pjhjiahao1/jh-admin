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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : panjiahao
 * @date : 14:31 2020/9/22
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menus")
@Api(tags = "系统：菜单管理")
public class MenuController {

    private final MenuService menuService;

    /*
    **
    * @Description: 构建左侧菜单栏
    * @Param: []
    * @return: me.jiahao.exception.R
    * @Author: panjiahao
    * @Date: 2020/10/8
    */
    @ApiOperation("构建左侧菜单栏")
    @PreAuthorize("@el.check('menu:list')")
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
     * @Description: 构建角色管理菜单树
     * @Param: []
     * @return: me.jiahao.exception.R
     * @Author: panjiahao
     * @Date: 2020/10/8
     */
    @ApiOperation("构建角色管理菜单树")
    @PreAuthorize("@el.check('menu:list')")
    @GetMapping(value = "/tree")
    public R buildMenusTree(@RequestParam(value = "rolecode",required = true) String rolecode){
        if (StringUtils.isBlank(rolecode)) {
            return R.error("角色编码丢失!");
        }
        // 获取所有菜单列表
        List<MenuEntity> menuList = menuService.getMenuForParams(new HashMap<>());
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

    @ApiOperation("菜单列表")
    @PreAuthorize("@el.check('menu:list')")
    @GetMapping
    public R listMenu () {
        // 获取所有菜单列表
        List<MenuEntity> menuList = menuService.getMenuForParams(new HashMap<>());
        List<MenuEntity> menuTree = menuService.buildTree(menuList);
        return R.success(menuTree);
    }

    /*
    **
    * @Description: 获取一级菜单
    * @Param: []
    * @return: me.jiahao.exception.R
    * @Author: panjiahao
    * @Date: 2020/11/19
    */
    @ApiOperation("获取一级菜单")
    @PreAuthorize("@el.check('menu:list')")
    @GetMapping(value = "/firstMenu")
    public R getFirstMenu () {
        Map<String,Object> params = new HashMap<>();
        params.put("pid","0");
        List<MenuEntity> menuForParams = menuService.getMenuForParams(params);
        return R.success(menuForParams);
    }

    @ApiOperation("保存")
    @PreAuthorize("@el.check('menu:list')")
    @PostMapping
    public R save (MenuEntity menuEntity) {
        if (menuEntity.getMenuPid() == null && menuEntity.getIsLeaf() != 0) {
            return R.warn("请选择上级名称");
        }
        // 表示目录
        if (menuEntity.getLevel() == 1) {
            menuEntity.setMenuPid(Long.parseLong("0"));
            menuEntity.setComponent("Main");
        }
        return menuService.save(menuEntity);
    }
    @ApiOperation("查询")
    @PreAuthorize("@el.check('menu:list')")
    @GetMapping(value = "list")
    public R list (@RequestParam String menuPid) {
        Map<String,Object> params = new HashMap<>();
        params.put("pid",menuPid);
        List<MenuEntity> menuForParams = menuService.getMenuForParams(params);
        return R.success(menuForParams);
    }



    @ApiOperation("更新")
    @PreAuthorize("@el.check('menu:list')")
    @PutMapping
    public R update (MenuEntity menuEntity) {
        return menuService.update(menuEntity);
    }

    @ApiOperation("删除")
    @PreAuthorize("@el.check('menu:list')")
    @DeleteMapping
    public R remove (@RequestParam(value = "id") Long id) {
        return menuService.remove(id);
    }
}
