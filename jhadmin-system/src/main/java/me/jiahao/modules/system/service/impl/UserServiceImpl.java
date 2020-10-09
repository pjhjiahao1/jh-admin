package me.jiahao.modules.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import me.jiahao.exception.R;
import me.jiahao.modules.system.entity.UserEntity;
import me.jiahao.modules.system.entity.UserRoleEntity;
import me.jiahao.modules.system.mapper.UserMapper;
import me.jiahao.modules.system.mapper.UserRoleMapper;
import me.jiahao.modules.system.service.UserService;
import me.jiahao.utils.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : panjiahao
 * @date : 15:22 2020/9/24
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final UserRoleMapper userRoleMapper;

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Override
    public R save(UserEntity userEntity) {
        UserEntity userName = userMapper.getUserByUserName(userEntity.getUsername());
        if (userName != null) {
           return R.warn("账号已存在!");
        }
        // 密码加密 密码默认 123456
        String encode = new BCryptPasswordEncoder().encode("123456");
        userEntity.setPassword(encode);
        int count = userMapper.save(userEntity);
        UserRoleEntity role = new UserRoleEntity();
        role.setRoleId(userEntity.getRoleId());
        role.setUserId(userEntity.getId());
        userRoleMapper.save(role);
        return R.common(count);
    }

    @Override
    public R update(UserEntity userEntity) {
        int count = userMapper.update(userEntity);
        return R.common(count);
    }

    @Override
    public R remove(String id) {
        // 删除用户
        int count = userMapper.remove(id);
        // 删除用户对于的角色
        userRoleMapper.remove(id);
        return R.common(count);
    }

    @Override
    public PageInfo<UserEntity> listForPage(PageRequest pageQuery) {
        PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());
        List<UserEntity> sysMenus = userMapper.listForPage();
        PageInfo<UserEntity> pageInfo = new PageInfo<>(sysMenus);
        return pageInfo;
    }
}
