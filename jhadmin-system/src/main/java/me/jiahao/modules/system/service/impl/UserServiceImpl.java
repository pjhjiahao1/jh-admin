package me.jiahao.modules.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import me.jiahao.exception.R;
import me.jiahao.modules.system.entity.UserEntity;
import me.jiahao.modules.system.entity.UserRoleEntity;
import me.jiahao.modules.system.entity.bo.UserExcelBO;
import me.jiahao.modules.system.mapper.UserMapper;
import me.jiahao.modules.system.mapper.UserRoleMapper;
import me.jiahao.modules.system.service.SysDeptService;
import me.jiahao.modules.system.service.UserService;
import me.jiahao.utils.Conversion;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

/**
 * @author : panjiahao
 * @date : 15:22 2020/9/24
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRoleMapper userRoleMapper;
    private final Conversion conversion;
    private final SysDeptService sysDeptService;

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

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Override
    public R update(UserEntity userEntity) {
        int count = userMapper.update(userEntity);
        return R.common(count);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Override
    public R remove(Long[] ids) {
        // 删除用户
        int count = userMapper.batchRemove(ids);
        // 删除用户对于的角色
        userRoleMapper.batchRemove(ids);
        return R.common(count);
    }

    @Override
    public PageInfo<UserEntity> listForPage(Map<String,Object> params) {
        PageHelper.startPage(Integer.parseInt(params.get("currentPage").toString()), Integer.parseInt(params.get("pageSize").toString()));
        String dept = params.get("dept").toString();
        if (StringUtils.isNotBlank(dept)) {
            List<Long> recursiveDept = sysDeptService.getRecursiveDept(Long.parseLong(dept));
            params.put("array",recursiveDept);
        }
        List<UserEntity> sysMenus = userMapper.listForPage(params);
        PageInfo<UserEntity> pageInfo = new PageInfo<>(sysMenus);
        return pageInfo;
    }

    @Override
    public List<UserExcelBO> findSysUser(Map<String, Object> params) {
        List<UserEntity> list = userMapper.listForPage(params);
        List<UserExcelBO> userCheckDataList = conversion.typeConversion(new UserExcelBO(), list);
        return userCheckDataList;

    }
}
