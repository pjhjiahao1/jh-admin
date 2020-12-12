package me.jiahao.modules.system.service;

import me.jiahao.exception.R;
import me.jiahao.modules.system.entity.vo.MenuTreeVo;

import java.util.List;

/**
 * @author : panjiahao
 * @date : 11:00 2020/10/9
 */
public interface RoleMenuService {
     R save(Long roleId, List<MenuTreeVo> roleMenu);
}
