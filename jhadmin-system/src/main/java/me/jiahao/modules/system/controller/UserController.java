package me.jiahao.modules.system.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.jiahao.exception.R;
import me.jiahao.logging.annotation.SysOperaLog;
import me.jiahao.modules.system.entity.UserEntity;
import me.jiahao.modules.system.entity.bo.UserExcelBO;
import me.jiahao.modules.system.service.UserService;
import me.jiahao.utils.EasyExcelUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author : panjiahao
 * @date : 15:23 2020/9/24
 */
@Api(tags = "系统：用户管理")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @ApiOperation("分页查询")
    @PreAuthorize("@el.check('user:list')")
    @GetMapping
    public R listForPage(@RequestParam Map<String,Object> params) {
        PageInfo<UserEntity> pageInfo = userService.listForPage(params);
        return R.success(pageInfo);
    }
    
    /*
    **
    * @Description: 按钮权限 后续优化
    * @Param: [userEntity]
    * @return: me.jiahao.exception.R
    * @Author: panjiahao
    * @Date: 2020/10/2
    */
    @SysOperaLog(descrption = "保存用户")
    @ApiOperation("保存")
    @PreAuthorize("@el.check('user:list')")
    @PostMapping
    public R save(UserEntity userEntity) {
        return userService.save(userEntity);
    }

    /*
     **
     * @Description: 按钮权限 后续优化
     * @Param: [userEntity]
     * @return: me.jiahao.exception.R
     * @Author: panjiahao
     * @Date: 2020/10/2
     */
    @SysOperaLog(descrption = "更新用户")
    @ApiOperation("更新")
    @PreAuthorize("@el.check('user:list')")
    @PutMapping
    public R update(UserEntity userEntity) {
        return userService.update(userEntity);
    }

    /*
     **
     * @Description: 按钮权限 后续优化
     * @Param: [userEntity]
     * @return: me.jiahao.exception.R
     * @Author: panjiahao
     * @Date: 2020/10/2
     */
    @SysOperaLog(descrption = "删除用户")
    @ApiOperation("删除")
    @PreAuthorize("@el.check('user:list')")
    @DeleteMapping
    public R remove(@RequestBody Long[] ids) {
        return userService.remove(ids);
    }

    /*
    **
    * @Description: 导出
    * @Param: []
    * @return: me.jiahao.exception.R
    * @Author: panjiahao
    * @Date: 2020/12/7
    */
    @SysOperaLog(descrption = "导出用户")
    @ApiOperation("导出")
    @PreAuthorize("@el.check('user:list')")
    @PostMapping(value = "export")
    public void exportExcel(@RequestBody Map<String,Object> params,HttpServletResponse response) throws IOException{
        List<UserExcelBO> list = userService.findSysUser(params);
        // 导出
        EasyExcelUtil.exportExcel(response,UserExcelBO.class,list);
    }

}
