package me.jiahao.generator.controller;

import me.jiahao.annotation.AnonymousAccess;
import me.jiahao.generator.service.GeneratorTableService;
import lombok.RequiredArgsConstructor;
import me.jiahao.exception.R;
import me.jiahao.utils.PageRequest;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * @author : panjiahao
 * @date : 13:09 2020/11/23
 */
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
