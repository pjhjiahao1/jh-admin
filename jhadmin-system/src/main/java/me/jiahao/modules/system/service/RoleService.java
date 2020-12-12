package me.jiahao.modules.system.service;

import com.github.pagehelper.PageInfo;
import me.jiahao.modules.system.entity.MenuEntity;
import me.jiahao.modules.system.entity.RoleEntity;
import me.jiahao.modules.system.entity.UserEntity;
import me.jiahao.modules.system.entity.bo.RoleExcelBO;
import me.jiahao.utils.PageRequest;
import org.springframework.web.bind.annotation.RequestParam;

import javax.management.relation.Role;
import java.util.List;
import java.util.Map;

/**
 * @author : panjiahao
 * @date : 15:58 2020/9/30
 */
public interface RoleService {

    List<RoleEntity> list();

    PageInfo<RoleEntity> listForPage(Map<String,Object> params);

    int save(RoleEntity roleEntity);

    int update(RoleEntity roleEntity);

    int batchRemove(Long[] ids);

    List<RoleExcelBO> findSysRole(Map<String,Object> params);
}
