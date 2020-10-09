package me.jiahao.modules.security.mapper;

import me.jiahao.modules.security.entity.UserDetailsEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : panjiahao
 * @date : 15:12 2020/9/17
 */
@Component
public interface UserMenuRoleMeaasgeMapper {
    // 获取用户信息
    UserDetailsEntity getUserDetail(String username);
    // 获取角色编码
    List<String> getRoleCode(@Param(value="username") String username);
    // 获取url
    List<String> getPermission(List<String> list);
    // 通过用户名获取URL
    List<String> getObjectFromName(String username);
}
