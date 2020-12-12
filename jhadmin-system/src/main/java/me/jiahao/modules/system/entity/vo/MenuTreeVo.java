package me.jiahao.modules.system.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author : panjiahao
 * 菜单树
 * @date : 14:00 2020/10/8
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY) // json数据中为空不返回
public class MenuTreeVo implements Serializable {

    private static final long serialVersionUID = -4665328678737608078L;
    private String title;

    private Boolean checked;

    private List<MenuTreeVo> children;
}
