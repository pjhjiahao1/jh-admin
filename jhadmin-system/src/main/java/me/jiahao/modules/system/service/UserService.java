package me.jiahao.modules.system.service;

import com.github.pagehelper.PageInfo;
import me.jiahao.exception.R;
import me.jiahao.modules.system.entity.UserEntity;
import me.jiahao.utils.PageRequest;

/**
 * @author : panjiahao
 * @date : 15:22 2020/9/24
 */
public interface UserService {
    PageInfo<UserEntity> listForPage(PageRequest pageQuery);
    R save (UserEntity userEntity);
    R update(UserEntity userEntity);
    R remove(String id);
}
