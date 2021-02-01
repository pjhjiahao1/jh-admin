package me.jiahao.modules.system.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import me.jiahao.exception.R;
import me.jiahao.modules.system.entity.SysDeptEntity;
import me.jiahao.utils.Conversion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import me.jiahao.modules.system.entity.SysDictEntity;
import me.jiahao.modules.system.mapper.SysDictMapper;
import me.jiahao.modules.system.service.SysDictService;
import me.jiahao.modules.system.entity.bo.SysDictBO;


@Service
@RequiredArgsConstructor
public class SysDictServiceImpl implements SysDictService {

    private final SysDictMapper sysDictMapper;
    private final Conversion conversion;

    @Override
    public List<SysDictEntity> listForPage(Map<String,Object> params) {
        List<SysDictEntity> list = sysDictMapper.listForPage(params);
        for (SysDictEntity s : list) {
            if (s.getType() == 0) {
                s.setHasChild("目录");
            }
        }
        return list;
    }

    @Override
    public List<SysDictEntity> findAll(Map<String, Object> params) {
        List<SysDictEntity> sysDictEntities = sysDictMapper.listForPage(params);
        for (SysDictEntity s : sysDictEntities) {
            s.setTitle(s.getName());
            s.setLoading(false);
            s.setChildren(new ArrayList<>());
        }
        return sysDictEntities;
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Override
    public R save(SysDictEntity sysDictEntity) {
        int count = sysDictMapper.save(sysDictEntity);
        return R.common(count);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Override
    public R update(SysDictEntity sysDictEntity) {
        int count = sysDictMapper.update(sysDictEntity);
        return R.common(count);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Override
    public R remove(Long[] ids) {
        int count = sysDictMapper.batchRemove(ids);
        batchRemoveChild(ids);
        return R.common(count);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Override
    public int batchRemoveChild(Long[] ids) {
        return sysDictMapper.batchRemoveChild(ids);
    }

    @Override
    public List<SysDictBO> findSysDict(Map<String, Object> params) {
        List<SysDictEntity> list = sysDictMapper.listForPage(params);
        List<SysDictBO> data = conversion.typeConversion(new SysDictBO(), list);
        return data;
    }

    @Override
    public List<SysDictEntity> getDictDetail(String code) {
        return sysDictMapper.getDictDetail(code);
    }
}