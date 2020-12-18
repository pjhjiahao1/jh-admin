package me.jiahao.modules.security.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import me.jiahao.exception.R;
import me.jiahao.modules.security.entity.SysOnlineEntity;
import me.jiahao.modules.security.entity.bo.SysOnlineBO;
import me.jiahao.modules.security.mapper.SysOnlineMapper;
import me.jiahao.modules.security.service.SysOnlineService;
import me.jiahao.utils.Conversion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class SysOnlineServiceImpl implements SysOnlineService {

    private final SysOnlineMapper sysOnlineMapper;
    private final Conversion conversion;

    @Override
    public PageInfo<SysOnlineEntity> listForPage(Map<String,Object> params) {
        PageHelper.startPage(Integer.parseInt(params.get("currentPage").toString()), Integer.parseInt(params.get("pageSize").toString()));
        List<SysOnlineEntity> list = sysOnlineMapper.listForPage(params);
        PageInfo<SysOnlineEntity> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Override
    public R save(SysOnlineEntity sysOnlineEntity) {
        int count = sysOnlineMapper.save(sysOnlineEntity);
        return R.common(count);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Override
    public R update(SysOnlineEntity sysOnlineEntity) {
        int count = sysOnlineMapper.update(sysOnlineEntity);
        return R.common(count);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Override
    public R remove(Long[] ids) {
        int count = sysOnlineMapper.batchRemove(ids);
        return R.common(count);
    }

    @Override
    public List<SysOnlineBO> findSysOnline(Map<String, Object> params) {
        List<SysOnlineEntity> list = sysOnlineMapper.listForPage(params);
        List<SysOnlineBO> data = conversion.typeConversion(new SysOnlineBO(), list);
        return data;
    }

}