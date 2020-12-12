package me.jiahao.modules.security.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author : panjiahao
 * @date : 16:13 2020/9/18
 */
@Getter
@Setter
public class UserInfoEntity implements Serializable {
    private static final long serialVersionUID = 6887578366687827289L;
    private String username;
    private String password;
}
