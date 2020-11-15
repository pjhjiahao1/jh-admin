package me.jiahao.modules.system.service;

import com.github.pagehelper.PageInfo;
import me.jiahao.modules.system.entity.MenuEntity;
import me.jiahao.modules.system.entity.RoleEntity;
import me.jiahao.modules.system.entity.UserEntity;
import me.jiahao.utils.PageRequest;

import javax.management.relation.Role;
import java.util.List;

/**
 * @author : panjiahao
 * @date : 15:58 2020/9/30
 */
public interface RoleService {

    List<RoleEntity> getAllRole();

    PageInfo<RoleEntity> listForPage(PageRequest pageQuery);

    int save(RoleEntity roleEntity);

    int update(RoleEntity roleEntity);

    int remove(Long id);
}
