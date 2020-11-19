package me.jiahao.modules.system.mapper;

import me.jiahao.base.BaseMapper;
import me.jiahao.modules.system.entity.MenuEntity;

import java.util.List;
import java.util.Map;

/**
 * @author : panjiahao
 * @date : 16:19 2020/9/22
 */
public interface MenuMapper extends BaseMapper<MenuEntity> {
    List<MenuEntity> getMenuForRole (List<String> list);

    List<MenuEntity> getMenuForParams(Map<String,Object> params);
}
