package me.jiahao.modules.system.service.impl;

import lombok.RequiredArgsConstructor;
import me.jiahao.exception.R;
import me.jiahao.modules.security.entity.UserDetailsEntity;
import me.jiahao.modules.security.mapper.UserMenuRoleMeaasgeMapper;
import me.jiahao.modules.system.entity.MenuEntity;
import me.jiahao.modules.system.entity.RoleEntity;
import me.jiahao.modules.system.entity.RoleMenuEntity;
import me.jiahao.modules.system.entity.vo.MenuMetaVo;
import me.jiahao.modules.system.entity.vo.MenuTreeVo;
import me.jiahao.modules.system.entity.vo.Menuvo;
import me.jiahao.modules.system.mapper.MenuMapper;
import me.jiahao.modules.system.mapper.RoleMapper;
import me.jiahao.modules.system.mapper.RoleMenuMapper;
import me.jiahao.modules.system.service.MenuService;
import me.jiahao.utils.SecurityUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : panjiahao
 * @date : 15:50 2020/9/22
 */
@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final UserMenuRoleMeaasgeMapper userMenuRoleMeaasgeMapper;
    private final MenuMapper menuMapper;
    private final RoleMapper roleMapper;
    private final RoleMenuMapper roleMenuMapper;

    @Override
    public List<MenuEntity> findByRole(String role) {
        List<String> roleCode = new ArrayList<>();
        if (StringUtils.isNotBlank(role)) {
            roleCode.add(role);
        } else {
            List<RoleEntity> allRole = roleMapper.list();
            for (RoleEntity r : allRole) {
                roleCode.add(r.getRoleCode());
            }
        }
        List<MenuEntity> menuForRole = new ArrayList<>();
        List<MenuEntity> menuEntityList = menuMapper.getMenuForRole(roleCode);
        // 账号无菜单权限会造成 空指针异常
        if (CollectionUtils.isNotEmpty(menuEntityList) && (menuEntityList.size() > 1 || menuEntityList.get(0) != null)) {
            menuForRole = menuEntityList;
        }
        return menuForRole;
    }

    @Override
    public List<MenuEntity> findByUser(String currentUserName) {
        List<String> roleCode = userMenuRoleMeaasgeMapper.getRoleCode(currentUserName);
        List<MenuEntity> menuForRole = new ArrayList<>();
        if (!roleCode.isEmpty()) {
            List<MenuEntity> menuEntityList = menuMapper.getMenuForRole(roleCode);
            // 账号无菜单权限会造成 空指针异常
            if (CollectionUtils.isNotEmpty(menuEntityList) && (menuEntityList.size() > 1 || menuEntityList.get(0) != null)) {
                menuForRole = menuEntityList;
            }
        }
        return menuForRole;
    }

    @Override
    public List<MenuEntity> buildTree(List<MenuEntity> Menus) {
        List<MenuEntity> trees = new ArrayList<>();
        Set<Long> ids = new HashSet<>();
        if (Menus.isEmpty()) {
            return trees;
        }
        for (MenuEntity menuDTO : Menus) {
            // 防止无权限造成空指针报错
            if (menuDTO == null) {
                continue;
            }
            if (menuDTO.getMenuPid() == 0) {
                trees.add(menuDTO);
            }
            for (MenuEntity it : Menus) {
                // 防止无权限造成空指针报错
                if (it == null) {
                    continue;
                }
                if (menuDTO.getId().equals(it.getMenuPid())) {
                    if (menuDTO.getChildren() == null) {
                        menuDTO.setChildren(new ArrayList<>());
                    }
                    menuDTO.getChildren().add(it);
                    ids.add(it.getId());
                }
            }
        }
        if(trees.size() == 0){
            trees = Menus.stream().filter(s -> !ids.contains(s.getId())).collect(Collectors.toList());
        }
        return trees;
    }

    @Override
    public Object buildMenusTree(List<MenuEntity> menuDtos) {
        List<MenuTreeVo> treeList = new ArrayList<>();
        for (MenuEntity m : menuDtos) {
            MenuTreeVo tree = new MenuTreeVo();
            // 表示顶层
            if (m.getMenuPid() == 0) {
                List<MenuTreeVo> childrenList = new ArrayList<>();
                tree.setTitle(m.getMenuName());
                List<MenuEntity> children = m.getChildren();
                if (children!= null && !children.isEmpty()) {
                    for (MenuEntity menu : children) {
                        MenuTreeVo childrenTree = new MenuTreeVo();
                        if (menu.getAuthMenu()) {
                            childrenTree.setChecked(true);
                        }
                        childrenTree.setTitle(menu.getMenuName());
                        childrenList.add(childrenTree);
                    }
                }
                tree.setChildren(childrenList);
            }
            treeList.add(tree);
        }
        return treeList;
    }

    @Override
    public Object buildMenus(List<MenuEntity> menuDtos) {
        List<Menuvo> menuvoList = new ArrayList<>();
        UserDetailsEntity userDetailsEntity = (UserDetailsEntity) SecurityUtils.getCurrentUser();
        Collection<? extends GrantedAuthority> authorities = userDetailsEntity.getAuthorities();
        List<String> emptyList = new ArrayList<>();
        // 获取角色列表
        authorities.forEach(ele -> emptyList.add(ele.getAuthority()));
        for (MenuEntity m : menuDtos) {
            Menuvo menuvo = new Menuvo();
            // 表示顶层
            if (m.getMenuPid() == 0) {
                menuvo.setName(m.getMenuCode());
                menuvo.setComponent(m.getComponent());
                menuvo.setPath("/" + m.getMenuCode());
                MenuMetaVo meta = new MenuMetaVo();
                meta.setIcon(m.getIcon());
                meta.setTitle(m.getMenuName());
                meta.setHideInMenu(false);
                meta.setShowAlways(true);
                menuvo.setMeta(meta);
                List<MenuEntity> children = m.getChildren();
                if (children!= null && !children.isEmpty()) {
                    List<Menuvo> childList = new ArrayList<>();
                    for (MenuEntity child : children) {
                        Menuvo menuChild = new Menuvo();
                        menuChild.setName(child.getMenuCode());
                        menuChild.setComponent(child.getComponent());
                        menuChild.setPath(child.getMenuCode());
                        MenuMetaVo metaChild = new MenuMetaVo();
                        metaChild.setIcon(child.getIcon());
                        metaChild.setTitle(child.getMenuName());
                        metaChild.setHideInMenu(false);
                        // 设置页面 角色标识
                        metaChild.setAccess(emptyList);
                        menuChild.setMeta(metaChild);
                        childList.add(menuChild);
                    }
                    menuvo.setChildren(childList);
                }
                menuvoList.add(menuvo);
            }
        }
        return menuvoList;
    }

    @Override
    public List<MenuEntity> getMenuForParams(Map<String, Object> params) {
        return menuMapper.listForPage(params);
    }

    @Override
    public R save(MenuEntity menuEntity) {
        int count = menuMapper.save(menuEntity);
        return R.common(count);
    }

    @Override
    public R update(MenuEntity menuEntity) {
        int count = menuMapper.update(menuEntity);
        return R.common(count);
    }

    @Override
    public R remove(Long id) {
        Map<String,Object> params = new HashMap<>();
        params.put("menuid",id);
        List<RoleMenuEntity> list = roleMenuMapper.list(params);
        if (!list.isEmpty()) {
            return R.warn("有角色关联此菜单，暂不可删除!");
        }
        int count = menuMapper.remove(id);
        return R.common(count);
    }
}
