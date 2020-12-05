package me.jiahao.modules.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.jiahao.exception.R;
import me.jiahao.modules.system.entity.RoleEntity;
import me.jiahao.modules.system.mapper.UserRoleMapper;
import me.jiahao.modules.system.service.RoleService;
import me.jiahao.utils.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author : panjiahao
 * @date : 15:52 2020/9/30
 */
@Api(tags = "系统：角色管理")
@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;
    private final UserRoleMapper userRoleMapper;


    /*
    **
    * @Description: 查询全部角色 不加权限 主做数据字典
    * @Param: []
    * @return: me.jiahao.exception.R
    * @Author: panjiahao
    * @Date: 2020/9/30
    */
    @ApiOperation("查询全部角色 不加权限 主做数据字典")
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
    @ApiOperation("分页查询")
    @PreAuthorize("@el.check('role:list')")
    @GetMapping
    public R listForPage(PageRequest pageQuery) {
        return R.success(roleService.listForPage(pageQuery));
    }
    /*
    **
    * @Description: 保存
    * @Param: [roleEntity]
    * @return: me.jiahao.exception.R
    * @Author: panjiahao
    * @Date: 2020/10/10
    */
    @ApiOperation("保存")
    @PreAuthorize("@el.check('role:list')")
    @PostMapping
    public R save(RoleEntity roleEntity) {
        int count = roleService.save(roleEntity);
        return R.common(count);
    }

    @ApiOperation("更新")
    @PreAuthorize("@el.check('role:list')")
    @PutMapping
    public R update(RoleEntity roleEntity) {
        int count = roleService.update(roleEntity);
        return R.common(count);
    }

    @ApiOperation("删除")
    @PreAuthorize("@el.check('role:list')")
    @DeleteMapping
    public R remove(@RequestParam(value = "id") Long id) {
        int res = userRoleMapper.getUserRole(id);
        if (res > 0) {
            return R.warn("有用户关联此角色暂不能删除");
        }
        int count = roleService.remove(id);
        return R.common(count);
    }

}
