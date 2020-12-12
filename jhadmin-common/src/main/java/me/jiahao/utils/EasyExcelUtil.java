package me.jiahao.utils;

import com.alibaba.excel.EasyExcel;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author : panjiahao
 * @date : 16:34 2020/12/9
 */
public class EasyExcelUtil {

    public static void exportExcel(HttpServletResponse response, Class data, List list) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        response.reset();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        EasyExcel.write(response.getOutputStream(), data).sheet("").doWrite(list);
    }
}
