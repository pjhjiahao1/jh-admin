package me.jiahao.modules.security.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author : panjiahao
 * @date : 15:03 2020/9/18
 */
@Getter
@Setter
public class AuthUserEntity implements Serializable {

    private static final long serialVersionUID = 3202908217544996655L;
    private String username;

    private String password;

    private String captcode;

    private String uuid;
}
