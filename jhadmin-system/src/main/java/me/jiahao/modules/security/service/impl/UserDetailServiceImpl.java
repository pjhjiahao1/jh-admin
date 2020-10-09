package me.jiahao.modules.security.service.impl;

import me.jiahao.modules.security.entity.UserDetailsEntity;
import me.jiahao.modules.security.mapper.UserMenuRoleMeaasgeMapper;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : panjiahao
 * @date : 15:25 2020/9/17
 */
@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    UserMenuRoleMeaasgeMapper userMenuRoleMeaasgeMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 加载基础用户信息
        UserDetailsEntity userDetail = userMenuRoleMeaasgeMapper.getUserDetail(username);
        // 加载用户角色列表
        List<String> roleCode = userMenuRoleMeaasgeMapper.getRoleCode(username);
        // 通过用户角色列表加载用户的资源权限列表
        List<String> permission = userMenuRoleMeaasgeMapper.getPermission(roleCode);
        // 角色是一个特殊的权限 ROLE_前缀
        roleCode = roleCode.stream().collect(Collectors.toList());
        permission.addAll(roleCode);
        userDetail.setAuthorities(
                AuthorityUtils.commaSeparatedStringToAuthorityList(
                        String.join(",",permission)
                )
        );
        return userDetail;
    }
}
