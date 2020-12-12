package me.jiahao.modules.system.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.jiahao.exception.R;
import me.jiahao.modules.system.entity.SysDeptEntity;
import me.jiahao.modules.system.entity.bo.SysDeptBO;
import me.jiahao.modules.system.service.SysDeptService;
import me.jiahao.utils.EasyExcelUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;



/**
 * 部门表
 *
 * @author jiahao.pan
 * @email 1342939721@qq.com
 * @date 2020-12-11 14:05:45
 */
@Api(tags = "部门表")
@RestController
@RequestMapping("api/sysdept")
@RequiredArgsConstructor
public class SysDeptController {

    private final SysDeptService sysDeptService;

    /*
     **
     * @Description: 分页查询
     * @Param: [pageQuery]
     * @return: me.jiahao.exception.R
     * @Author: jiahao.pan
     * @Date: 2020-12-11 14:05:45
     */
    @ApiOperation("分页查询")
    @PreAuthorize("@el.check('sysdept:list')")
    @GetMapping
    public R listForPage(@RequestParam Map<String,Object> params) {
        PageInfo<SysDeptEntity> pageInfo = sysDeptService.listForPage(params);
        return R.success(pageInfo);
    }

    /*
     **
     * @Description: 保存
     * @Param: [sysDeptEntity]
     * @return: me.jiahao.exception.R
     * @Author: jiahao.pan
     * @Date: 2020-12-11 14:05:45
     */
    @ApiOperation("保存")
    @PreAuthorize("@el.check('sysdept:list')")
    @PostMapping
    public R save(SysDeptEntity sysDeptEntity) {
        return sysDeptService.save(sysDeptEntity);
    }

    /*
     **
     * @Description: 更新
     * @Param: [sysDeptEntity]
     * @return: me.jiahao.exception.R
     * @Author: jiahao.pan
     * @Date: 2020-12-11 14:05:45
     */
    @ApiOperation("更新")
    @PreAuthorize("@el.check('sysdept:list')")
    @PutMapping
    public R update(SysDeptEntity sysDeptEntity) {
        return sysDeptService.update(sysDeptEntity);
    }

    /*
     **
     * @Description: 删除
     * @Param: [id]
     * @return: me.jiahao.exception.R
     * @Author: jiahao.pan
     * @Date: 2020-12-11 14:05:45
     */
    @ApiOperation("删除")
    @PreAuthorize("@el.check('sysdept:list')")
    @DeleteMapping
    public R remove(@RequestBody Long[] ids) {
        return sysDeptService.remove(ids);
    }

    /*
     **
     * @Description: 导出
     * @Param: []
     * @return: me.jiahao.exception.R
     * @Author: panjiahao
     * @Date: 2020/12/7
     */
    @ApiOperation("导出")
    @PreAuthorize("@el.check('sysdept:list')")
    @PostMapping(value = "export")
    public void exportExcel(@RequestBody Map<String,Object> params,HttpServletResponse response) throws IOException{
        List<SysDeptBO> list = sysDeptService.findSysUser(params);
        // 导出
        EasyExcelUtil.exportExcel(response,SysDeptBO.class,list);
    }

}
