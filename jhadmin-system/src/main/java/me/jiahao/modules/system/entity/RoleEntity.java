package me.jiahao.modules.system.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import me.jiahao.base.BaseEntity;

import java.io.Serializable;

/**
 * @author : panjiahao
 * @date : 15:58 2020/9/30
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY) // json数据中为空不返回
public class RoleEntity extends BaseEntity implements Serializable {
    private Long id;
    private String roleName;
    private String roleDesc;
    private String roleCode;
    private int sort;
    private int status;


}
