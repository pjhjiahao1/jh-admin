package me.jiahao.modules.system.mapper;

import me.jiahao.base.BaseMapper;
import me.jiahao.modules.system.entity.SysDictEntity;

import java.util.List;

/**
 * 数据字典
 * 
 * @author jiahao.pan
 * @email 1342939721@qq.com
 * @date 2021-01-27 07:38:46
 */
public interface SysDictMapper extends BaseMapper<SysDictEntity> {
	int batchRemoveChild(Long[] ids);

	List<SysDictEntity> getDictDetail(String code);
}
