package me.jiahao.generator.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.jiahao.annotation.AnonymousAccess;
import me.jiahao.generator.service.GeneratorTableService;
import lombok.RequiredArgsConstructor;
import me.jiahao.exception.R;
import me.jiahao.utils.PageRequest;
import org.apache.commons.io.IOUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author : panjiahao
 * @date : 13:09 2020/11/23
 */
@Api(tags = "系统：代码生成器配置管理")
@RestController
@RequestMapping(value = "/api/generator")
@RequiredArgsConstructor
public class GeneratorTableController {

    private final GeneratorTableService generatorTableService;

    /*
    **
    * @Description: 分页查询
    * @Param: [pageQuery]
    * @return: me.jiahao.exception.R
    * @Author: panjiahao
    * @Date: 2020/11/25
    */
//    @PreAuthorize("@el.check('kjkj:list')")
    @ApiOperation("分页查询")
    @PreAuthorize("@el.check('generator:list')")
    @GetMapping
    public R listForPage(PageRequest pageQuery) {
        return R.success(generatorTableService.listForPage(pageQuery));
    }

    /*
     **
     * @Description: 生成代码
     * @Param: [tableName, saveUrl, basePackageUrl]
     * @return: java.lang.String
     * @Author: panjiahao
     * @Date: 2020/11/24
     */
    @ApiOperation("下载模板")
    @PreAuthorize("@el.check('generator:list')")
    @PostMapping
    public void createCode(@RequestBody Map<String,Object> params, HttpServletRequest request, HttpServletResponse response) throws IOException {
        byte[] data = generatorTableService.generatorCode(params);
        response.reset();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }




}
