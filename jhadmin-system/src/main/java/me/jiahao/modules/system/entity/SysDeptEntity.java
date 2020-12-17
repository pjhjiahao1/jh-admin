package me.jiahao.modules.system.entity;


import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import me.jiahao.base.BaseEntity;

/**
 * 部门表
 *
 * @author jiahao.pan
 * @email 1342939721@qq.com
 * @date 2020-12-11 14:05:45
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_EMPTY) // json数据中为空不返回
public class SysDeptEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Long id;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 上级id
     */
    private Integer pid;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * '0:启用,1:禁用',
     */
    private Integer status;

    private String pidName;

    private String title;

    private Boolean loading;

    private List<SysDeptEntity> children;

}
