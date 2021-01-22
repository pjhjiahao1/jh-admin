package me.jiahao.modules.quartz.entity.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : panjiahao
 * @date : 15:14 2020/12/7
 */
@Data
public class SysQuartzLogBO implements Serializable {

    @ExcelProperty("ID")
    private Integer logId;
    @ExcelProperty("")
    private String beanName;
    @ExcelProperty("")
    private String cronExpression;
    @ExcelProperty("")
    private String exceptionDetail;
    @ExcelProperty("")
    private Integer isSuccess;
    @ExcelProperty("")
    private String jobName;
    @ExcelProperty("")
    private String methodName;
    @ExcelProperty("")
    private String params;
    @ExcelProperty("")
    private Integer time;
    @ExcelProperty("quartz表id")
    private Integer pid;

}
