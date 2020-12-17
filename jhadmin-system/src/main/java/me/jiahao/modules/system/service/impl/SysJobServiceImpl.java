package me.jiahao.modules.system.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import me.jiahao.exception.R;
import me.jiahao.utils.Conversion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import me.jiahao.modules.system.entity.SysJobEntity;
import me.jiahao.modules.system.mapper.SysJobMapper;
import me.jiahao.modules.system.service.SysJobService;
import me.jiahao.modules.system.entity.bo.SysJobBO;


@Service
@RequiredArgsConstructor
public class SysJobServiceImpl implements SysJobService {

    private final SysJobMapper sysJobMapper;
    private final Conversion conversion;

    @Override
    public PageInfo<SysJobEntity> listForPage(Map<String,Object> params) {
        PageHelper.startPage(Integer.parseInt(params.get("currentPage").toString()), Integer.parseInt(params.get("pageSize").toString()));
        List<SysJobEntity> list = sysJobMapper.listForPage(params);
        PageInfo<SysJobEntity> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Override
    public R save(SysJobEntity sysJobEntity) {
        int count = sysJobMapper.save(sysJobEntity);
        return R.common(count);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Override
    public R update(SysJobEntity sysJobEntity) {
        int count = sysJobMapper.update(sysJobEntity);
        return R.common(count);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Override
    public R remove(Long[] ids) {
        int count = sysJobMapper.batchRemove(ids);
        return R.common(count);
    }

    @Override
    public List<SysJobEntity> findAll() {
        return sysJobMapper.listForPage();
    }

    @Override
    public List<SysJobBO> findSysJob(Map<String, Object> params) {
        List<SysJobEntity> list = sysJobMapper.listForPage(params);
        List<SysJobBO> data = conversion.typeConversion(new SysJobBO(), list);
        return data;
    }

}