package me.jiahao.modules.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import me.jiahao.modules.system.entity.MenuEntity;
import me.jiahao.modules.system.entity.RoleEntity;
import me.jiahao.modules.system.entity.UserEntity;
import me.jiahao.modules.system.mapper.RoleMapper;
import me.jiahao.modules.system.mapper.UserMapper;
import me.jiahao.modules.system.service.RoleService;
import me.jiahao.utils.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : panjiahao
 * @date : 15:58 2020/9/30
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

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
}
