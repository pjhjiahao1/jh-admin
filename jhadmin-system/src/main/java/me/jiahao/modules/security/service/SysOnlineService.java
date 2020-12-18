package me.jiahao.modules.security.service;

import me.jiahao.exception.R;
import com.github.pagehelper.PageInfo;
import me.jiahao.modules.security.entity.SysOnlineEntity;
import me.jiahao.modules.security.entity.bo.SysOnlineBO;


import java.util.List;
import java.util.Map;


/**
 * 在线用户表
 *
 * @author jiahao.pan
 * @email 1342939721@qq.com
 * @date 2020-12-18 15:51:00
 */
public interface SysOnlineService {

    PageInfo<SysOnlineEntity> listForPage(Map<String,Object> params);

    R save (SysOnlineEntity sysOnlineEntity);

    R update(SysOnlineEntity sysOnlineEntity);

    R remove(Long[] id);

    List<SysOnlineBO> findSysOnline(Map<String,Object> params);
}



