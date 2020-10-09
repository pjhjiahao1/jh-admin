package me.jiahao.modules.system.mapper;

import me.jiahao.modules.system.entity.MenuEntity;
import me.jiahao.modules.system.entity.RoleEntity;

import java.util.List;

/**
 * @author : panjiahao
 * @date : 15:58 2020/9/30
 */
public interface RoleMapper {
    /*
    **
    * @Description: 查询所有角色
    * @Param: []
    * @return: java.util.List<me.jiahao.modules.system.entity.RoleEntity>
    * @Author: panjiahao
    * @Date: 2020/10/8
    */
    List<RoleEntity> getAllRole();
    /*
    **
    * @Description: 分页查询
    * @Param: []
    * @return: java.util.List<me.jiahao.modules.system.entity.MenuEntity>
    * @Author: panjiahao
    * @Date: 2020/10/8
    */
    List<RoleEntity> listForPage();
}
