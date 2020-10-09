package me.jiahao.modules.system.mapper;

import me.jiahao.modules.system.entity.RoleMenuEntity;

/**
 * @author : panjiahao
 * @date : 11:12 2020/10/9
 */
public interface RoleMenuMapper {
    int save (RoleMenuEntity roleMenuEntity);
    int removeRole(Long id);
}
