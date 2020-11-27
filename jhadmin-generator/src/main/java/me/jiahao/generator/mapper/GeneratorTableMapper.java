package me.jiahao.generator.mapper;

import me.jiahao.generator.entity.GeneratorTableEntity;
import me.jiahao.base.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @author : panjiahao
 * @date : 13:03 2020/11/23
 */
public interface GeneratorTableMapper extends BaseMapper<GeneratorTableEntity> {

    Map<String, String> queryTable(String tableName);

    List<Map<String, String>> queryColumns(String tableName);

}
