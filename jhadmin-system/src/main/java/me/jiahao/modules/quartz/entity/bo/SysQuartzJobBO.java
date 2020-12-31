package me.jiahao.modules.quartz.entity.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : panjiahao
 * @date : 15:14 2020/12/7
 */
@Data
public class SysQuartzJobBO implements Serializable {

    @ExcelProperty("Spring Bean名称")
    private String beanName;
    @ExcelProperty("cron 表达式")
    private String cronExpression;
    @ExcelProperty("状态：1暂停、0启用")
    private Boolean isPause;
    @ExcelProperty("任务名称")
    private String jobName;
    @ExcelProperty("方法名称")
    private String methodName;
    @ExcelProperty("参数")
    private String params;
    @ExcelProperty("备注")
    private String description;
    @ExcelProperty("负责人")
    private String personInCharge;
    @ExcelProperty("报警邮箱")
    private String email;
    @ExcelProperty("子任务ID")
    private String subTask;
    @ExcelProperty("任务失败后是否暂停")
    private Boolean pauseAfterFailure;

}
