package me.jiahao.modules.system.mapper;

import me.jiahao.base.BaseMapper;
import me.jiahao.modules.system.entity.UserEntity;

/**
 * @author : panjiahao
 * @date : 15:19 2020/9/24
 */
public interface UserMapper extends BaseMapper<UserEntity> {
    UserEntity getUserByUserName(String username);
}
