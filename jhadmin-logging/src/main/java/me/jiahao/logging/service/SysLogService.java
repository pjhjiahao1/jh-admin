package me.jiahao.logging.service;

import me.jiahao.exception.R;
import com.github.pagehelper.PageInfo;
import me.jiahao.logging.entity.SysLogEntity;
import me.jiahao.logging.entity.bo.SysLogBO;

import java.util.List;
import java.util.Map;


/**
 * 操作日志表
 *
 * @author jiahao.pan
 * @email 1342939721@qq.com
 * @date 2020-12-23 16:29:28
 */
public interface SysLogService {

    PageInfo<SysLogEntity> listForPage(Map<String,Object> params);

    R save (SysLogEntity sysLogEntity);

    R update(SysLogEntity sysLogEntity);

    R remove(Long[] id);

    List<SysLogBO> findSysLog(Map<String,Object> params);
}



