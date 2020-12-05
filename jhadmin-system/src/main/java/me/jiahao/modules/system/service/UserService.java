package me.jiahao.modules.system.service;

import com.github.pagehelper.PageInfo;
import me.jiahao.exception.R;
import me.jiahao.modules.system.entity.UserEntity;
import me.jiahao.utils.PageRequest;

import java.util.Map;

/**
 * @author : panjiahao
 * @date : 15:22 2020/9/24
 */
public interface UserService {
    PageInfo<UserEntity> listForPage(Map<String,Object> params);
    R save (UserEntity userEntity);
    R update(UserEntity userEntity);
    R remove(Long[] id);
}
