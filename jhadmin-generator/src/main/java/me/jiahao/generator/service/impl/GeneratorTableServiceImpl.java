package me.jiahao.generator.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.jiahao.generator.entity.GeneratorTableEntity;
import me.jiahao.generator.mapper.GeneratorTableMapper;
import me.jiahao.generator.service.GeneratorTableService;
import lombok.RequiredArgsConstructor;
import me.jiahao.generator.util.GenUtils;
import me.jiahao.utils.PageRequest;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * @author : panjiahao
 * @date : 13:07 2020/11/23
 */
@Service
@RequiredArgsConstructor
public class GeneratorTableServiceImpl implements GeneratorTableService {

    private final GeneratorTableMapper generatorTableMapper;

    @Override
    public PageInfo<GeneratorTableEntity> listForPage(Map<String,Object> params) {
        PageHelper.startPage(Integer.parseInt(params.get("currentPage").toString()), Integer.parseInt(params.get("pageSize").toString()));
        List<GeneratorTableEntity> sysRoles = generatorTableMapper.listForPage(params);
        PageInfo<GeneratorTableEntity> pageInfo = new PageInfo<>(sysRoles);
        return pageInfo;
    }

    @Override
    public byte[] generatorCode(Map<String,Object> params) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        String tableNames = (String) params.get("tableName");
        //查询表信息
        Map<String, String> table = generatorTableMapper.queryTable(tableNames);
        //查询列信息
        List<Map<String, String>> columns = generatorTableMapper.queryColumns(tableNames);
        //生成代码
        GenUtils.generatorCode(table, columns, zip , params);
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }
}
