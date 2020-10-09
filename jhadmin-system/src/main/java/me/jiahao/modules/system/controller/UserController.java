package me.jiahao.modules.system.controller;

import lombok.RequiredArgsConstructor;
import me.jiahao.exception.R;
import me.jiahao.modules.system.entity.UserEntity;
import me.jiahao.modules.system.service.UserService;
import me.jiahao.utils.PageRequest;
import org.hibernate.annotations.Parameter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author : panjiahao
 * @date : 15:23 2020/9/24
 */

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("@el.check('user:list')")
    @GetMapping
    public R listForPage(PageRequest pageQuery) {
        return R.success(userService.listForPage(pageQuery));
    }
    
    /*
    **
    * @Description: 按钮权限 后续优化
    * @Param: [userEntity]
    * @return: me.jiahao.exception.R
    * @Author: panjiahao
    * @Date: 2020/10/2
    */
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
    @PreAuthorize("@el.check('user:list')")
    @DeleteMapping
    public R remove(@RequestParam(value = "id") String id) {
        return userService.remove(id);
    }

}
