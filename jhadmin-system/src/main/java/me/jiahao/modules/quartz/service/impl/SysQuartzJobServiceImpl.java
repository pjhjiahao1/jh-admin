package me.jiahao.modules.quartz.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import me.jiahao.exception.R;
import me.jiahao.modules.quartz.utils.QuartzManager;
import me.jiahao.utils.Conversion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import me.jiahao.modules.quartz.entity.SysQuartzJobEntity;
import me.jiahao.modules.quartz.mapper.SysQuartzJobMapper;
import me.jiahao.modules.quartz.service.SysQuartzJobService;
import me.jiahao.modules.quartz.entity.bo.SysQuartzJobBO;


@Service
@RequiredArgsConstructor
public class SysQuartzJobServiceImpl implements SysQuartzJobService {

    private final SysQuartzJobMapper sysQuartzJobMapper;
    private final Conversion conversion;
    private final QuartzManager quartzManager;

    @Override
    public PageInfo<SysQuartzJobEntity> listForPage(Map<String,Object> params) {
        PageHelper.startPage(Integer.parseInt(params.get("currentPage").toString()), Integer.parseInt(params.get("pageSize").toString()));
        List<SysQuartzJobEntity> list = sysQuartzJobMapper.listForPage(params);
        PageInfo<SysQuartzJobEntity> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Override
    public R save(SysQuartzJobEntity sysQuartzJobEntity) {
        // 执行定时任务
        quartzManager.addJob(sysQuartzJobEntity);
        int count = sysQuartzJobMapper.save(sysQuartzJobEntity);
        return R.common(count);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Override
    public R update(SysQuartzJobEntity sysQuartzJobEntity) {
        int count = sysQuartzJobMapper.update(sysQuartzJobEntity);
        return R.common(count);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Override
    public R remove(Long[] ids) {
        for (Long s : ids) {
            SysQuartzJobEntity jobEntity = sysQuartzJobMapper.getObjectById(s);
            quartzManager.deleteJob(jobEntity.getUid());
        }
        int count = sysQuartzJobMapper.batchRemove(ids);
        return R.common(count);
    }

    @Override
    public List<SysQuartzJobBO> findSysQuartzJob(Map<String, Object> params) {
        List<SysQuartzJobEntity> list = sysQuartzJobMapper.listForPage(params);
        List<SysQuartzJobBO> data = conversion.typeConversion(new SysQuartzJobBO(), list);
        return data;
    }

    @Override
    public List<SysQuartzJobEntity> list(Map<String, Object> params) {
        return sysQuartzJobMapper.listForPage(params);
    }
}