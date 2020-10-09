package me.jiahao.modules.system.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author : panjiahao
 * @date : 19:05 2020/9/22
 */
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY) // json数据中为空不返回
public class MenuMetaVo implements Serializable {
    private String icon;
    private String title;
    private boolean notCache;
    private boolean hideInMenu;
    private List<String> access;
    // 只有一个子菜单设置true
    private Boolean showAlways;
    public MenuMetaVo() {

    }
}
