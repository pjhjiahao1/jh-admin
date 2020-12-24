package me.jiahao.logging.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.jiahao.exception.R;
import me.jiahao.logging.entity.SysLogEntity;
import me.jiahao.logging.service.SysLogService;
import me.jiahao.logging.entity.bo.SysLogBO;

import me.jiahao.utils.EasyExcelUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;



/**
 * 操作日志表
 *
 * @author jiahao.pan
 * @email 1342939721@qq.com
 * @date 2020-12-23 16:29:28
 */
@Api(tags = "操作日志表")
@RestController
@RequestMapping("api/syslog")
@RequiredArgsConstructor
public class SysLogController {

    private final SysLogService sysLogService;

    /*
     **
     * @Description: 分页查询
     * @Param: [pageQuery]
     * @return: me.jiahao.exception.R
     * @Author: jiahao.pan
     * @Date: 2020-12-23 16:29:28
     */
    @ApiOperation("分页查询")
    @PreAuthorize("@el.check('syslog:list')")
    @GetMapping
    public R listForPage(@RequestParam Map<String,Object> params) {
        PageInfo<SysLogEntity> pageInfo = sysLogService.listForPage(params);
        return R.success(pageInfo);
    }

    /*
     **
     * @Description: 保存
     * @Param: [sysLogEntity]
     * @return: me.jiahao.exception.R
     * @Author: jiahao.pan
     * @Date: 2020-12-23 16:29:28
     */
    @ApiOperation("保存")
    @PreAuthorize("@el.check('syslog:list')")
    @PostMapping
    public R save(SysLogEntity sysLogEntity) {
        return sysLogService.save(sysLogEntity);
    }

    /*
     **
     * @Description: 更新
     * @Param: [sysLogEntity]
     * @return: me.jiahao.exception.R
     * @Author: jiahao.pan
     * @Date: 2020-12-23 16:29:28
     */
    @ApiOperation("更新")
    @PreAuthorize("@el.check('syslog:list')")
    @PutMapping
    public R update(SysLogEntity sysLogEntity) {
        return sysLogService.update(sysLogEntity);
    }

    /*
     **
     * @Description: 删除
     * @Param: [id]
     * @return: me.jiahao.exception.R
     * @Author: jiahao.pan
     * @Date: 2020-12-23 16:29:28
     */
    @ApiOperation("删除")
    @PreAuthorize("@el.check('syslog:list')")
    @DeleteMapping
    public R remove(@RequestBody Long[] ids) {
        return sysLogService.remove(ids);
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
    @PreAuthorize("@el.check('syslog:list')")
    @PostMapping(value = "export")
    public void exportExcel(@RequestBody Map<String,Object> params,HttpServletResponse response) throws IOException{
        List<SysLogBO> list = sysLogService.findSysLog(params);
        // 导出
        EasyExcelUtil.exportExcel(response,SysLogBO.class,list);
    }

}
