package me.jiahao.modules.system.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author : panjiahao
 * 菜单
 * @date : 19:03 2020/9/22
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY) // json数据中为空不返回
public class Menuvo implements Serializable {
    private static final long serialVersionUID = 2905495732016091666L;
    private String path;
    private String name;
    private String redirect;
    private String component;
    private MenuMetaVo meta;
    private List<Menuvo> children;
}
