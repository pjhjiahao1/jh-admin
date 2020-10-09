package me.jiahao.modules.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import me.jiahao.exception.R;
import me.jiahao.modules.system.entity.vo.MenuTreeVo;
import me.jiahao.modules.system.service.RoleMenuService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author : panjiahao
 * @date : 10:28 2020/10/9
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rolemenu")
public class RoleMenuController {

    private final RoleMenuService roleMenuService;
    /*
    **
    * @Description: 保存菜单关联权限
    * @Param: []
    * @return: me.jiahao.exception.R
    * @Author: panjiahao
    * @Date: 2020/10/9
    */
    @PreAuthorize("@el.check('role:list')")
    @PostMapping
    public R save (@RequestBody Map<String,Object> data) {
        Integer roleId = (Integer) data.get("roleid");
        String json = JSON.toJSONString(data.get("rolemenu"));
        List<MenuTreeVo> menuList = JSON.parseArray(json, MenuTreeVo.class);
        return roleMenuService.save(roleId.longValue(),menuList);
    }
}
