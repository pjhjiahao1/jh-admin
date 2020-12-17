package me.jiahao.modules.system.entity.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : panjiahao
 * @date : 15:14 2020/12/7
 */
@Data
public class SysJobBO implements Serializable {

    @ExcelProperty("")
    private Integer id;
    @ExcelProperty("岗位名称")
    private String name;
    @ExcelProperty("'0:启用,1:禁用',")
    private Integer status;

}
