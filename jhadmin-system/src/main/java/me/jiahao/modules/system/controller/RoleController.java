package me.jiahao.modules.system.controller;

import lombok.RequiredArgsConstructor;
import me.jiahao.exception.R;
import me.jiahao.modules.system.entity.RoleEntity;
import me.jiahao.modules.system.service.RoleService;
import me.jiahao.utils.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : panjiahao
 * @date : 15:52 2020/9/30
 */
@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    /*
    **
    * @Description: 查询全部角色 不加权限 主做数据字典
    * @Param: []
    * @return: me.jiahao.exception.R
    * @Author: panjiahao
    * @Date: 2020/9/30
    */
    @GetMapping(value = "/all")
    public R qurey () {
        List<RoleEntity> allRole = roleService.getAllRole();
        return R.success(allRole);
    }
    /*
    **
    * @Description: 分页查询
    * @Param: [pageQuery]
    * @return: me.jiahao.exception.R
    * @Author: panjiahao
    * @Date: 2020/10/8
    */
    @PreAuthorize("@el.check('role:list')")
    @GetMapping
    public R listForPage(PageRequest pageQuery) {
        return R.success(roleService.listForPage(pageQuery));
    }

}
