package me.jiahao.modules.system.mapper;

import com.github.pagehelper.Page;
import me.jiahao.base.BaseMapper;
import me.jiahao.modules.system.entity.UserEntity;
import org.apache.catalina.User;

import java.util.List;

/**
 * @author : panjiahao
 * @date : 15:19 2020/9/24
 */
public interface UserMapper extends BaseMapper<UserEntity> {
    UserEntity getUserByUserName(String username);
}
