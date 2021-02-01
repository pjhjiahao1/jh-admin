package me.jiahao.modules.system.service;

import me.jiahao.exception.R;
import com.github.pagehelper.PageInfo;
import me.jiahao.modules.system.entity.SysDictEntity;
import me.jiahao.modules.system.entity.bo.SysDictBO;

import java.util.List;
import java.util.Map;


/**
 * 数据字典
 *
 * @author jiahao.pan
 * @email 1342939721@qq.com
 * @date 2021-01-27 07:38:46
 */
public interface SysDictService {

    List<SysDictEntity> listForPage(Map<String,Object> params);

    List<SysDictEntity> findAll(Map<String,Object> params);

    R save (SysDictEntity sysDictEntity);

    R update(SysDictEntity sysDictEntity);

    R remove(Long[] id);

    List<SysDictBO> findSysDict(Map<String,Object> params);

    int batchRemoveChild(Long[] ids);

    List<SysDictEntity> getDictDetail(String code);
}



