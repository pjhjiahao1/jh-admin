package me.jiahao.modules.security.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : panjiahao
 * @date : 16:46 2020/10/7
 */
@Getter
@Setter
public class OnlineUserEntity implements Serializable {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 岗位
     */
    private String dept;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * IP
     */
    private String ip;

    /**
     * 地址
     */
    private String address;

    /**
     * 登录时间
     */
    private Date loginTime;
}
