package me.jiahao.modules.quartz.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import me.jiahao.exception.R;
import me.jiahao.modules.quartz.entity.bo.SysQuartzLogBO;
import me.jiahao.utils.Conversion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import me.jiahao.modules.quartz.entity.SysQuartzLogEntity;
import me.jiahao.modules.quartz.mapper.SysQuartzLogMapper;
import me.jiahao.modules.quartz.service.SysQuartzLogService;


@Service(value = "sysQuartzLogService")
@RequiredArgsConstructor
public class SysQuartzLogServiceImpl implements SysQuartzLogService {

    private final SysQuartzLogMapper sysQuartzLogMapper;
    private final Conversion conversion;

    @Override
    public PageInfo<SysQuartzLogEntity> listForPage(Map<String,Object> params) {
        PageHelper.startPage(Integer.parseInt(params.get("currentPage").toString()), Integer.parseInt(params.get("pageSize").toString()));
        List<SysQuartzLogEntity> list = sysQuartzLogMapper.listForPage(params);
        PageInfo<SysQuartzLogEntity> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Override
    public R save(SysQuartzLogEntity sysQuartzLogEntity) {
        int count = sysQuartzLogMapper.save(sysQuartzLogEntity);
        return R.common(count);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Override
    public R update(SysQuartzLogEntity sysQuartzLogEntity) {
        int count = sysQuartzLogMapper.update(sysQuartzLogEntity);
        return R.common(count);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Override
    public R remove(Long[] ids) {
        int count = sysQuartzLogMapper.batchRemove(ids);
        return R.common(count);
    }

    @Override
    public List<SysQuartzLogBO> findSysQuartzLog(Map<String, Object> params) {
        List<SysQuartzLogEntity> list = sysQuartzLogMapper.listForPage(params);
        List<SysQuartzLogBO> data = conversion.typeConversion(new SysQuartzLogBO(), list);
        return data;
    }
}