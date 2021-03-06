package me.jiahao.modules.system.entity.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : panjiahao
 * @date : 15:14 2020/12/7
 */
@Data
public class SysDeptBO implements Serializable {

    @ExcelProperty("")
    private Integer id;
    @ExcelProperty("部门名称")
    private String deptName;
    @ExcelProperty("上级id")
    private Integer pid;
    @ExcelProperty("排序")
    private Integer sort;
    @ExcelProperty("'0:启用,1:禁用',")
    private Integer status;

}
