package me.jiahao.modules.quartz.service;

import me.jiahao.exception.R;
import com.github.pagehelper.PageInfo;
import me.jiahao.modules.quartz.entity.SysQuartzLogEntity;

import java.util.List;
import java.util.Map;


/**
 * 定时任务日志
 *
 * @author jiahao.pan
 * @email 1342939721@qq.com
 * @date 2020-12-25 16:44:42
 */
public interface SysQuartzLogService {

    PageInfo<SysQuartzLogEntity> listForPage(Map<String,Object> params);

    R save (SysQuartzLogEntity sysQuartzLogEntity);

    R update(SysQuartzLogEntity sysQuartzLogEntity);

    R remove(Long[] id);
}



