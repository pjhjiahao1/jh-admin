package me.jiahao.modules.system.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author : panjiahao
 * @date : 16:41 2020/9/30
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY) // json数据中为空不返回
public class UserRoleEntity implements Serializable {

    private static final long serialVersionUID = -6950357144319839025L;
    private Long id;
    private Long roleId;
    private Long userId;
}
