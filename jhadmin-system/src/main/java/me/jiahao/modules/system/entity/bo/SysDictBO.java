package me.jiahao.modules.system.entity.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : panjiahao
 * @date : 15:14 2020/12/7
 */
@Data
public class SysDictBO implements Serializable {

    @ExcelProperty("ID")
    private Long id;
    @ExcelProperty("字典名称")
    private String name;
    @ExcelProperty("值")
    private String value;
    @ExcelProperty("描述")
    private String description;
    @ExcelProperty("排序")
    private Integer dictSort;
    @ExcelProperty("上级")
    private Integer parent;

}
