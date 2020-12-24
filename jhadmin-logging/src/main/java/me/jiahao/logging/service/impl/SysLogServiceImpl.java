package me.jiahao.logging.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import me.jiahao.exception.R;
import me.jiahao.logging.service.SysLogService;
import me.jiahao.utils.Conversion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import me.jiahao.logging.entity.SysLogEntity;
import me.jiahao.logging.mapper.SysLogMapper;
import me.jiahao.logging.entity.bo.SysLogBO;


@Service
@RequiredArgsConstructor
public class SysLogServiceImpl implements SysLogService {

    private final SysLogMapper sysLogMapper;
    private final Conversion conversion;

    @Override
    public PageInfo<SysLogEntity> listForPage(Map<String,Object> params) {
        PageHelper.startPage(Integer.parseInt(params.get("currentPage").toString()), Integer.parseInt(params.get("pageSize").toString()));
        List<SysLogEntity> list = sysLogMapper.listForPage(params);
        PageInfo<SysLogEntity> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Override
    public R save(SysLogEntity sysLogEntity) {
        int count = sysLogMapper.save(sysLogEntity);
        return R.common(count);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Override
    public R update(SysLogEntity sysLogEntity) {
        int count = sysLogMapper.update(sysLogEntity);
        return R.common(count);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Override
    public R remove(Long[] ids) {
        int count = sysLogMapper.batchRemove(ids);
        return R.common(count);
    }

    @Override
    public List<SysLogBO> findSysLog(Map<String, Object> params) {
        List<SysLogEntity> list = sysLogMapper.listForPage(params);
        List<SysLogBO> data = conversion.typeConversion(new SysLogBO(), list);
        return data;
    }

}