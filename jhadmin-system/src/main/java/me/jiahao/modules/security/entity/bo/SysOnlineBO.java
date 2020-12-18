package me.jiahao.modules.security.entity.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : panjiahao
 * @date : 15:14 2020/12/7
 */
@Data
public class SysOnlineBO implements Serializable {
    private static final long serialVersionUID = -5202157441412206306L;
    @ExcelProperty("用户名")
    private String userName;
    @ExcelProperty("昵称")
    private String nickName;
    @ExcelProperty("部门")
    private String dept;
    @ExcelProperty("浏览器")
    private String browser;
    @ExcelProperty("ip地址")
    private String ip;
    @ExcelProperty("地址")
    private String address;
    @ExcelProperty("登录时间")
    private Date loginTime;

}
