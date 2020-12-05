package me.jiahao.modules.system.mapper;

import me.jiahao.base.BaseMapper;
import me.jiahao.modules.system.entity.UserRoleEntity;

/**
 * @author : panjiahao
 * @date : 16:57 2020/9/30
 */
public interface UserRoleMapper extends BaseMapper<UserRoleEntity> {
    int getUserRole(Long id);
}
