package me.jiahao.modules.system.service;

import me.jiahao.exception.R;
import com.github.pagehelper.PageInfo;
import me.jiahao.modules.system.entity.SysJobEntity;
import me.jiahao.modules.system.entity.bo.SysJobBO;

import java.util.List;
import java.util.Map;


/**
 * 岗位表
 *
 * @author jiahao.pan
 * @email 1342939721@qq.com
 * @date 2020-12-16 17:01:04
 */
public interface SysJobService {

    PageInfo<SysJobEntity> listForPage(Map<String,Object> params);

    List<SysJobEntity> findAll();

    R save (SysJobEntity sysJobEntity);

    R update(SysJobEntity sysJobEntity);

    R remove(Long[] id);

    List<SysJobBO> findSysJob(Map<String,Object> params);
}



