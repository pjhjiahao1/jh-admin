package me.jiahao.modules.system.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import me.jiahao.base.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author : panjiahao
 * @date : 15:56 2020/9/22
 */
@Getter
@Setter
public class MenuEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -2799068742108381436L;
    private Long id;

    private List<MenuEntity> children;

    // 上级id
    private Long menuPid;

    private Integer isLeaf;

    private String menuName;

    private String url;

    private String icon;

    private Integer sort;

    private Integer level;

    private Integer status;

    private String permission;

    private String menuCode;

    private String component;

    private String menuPidName;
    // 构建菜单树使用 当前用户是否有权限 true：有 false：无

    private Boolean authMenu = false;

    private String title;

    private Boolean loading;

}
