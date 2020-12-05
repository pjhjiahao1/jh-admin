package me.jiahao.modules.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import me.jiahao.modules.system.entity.RoleEntity;
import me.jiahao.modules.system.mapper.RoleMapper;
import me.jiahao.modules.system.mapper.RoleMenuMapper;
import me.jiahao.modules.system.mapper.UserRoleMapper;
import me.jiahao.modules.system.service.RoleService;
import me.jiahao.utils.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author : panjiahao
 * @date : 15:58 2020/9/30
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;
    private final RoleMenuMapper roleMenuMapper;
    private final UserRoleMapper userRoleMapper;

    @Override
    public PageInfo<RoleEntity> listForPage(Map<String,Object> params) {
        PageHelper.startPage(Integer.parseInt(params.get("currentPage").toString()), Integer.parseInt(params.get("pageSize").toString()));
        List<RoleEntity> sysRoles = roleMapper.listForPage(params);
        PageInfo<RoleEntity> pageInfo = new PageInfo<>(sysRoles);
        return pageInfo;
    }

    @Override
    public List<RoleEntity> list() {
        return roleMapper.list();
    }

    @Override
    public int save(RoleEntity roleEntity) {
        return roleMapper.save(roleEntity);
    }

    @Override
    public int update(RoleEntity roleEntity) {
        return roleMapper.update(roleEntity);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Override
    public int batchRemove(Long[] ids) {
        userRoleMapper.batchRemove(ids);
        roleMenuMapper.batchRemove(ids);
        return roleMapper.batchRemove(ids);
    }
}
