package me.jiahao.modules.system.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.jiahao.exception.R;
import me.jiahao.logging.annotation.SysOperaLog;
import me.jiahao.modules.system.entity.SysDeptEntity;
import me.jiahao.modules.system.entity.bo.SysDeptBO;
import me.jiahao.modules.system.service.SysDeptService;
import me.jiahao.utils.EasyExcelUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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
    @Value("${spring.profiles.active}")
    private String active;
    /*
     **
     * @Description: 分页查询
     * @Param: [pageQuery]
     * @return: me.jiahao.exception.R
     * @Author: jiahao.pan
     * @Date: 2020-12-11 14:05:45
     */
    @SysOperaLog(descrption = "查询部门")
    @ApiOperation("分页查询")
    @PreAuthorize("@el.check('sysdept:list')")
    @GetMapping
    public R listForPage(@RequestParam Map<String,Object> params) {
        PageInfo<SysDeptEntity> pageInfo = sysDeptService.listForPage(params);
        return R.success(pageInfo);
    }

    @ApiOperation("查询")
    @PreAuthorize("@el.check('sysdept:list')")
    @GetMapping(value = "findAll")
    public R findAll(@RequestParam Map<String,Object> params) {
        List<SysDeptEntity> findAll = sysDeptService.findAll(params);
        return R.success(findAll);
    }

    @ApiOperation("查询")
    @PreAuthorize("@el.check('sysdept:list')")
    @GetMapping(value = "getTreeData")
    public R getTreeData(@RequestParam Map<String,Object> params) {
        List<SysDeptEntity> findAll = sysDeptService.getTreeData(params);
        return R.success(findAll);
    }

    /*
     **
     * @Description: 保存
     * @Param: [sysDeptEntity]
     * @return: me.jiahao.exception.R
     * @Author: jiahao.pan
     * @Date: 2020-12-11 14:05:45
     */
    @SysOperaLog(descrption = "保存部门")
    @ApiOperation("保存")
    @PreAuthorize("@el.check('sysdept:list')")
    @PostMapping
    public R save(SysDeptEntity sysDeptEntity) {
        if (active.equals("prod")) {
            return R.error("演示环境不可操作!");
        }
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
    @SysOperaLog(descrption = "更新部门")
    @ApiOperation("更新")
    @PreAuthorize("@el.check('sysdept:list')")
    @PutMapping
    public R update(SysDeptEntity sysDeptEntity) {
        if (active.equals("prod")) {
            return R.error("演示环境不可操作!");
        }
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
    @SysOperaLog(descrption = "删除部门")
    @ApiOperation("删除")
    @PreAuthorize("@el.check('sysdept:list')")
    @DeleteMapping
    public R remove(@RequestBody Long[] ids) {
        if (active.equals("prod")) {
            return R.error("演示环境不可操作!");
        }
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
    @SysOperaLog(descrption = "导出部门")
    @ApiOperation("导出")
    @PreAuthorize("@el.check('sysdept:list')")
    @PostMapping(value = "export")
    public void exportExcel(@RequestBody Map<String,Object> params,HttpServletResponse response) throws IOException{
        List<SysDeptBO> list = sysDeptService.findSysUser(params);
        // 导出
        EasyExcelUtil.exportExcel(response,SysDeptBO.class,list);
    }

}
