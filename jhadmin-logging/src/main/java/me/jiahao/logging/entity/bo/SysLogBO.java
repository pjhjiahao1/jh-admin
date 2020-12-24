package me.jiahao.logging.entity.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : panjiahao
 * @date : 15:14 2020/12/7
 */
@Data
public class SysLogBO implements Serializable {

    @ExcelProperty("用户名")
    private String userName;
    @ExcelProperty("IP")
    private String ip;
    @ExcelProperty("操作地点")
    private String location;
    @ExcelProperty("操作描述")
    private String description;
    @ExcelProperty("浏览器")
    private String browser;
    @ExcelProperty("开始时间")
    private Date startTime;
    @ExcelProperty("完成时间")
    private Date finishTime;
    @ExcelProperty("消耗时间")
    private Date consumingTime;

}
