package me.jiahao.modules.security.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : panjiahao
 * @date : 16:46 2020/10/7
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY) // json数据中为空不返回
public class SysOnlineEntity implements Serializable {

    private static final long serialVersionUID = 5773006853701599525L;


    /**
     *
     */
    private Long id;


    /**
     * 用户名
     */
    private String userName;


    /**
     * 昵称
     */
    private String nickName;


    /**
     * 部门
     */
    private String dept;


    /**
     * 浏览器
     */
    private String browser;


    /**
     * ip地址
     */
    private String ip;


    /**
     * 地址
     */
    private String address;


    /**
     * token
     */
    private String key;


    /**
     * 登录时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date loginTime;

}
