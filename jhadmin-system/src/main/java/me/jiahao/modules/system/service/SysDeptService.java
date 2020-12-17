package me.jiahao.modules.system.service;

import me.jiahao.exception.R;
import com.github.pagehelper.PageInfo;
import me.jiahao.modules.system.entity.SysDeptEntity;
import me.jiahao.modules.system.entity.bo.SysDeptBO;

import java.util.List;
import java.util.Map;


/**
 * 部门表
 *
 * @author jiahao.pan
 * @email 1342939721@qq.com
 * @date 2020-12-11 14:05:45
 */
public interface SysDeptService {

    PageInfo<SysDeptEntity> listForPage(Map<String,Object> params);

    List<SysDeptEntity> findAll(Map<String,Object> params);

    List<Long> getRecursiveDept(Long id);

    List<SysDeptEntity> getTreeData(Map<String,Object> params);

    R save (SysDeptEntity sysDeptEntity);

    R update(SysDeptEntity sysDeptEntity);

    R remove(Long[] id);

    List<SysDeptBO> findSysUser(Map<String,Object> params);
}



