package me.jiahao.modules.system.mapper;

import me.jiahao.modules.system.entity.UserRoleEntity;

/**
 * @author : panjiahao
 * @date : 16:57 2020/9/30
 */
public interface UserRoleMapper {
    int save (UserRoleEntity userRoleEntity);
    int remove (String id);
}
