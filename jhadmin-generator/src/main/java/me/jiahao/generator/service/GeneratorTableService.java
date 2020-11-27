package me.jiahao.generator.service;

import com.github.pagehelper.PageInfo;
import me.jiahao.generator.entity.GeneratorTableEntity;
import me.jiahao.utils.PageRequest;

import java.util.Map;

/**
 * @author : panjiahao
 * @date : 13:07 2020/11/23
 */
public interface GeneratorTableService {
    PageInfo<GeneratorTableEntity> listForPage(PageRequest pageQuery);
    byte[] generatorCode(Map<String,Object> params);
}
