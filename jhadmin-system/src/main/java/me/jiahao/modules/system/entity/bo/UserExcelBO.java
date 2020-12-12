package me.jiahao.modules.system.entity.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : panjiahao
 * @date : 15:14 2020/12/7
 */
@Data
public class UserExcelBO implements Serializable {

    private static final long serialVersionUID = 5312425919561141061L;

    @ExcelProperty("用户名")
    private String username;

    @ExcelProperty("禁用")
    private int enabled;

    @ExcelProperty("联系电话")
    private String phone;

    @ExcelProperty("邮箱")
    private String email;

    @ExcelProperty("昵称")
    private String nickName;

    @ExcelProperty("性别")
    private String gender;

    @ExcelProperty("部门id")
    private Long orgId;

    @ExcelProperty("角色id")
    private Long roleId;

    @ExcelProperty("角色名称")
    private String roleName;

}
