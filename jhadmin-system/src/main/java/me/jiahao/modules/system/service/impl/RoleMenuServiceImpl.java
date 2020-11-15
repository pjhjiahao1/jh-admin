package me.jiahao.modules.system.service.impl;

import lombok.RequiredArgsConstructor;
import me.jiahao.exception.R;
import me.jiahao.modules.system.entity.MenuEntity;
import me.jiahao.modules.system.entity.RoleMenuEntity;
import me.jiahao.modules.system.entity.vo.MenuTreeVo;
import me.jiahao.modules.system.mapper.MenuMapper;
import me.jiahao.modules.system.mapper.RoleMenuMapper;
import me.jiahao.modules.system.service.RoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : panjiahao
 * @date : 11:00 2020/10/9
 */
@Service
@RequiredArgsConstructor
public class RoleMenuServiceImpl implements RoleMenuService {
    private final MenuMapper menuMapper;
    private final RoleMenuMapper roleMenuMapper;

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Override
    public R save(Long roleId, List<MenuTreeVo> roleMenu) {
        roleMenuMapper.removeRole(roleId);// 删除
        Map<String,Object> params = new HashMap<>();
        RoleMenuEntity roleMenuEntity = new RoleMenuEntity();
        boolean flag = false;
        for (MenuTreeVo m : roleMenu) {
            String menuName = m.getTitle(); // 表示父级菜单
            params.put("menuName", menuName);
            params.remove("menuPid");
            List<MenuEntity> entityList = menuMapper.getMenuForParams(params);
            if (entityList.isEmpty())
                return R.error("保存失败：cause 菜单不存在");
            Long menuId = entityList.get(0).getId();
            if (m.getChildren() != null && !m.getChildren().isEmpty()) { // 有子级
                roleMenuEntity.setMenuId(menuId);
                roleMenuEntity.setRoleId(roleId);
                roleMenuMapper.save(roleMenuEntity);
                flag = true;
            } else {
                // true 表示上级已保存 无需再操作 false 表示上级没保存需手动查询并保存
                if (flag) {
                    roleMenuEntity.setMenuId(menuId);
                    roleMenuEntity.setRoleId(roleId);
                    roleMenuMapper.save(roleMenuEntity);
                } else {
                    params.put("menuPid", entityList.get(0).getMenuPid());
                    params.remove("menuName");
                    List<MenuEntity> menuForParams = menuMapper.getMenuForParams(params);
                    roleMenuEntity.setMenuId(menuForParams.get(0).getId());
                    roleMenuEntity.setRoleId(roleId);
                    roleMenuMapper.save(roleMenuEntity); // 保存父级
                    roleMenuEntity.setMenuId(menuId);
                    roleMenuEntity.setRoleId(roleId);
                    roleMenuMapper.save(roleMenuEntity); // 保存子级
                }
            }
        }
        return R.success("保存成功！");
    }
}
