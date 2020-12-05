package me.jiahao.modules.system.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.jiahao.exception.R;
import me.jiahao.modules.system.entity.UserEntity;
import me.jiahao.modules.system.service.UserService;
import me.jiahao.utils.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation("更新")
    @PreAuthorize("@el.check('user:list')")
    @PutMapping
    public R update(UserEntity userEntity) {
        if (userEntity.getUsername().equals("admin")) {
            return R.error("不允许编辑管理员");
        }
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
    @ApiOperation("删除")
    @PreAuthorize("@el.check('user:list')")
    @DeleteMapping
    public R remove(@RequestBody Long[] ids) {
        for (Long l : ids) {
            if (l == 2) {
                return R.error("不允许删除管理员");
            }
        }
        return userService.remove(ids);
    }

}
