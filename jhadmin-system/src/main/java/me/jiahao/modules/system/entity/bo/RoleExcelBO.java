package me.jiahao.modules.system.entity.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : panjiahao
 * @date : 16:51 2020/12/9
 */
@Data
public class RoleExcelBO implements Serializable {

    private static final long serialVersionUID = -220902517621958215L;

    @ExcelProperty("角色名称")
    private String roleName;
    @ExcelProperty("角色描述")
    private String roleDesc;
    @ExcelProperty("角色编码")
    private String roleCode;

}
