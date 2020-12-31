package me.jiahao.modules.quartz.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.jiahao.exception.R;
import me.jiahao.logging.annotation.SysOperaLog;
import me.jiahao.modules.quartz.entity.SysQuartzJobEntity;
import me.jiahao.modules.quartz.service.SysQuartzJobService;
import me.jiahao.modules.quartz.entity.bo.SysQuartzJobBO;

import me.jiahao.utils.EasyExcelUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;



/**
 * 定时任务
 *
 * @author jiahao.pan
 * @email 1342939721@qq.com
 * @date 2020-12-25 15:26:16
 */
@Api(tags = "定时任务")
@RestController
@RequestMapping("api/jobs")
@RequiredArgsConstructor
public class SysQuartzJobController {

    private final SysQuartzJobService sysQuartzJobService;

    /*
     **
     * @Description: 分页查询
     * @Param: [pageQuery]
     * @return: me.jiahao.exception.R
     * @Author: jiahao.pan
     * @Date: 2020-12-25 15:26:16
     */
    @SysOperaLog(descrption = "查询")
    @ApiOperation("分页查询")
    @PreAuthorize("@el.check('sysquartzjob:list')")
    @GetMapping
    public R listForPage(@RequestParam Map<String,Object> params) {
        PageInfo<SysQuartzJobEntity> pageInfo = sysQuartzJobService.listForPage(params);
        return R.success(pageInfo);
    }

    /*
     **
     * @Description: 保存
     * @Param: [sysQuartzJobEntity]
     * @return: me.jiahao.exception.R
     * @Author: jiahao.pan
     * @Date: 2020-12-25 15:26:16
     */
    @SysOperaLog(descrption = "保存")
    @ApiOperation("保存")
    @PreAuthorize("@el.check('sysquartzjob:list')")
    @PostMapping
    public R save(SysQuartzJobEntity sysQuartzJobEntity) {
        return sysQuartzJobService.save(sysQuartzJobEntity);
    }

    /*
     **
     * @Description: 更新
     * @Param: [sysQuartzJobEntity]
     * @return: me.jiahao.exception.R
     * @Author: jiahao.pan
     * @Date: 2020-12-25 15:26:16
     */
    @SysOperaLog(descrption = "更新")
    @ApiOperation("更新")
    @PreAuthorize("@el.check('sysquartzjob:list')")
    @PutMapping
    public R update(SysQuartzJobEntity sysQuartzJobEntity) {
        return sysQuartzJobService.update(sysQuartzJobEntity);
    }

    /*
     **
     * @Description: 删除
     * @Param: [id]
     * @return: me.jiahao.exception.R
     * @Author: jiahao.pan
     * @Date: 2020-12-25 15:26:16
     */
    @SysOperaLog(descrption = "删除")
    @ApiOperation("删除")
    @PreAuthorize("@el.check('sysquartzjob:list')")
    @DeleteMapping
    public R remove(@RequestBody Long[] ids) {
        return sysQuartzJobService.remove(ids);
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
    @PreAuthorize("@el.check('sysquartzjob:list')")
    @PostMapping(value = "export")
    public void exportExcel(@RequestBody Map<String,Object> params,HttpServletResponse response) throws IOException{
        List<SysQuartzJobBO> list = sysQuartzJobService.findSysQuartzJob(params);
        // 导出
        EasyExcelUtil.exportExcel(response,SysQuartzJobBO.class,list);
    }

}
