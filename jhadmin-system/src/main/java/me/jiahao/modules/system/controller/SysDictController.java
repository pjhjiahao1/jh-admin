package me.jiahao.modules.system.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.jiahao.exception.R;
import me.jiahao.logging.annotation.SysOperaLog;
import me.jiahao.modules.system.entity.SysDictEntity;
import me.jiahao.modules.system.service.SysDictService;
import me.jiahao.modules.system.entity.bo.SysDictBO;

import me.jiahao.utils.EasyExcelUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;



/**
 * 数据字典
 *
 * @author jiahao.pan
 * @email 1342939721@qq.com
 * @date 2021-01-27 07:38:46
 */
@Api(tags = "数据字典")
@RestController
@RequestMapping("api/sysdict")
@RequiredArgsConstructor
public class SysDictController {

    private final SysDictService sysDictService;

    /*
     **
     * @Description: 分页查询
     * @Param: [pageQuery]
     * @return: me.jiahao.exception.R
     * @Author: jiahao.pan
     * @Date: 2021-01-27 07:38:46
     */
    @ApiOperation("分页查询")
    @PreAuthorize("@el.check('sysdict:list')")
    @GetMapping
    public R listForPage(@RequestParam Map<String,Object> params) {
        List<SysDictEntity> pageInfo = sysDictService.listForPage(params);
        return R.success(pageInfo);
    }

    /*
     **
     * @Description: 查询
     * @Param: [pageQuery]
     * @return: me.jiahao.exception.R
     * @Author: jiahao.pan
     * @Date: 2021-01-27 07:38:46
     */
    @ApiOperation("查询")
    @PreAuthorize("@el.check('sysdict:list')")
    @GetMapping(value = "find")
    public R findAll(@RequestParam Map<String,Object> params) {
        List<SysDictEntity> entityList = sysDictService.findAll(params);
        return R.success(entityList);
    }

    /*
     **
     * @Description: 查询字典明细
     * @Param: [pageQuery]
     * @return: me.jiahao.exception.R
     * @Author: jiahao.pan
     * @Date: 2021-01-27 07:38:46
     */
    @ApiOperation("查询字典明细")
    @PreAuthorize("@el.check('sysdict:list')")
    @GetMapping(value = "getDictDetail")
    public R getDictDetail(@RequestParam Map<String,Object> params) {
        if (params != null && params.containsKey("code")) {
            List<SysDictEntity> entityList = sysDictService.getDictDetail(params.get("code").toString());
            return R.success(entityList);
        }
        return R.error("字典异常!");
    }

    /*
     **
     * @Description: 保存
     * @Param: [sysDictEntity]
     * @return: me.jiahao.exception.R
     * @Author: jiahao.pan
     * @Date: 2021-01-27 07:38:46
     */
    @SysOperaLog(descrption = "保存")
    @ApiOperation("保存")
    @PreAuthorize("@el.check('sysdict:list')")
    @PostMapping
    public R save(SysDictEntity sysDictEntity) {
        return sysDictService.save(sysDictEntity);
    }

    /*
     **
     * @Description: 更新
     * @Param: [sysDictEntity]
     * @return: me.jiahao.exception.R
     * @Author: jiahao.pan
     * @Date: 2021-01-27 07:38:46
     */
    @SysOperaLog(descrption = "更新")
    @ApiOperation("更新")
    @PreAuthorize("@el.check('sysdict:list')")
    @PutMapping
    public R update(SysDictEntity sysDictEntity) {
        return sysDictService.update(sysDictEntity);
    }

    /*
     **
     * @Description: 删除
     * @Param: [id]
     * @return: me.jiahao.exception.R
     * @Author: jiahao.pan
     * @Date: 2021-01-27 07:38:46
     */
    @SysOperaLog(descrption = "删除")
    @ApiOperation("删除")
    @PreAuthorize("@el.check('sysdict:list')")
    @DeleteMapping
    public R remove(@RequestBody Long[] ids) {
        return sysDictService.remove(ids);
    }

    /*
     **
     * @Description: 导出
     * @Param: []
     * @return: me.jiahao.exception.R
     * @Author: panjiahao
     * @Date: 2020/12/7
     */
    @SysOperaLog(descrption = "导出")
    @ApiOperation("导出")
    @PreAuthorize("@el.check('sysdict:list')")
    @PostMapping(value = "export")
    public void exportExcel(@RequestBody Map<String,Object> params,HttpServletResponse response) throws IOException{
        List<SysDictBO> list = sysDictService.findSysDict(params);
        // 导出
        EasyExcelUtil.exportExcel(response,SysDictBO.class,list);
    }

}
