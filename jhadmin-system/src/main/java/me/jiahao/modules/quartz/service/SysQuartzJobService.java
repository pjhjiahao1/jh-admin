package me.jiahao.modules.quartz.service;

import me.jiahao.exception.R;
import com.github.pagehelper.PageInfo;
import me.jiahao.modules.quartz.entity.SysQuartzJobEntity;
import me.jiahao.modules.quartz.entity.bo.SysQuartzJobBO;

import java.util.List;
import java.util.Map;


/**
 * 定时任务
 *
 * @author jiahao.pan
 * @email 1342939721@qq.com
 * @date 2020-12-25 15:26:16
 */
public interface SysQuartzJobService {

    PageInfo<SysQuartzJobEntity> listForPage(Map<String,Object> params);

    R save (SysQuartzJobEntity sysQuartzJobEntity);

    R update(SysQuartzJobEntity sysQuartzJobEntity);

    R remove(Long[] id);

    List<SysQuartzJobBO> findSysQuartzJob(Map<String,Object> params);

    List<SysQuartzJobEntity> list(Map<String,Object> params); // 根据参数返回数据
}



