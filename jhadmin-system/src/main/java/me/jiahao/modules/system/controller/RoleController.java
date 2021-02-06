package me.jiahao.modules.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.jiahao.exception.R;
import me.jiahao.logging.annotation.SysOperaLog;
import me.jiahao.modules.system.entity.RoleEntity;
import me.jiahao.modules.system.entity.bo.RoleExcelBO;
import me.jiahao.modules.system.mapper.UserRoleMapper;
import me.jiahao.modules.system.service.RoleService;
import me.jiahao.utils.EasyExcelUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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

    @ApiOperation("查询全部角色 不加权限 主做数据字典")
    @GetMapping(value = "/all")
    public R qurey () {
        List<RoleEntity> allRole = roleService.list();
        return R.success(allRole);
    }

    @ApiOperation("分页查询")
    @PreAuthorize("@el.check('role:list')")
    @GetMapping
    public R listForPage(@RequestParam Map<String,Object> params) {
        return R.success(roleService.listForPage(params));
    }

    @SysOperaLog(descrption = "保存角色")
    @ApiOperation("保存")
    @PreAuthorize("@el.check('role:list')")
    @PostMapping
    public R save(RoleEntity roleEntity) {
        int count = roleService.save(roleEntity);
        return R.common(count);
    }

    @SysOperaLog(descrption = "更新角色")
    @ApiOperation("更新")
    @PreAuthorize("@el.check('role:list')")
    @PutMapping
    public R update(RoleEntity roleEntity) {
        int count = roleService.update(roleEntity);
        return R.common(count);
    }

    @SysOperaLog(descrption = "删除角色")
    @ApiOperation("删除")
    @PreAuthorize("@el.check('role:list')")
    @DeleteMapping
    public R remove(@RequestBody Long[] ids) {
        int count = roleService.batchRemove(ids);
        return R.common(count);
    }

    @SysOperaLog(descrption = "导出角色")
    @ApiOperation("导出")
    @PreAuthorize("@el.check('role:list')")
    @PostMapping(value = "export")
    public void exportExcel(@RequestBody Map<String,Object> params, HttpServletResponse response) throws IOException {
        List<RoleExcelBO> list = roleService.findSysRole(params);
        // 导出
        EasyExcelUtil.exportExcel(response,RoleExcelBO.class,list);
    }

}
