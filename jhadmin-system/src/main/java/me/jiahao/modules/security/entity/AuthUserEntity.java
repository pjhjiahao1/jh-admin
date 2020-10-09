package me.jiahao.modules.security.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author : panjiahao
 * @date : 15:03 2020/9/18
 */
@Getter
@Setter
public class AuthUserEntity {

    private String username;

    private String password;

    private String captcode;

    private String uuid;
}
