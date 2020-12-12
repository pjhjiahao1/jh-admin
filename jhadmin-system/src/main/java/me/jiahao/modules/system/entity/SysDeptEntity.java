package me.jiahao.modules.system.entity;


import java.io.Serializable;

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
@JsonInclude(JsonInclude.Include.NON_EMPTY) // json数据中为空不返回
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
    private Long pid;
    /**
     * 排序
     */
    private Long sort;
    /**
     * '0:启用,1:禁用',
     */
    private Long status;


}
