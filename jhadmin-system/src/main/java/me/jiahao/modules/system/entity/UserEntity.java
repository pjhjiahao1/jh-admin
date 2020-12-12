package me.jiahao.modules.system.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.jiahao.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : panjiahao
 * @date : 15:14 2020/9/24
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY) // json数据中为空不返回
public class UserEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 2988029030564859565L;
    private Long id;
    private String username;
    private int enabled;
    private String phone;
    private String email;
    private String nickName;
    private String gender;
    private String password;
    private Long orgId;
    private Long roleId;
    private String roleName;

}
