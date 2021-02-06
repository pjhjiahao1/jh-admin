package me.jiahao.modules.quartz.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.jiahao.exception.R;
import me.jiahao.logging.annotation.SysOperaLog;
import me.jiahao.modules.quartz.entity.SysQuartzLogEntity;
import me.jiahao.modules.quartz.entity.bo.SysQuartzLogBO;
import me.jiahao.modules.quartz.service.SysQuartzLogService;
import me.jiahao.utils.EasyExcelUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;



/**
 * 定时任务日志
 *
 * @author jiahao.pan
 * @email 1342939721@qq.com
 * @date 2020-12-25 16:44:42
 */
@Api(tags = "定时任务日志")
@RestController
@RequestMapping("api/sysquartzlog")
@RequiredArgsConstructor
public class SysQuartzLogController {

    private final SysQuartzLogService sysQuartzLogService;

    /*
     **
     * @Description: 分页查询
     * @Param: [pageQuery]
     * @return: me.jiahao.exception.R
     * @Author: jiahao.pan
     * @Date: 2020-12-25 16:44:42
     */
    @ApiOperation("分页查询")
    @PreAuthorize("@el.check('sysquartzlog:list')")
    @GetMapping
    public R listForPage(@RequestParam Map<String,Object> params) {
        PageInfo<SysQuartzLogEntity> pageInfo = sysQuartzLogService.listForPage(params);
        return R.success(pageInfo);
    }

    /*
     **
     * @Description: 保存
     * @Param: [sysQuartzLogEntity]
     * @return: me.jiahao.exception.R
     * @Author: jiahao.pan
     * @Date: 2020-12-25 16:44:42
     */
    @SysOperaLog(descrption = "保存")
    @ApiOperation("保存")
    @PreAuthorize("@el.check('sysquartzlog:list')")
    @PostMapping
    public R save(SysQuartzLogEntity sysQuartzLogEntity) {
        return sysQuartzLogService.save(sysQuartzLogEntity);
    }

    /*
     **
     * @Description: 更新
     * @Param: [sysQuartzLogEntity]
     * @return: me.jiahao.exception.R
     * @Author: jiahao.pan
     * @Date: 2020-12-25 16:44:42
     */
    @SysOperaLog(descrption = "更新")
    @ApiOperation("更新")
    @PreAuthorize("@el.check('sysquartzlog:list')")
    @PutMapping
    public R update(SysQuartzLogEntity sysQuartzLogEntity) {
        return sysQuartzLogService.update(sysQuartzLogEntity);
    }

    /*
     **
     * @Description: 删除
     * @Param: [id]
     * @return: me.jiahao.exception.R
     * @Author: jiahao.pan
     * @Date: 2020-12-25 16:44:42
     */
    @SysOperaLog(descrption = "删除")
    @ApiOperation("删除")
    @PreAuthorize("@el.check('sysquartzlog:list')")
    @DeleteMapping
    public R remove(@RequestBody Long[] ids) {
        return sysQuartzLogService.remove(ids);
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
    @PreAuthorize("@el.check('sysquartzlog:list')")
    @PostMapping(value = "export")
    public void exportExcel(@RequestBody Map<String,Object> params,HttpServletResponse response) throws IOException{
        List<SysQuartzLogBO> list = sysQuartzLogService.findSysQuartzLog(params);
        // 导出
        EasyExcelUtil.exportExcel(response,SysQuartzLogBO.class,list);
    }

}
