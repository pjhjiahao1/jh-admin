package me.jiahao.modules.system.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import me.jiahao.exception.R;
import me.jiahao.modules.system.entity.bo.UserExcelBO;
import me.jiahao.modules.system.service.SysDeptService;
import me.jiahao.utils.Conversion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import me.jiahao.modules.system.entity.SysDeptEntity;
import me.jiahao.modules.system.mapper.SysDeptMapper;
import me.jiahao.modules.system.entity.bo.SysDeptBO;

@Service(value = "sysDeptService")
@RequiredArgsConstructor
public class SysDeptServiceImpl implements SysDeptService {

    private final SysDeptMapper sysDeptMapper;
    private final Conversion conversion;

    @Override
    public PageInfo<SysDeptEntity> listForPage(Map<String,Object> params) {
        PageHelper.startPage(Integer.parseInt(params.get("currentPage").toString()), Integer.parseInt(params.get("pageSize").toString()));
        List<SysDeptEntity> list = sysDeptMapper.listForPage(params);
        PageInfo<SysDeptEntity> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Override
    public R save(SysDeptEntity sysDeptEntity) {
        int count = sysDeptMapper.save(sysDeptEntity);
        return R.common(count);
    }

    @Override
    public R update(SysDeptEntity sysDeptEntity) {
        int count = sysDeptMapper.update(sysDeptEntity);
        return R.common(count);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Override
    public R remove(Long[] ids) {
        int count = sysDeptMapper.batchRemove(ids);
        return R.common(count);
    }

    @Override
    public List<SysDeptBO> findSysUser(Map<String, Object> params) {
        List<SysDeptEntity> list = sysDeptMapper.listForPage(params);
        List<SysDeptBO> userCheckDataList = conversion.typeConversion(new UserExcelBO(), list);
        return userCheckDataList;
    }


}