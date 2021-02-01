package me.jiahao.modules.system.entity;


import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import me.jiahao.base.BaseEntity;

/**
 * 数据字典
 *
 * @author jiahao.pan
 * @email 1342939721@qq.com
 * @date 2021-01-27 07:38:46
 */
@Data
public class SysDictEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * ID
     */
    private Long id;


    /**
     * 字典名称
     */
    private String name;


    /**
     * 值
     */
    private String value;


    /**
     * 描述
     */
    private String description;


    /**
     * 排序
     */
    private Integer dictSort;


    /**
     * 上级
     */
    private Integer parent;
    /**
     * 上级名称
     */
    private String parentName;
    /**
     * 类型 0 目录 1 参数
     */
    private Integer type;

    private String title;

    private Boolean loading;

    private List<SysDictEntity> children;

    private String hasChild;


}
