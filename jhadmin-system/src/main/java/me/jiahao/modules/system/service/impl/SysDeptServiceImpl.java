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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

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
        Map<String,Object> params = new HashMap<>();
        params.put("id",sysDeptEntity.getPid());
        List<SysDeptEntity> sysDeptEntities = sysDeptMapper.listForPage(params);
        sysDeptEntity.setPidName(sysDeptEntities.get(0).getDeptName());
        int count = sysDeptMapper.save(sysDeptEntity);
        return R.common(count);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
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

    @Override
    public List<SysDeptEntity> findAll(Map<String, Object> params) {
        return sysDeptMapper.listForPage(params);
    }

    @Override
    public List<SysDeptEntity> getTreeData(Map<String, Object> params) {
        List<SysDeptEntity> sysDeptEntities = sysDeptMapper.listForPage(params);
        for (SysDeptEntity s : sysDeptEntities) {
            s.setTitle(s.getDeptName());
            s.setLoading(false);
            s.setChildren(new ArrayList<>());
        }
        return sysDeptEntities;
    }

    public List<Long> getRecursiveDeptForParams(List<SysDeptEntity> sysDeptEntitys,List<Long> idLong) {
        Map<String,Object> params = new HashMap<>();
        List<Long> paramsLong = new ArrayList<>();
        Stream<SysDeptEntity> s = sysDeptEntitys.stream();
        s.forEach(ele -> {
            paramsLong.add(ele.getId());
            idLong.add(ele.getId());
        });
        s.close();
        params.put("array",paramsLong);
        List<SysDeptEntity> sysList = sysDeptMapper.listForPage(params);
        if (!sysList.isEmpty()) {
            getRecursiveDeptForParams(sysList,idLong);
        }
        return idLong;
    }

    @Override
    public List<Long> getRecursiveDept(Long id) {
        Map<String,Object> params = new HashMap<>();
        params.put("pid",id);
        List<Long> idLong = new ArrayList<>();
        idLong.add(id);
        List<SysDeptEntity> sysDeptEntitys = sysDeptMapper.listForPage(params);
        if (!sysDeptEntitys.isEmpty()) {
            List<Long> dept = getRecursiveDeptForParams(sysDeptEntitys,idLong);
            return dept;
        }
        return idLong;
    }
}