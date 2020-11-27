package me.jiahao.modules.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import me.jiahao.modules.system.entity.RoleEntity;
import me.jiahao.modules.system.mapper.RoleMapper;
import me.jiahao.modules.system.mapper.RoleMenuMapper;
import me.jiahao.modules.system.service.RoleService;
import me.jiahao.utils.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : panjiahao
 * @date : 15:58 2020/9/30
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;
    private final RoleMenuMapper roleMenuMapper;

    @Override
    public PageInfo<RoleEntity> listForPage(PageRequest pageQuery) {
        PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());
        List<RoleEntity> sysRoles = roleMapper.listForPage();
        PageInfo<RoleEntity> pageInfo = new PageInfo<>(sysRoles);
        return pageInfo;
    }

    @Override
    public List<RoleEntity> getAllRole() {
        return roleMapper.getAllRole();
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
    public int remove(Long id) {
        roleMenuMapper.remove(id);
        return roleMapper.remove(id);
    }
}
