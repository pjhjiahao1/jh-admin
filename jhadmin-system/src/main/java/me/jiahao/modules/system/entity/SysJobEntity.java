package me.jiahao.modules.system.entity;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import me.jiahao.base.BaseEntity;

/**
 * 岗位表
 *
 * @author jiahao.pan
 * @email 1342939721@qq.com
 * @date 2020-12-16 17:01:04
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY) // json数据中为空不返回
public class SysJobEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     *
     */
    private Integer id;

    /**
     * 岗位名称
     */
    private String name;

    /**
     * '0:启用,1:禁用',
     */
    private Integer status;


}
