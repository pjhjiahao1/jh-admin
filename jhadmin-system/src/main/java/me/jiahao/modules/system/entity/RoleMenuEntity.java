package me.jiahao.modules.system.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author : panjiahao
 * @date : 11:13 2020/10/9
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY) // json数据中为空不返回
public class RoleMenuEntity implements Serializable {
    private Long id;
    private Long roleId;
    private Long MenuId;
}
