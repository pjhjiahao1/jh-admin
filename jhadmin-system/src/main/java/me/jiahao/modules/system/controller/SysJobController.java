package me.jiahao.modules.system.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.jiahao.exception.R;
import me.jiahao.logging.annotation.SysOperaLog;
import me.jiahao.modules.system.entity.SysJobEntity;
import me.jiahao.modules.system.service.SysJobService;
import me.jiahao.modules.system.entity.bo.SysJobBO;

import me.jiahao.utils.EasyExcelUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;



/**
 * 岗位表
 *
 * @author jiahao.pan
 * @email 1342939721@qq.com
 * @date 2020-12-16 17:01:04
 */
@Api(tags = "岗位表")
@RestController
@RequestMapping("api/sysjob")
@RequiredArgsConstructor
public class SysJobController {

    private final SysJobService sysJobService;
    @Value("${spring.profiles.active}")
    private String active;


    /*
     **
     * @Description: 分页查询
     * @Param: [pageQuery]
     * @return: me.jiahao.exception.R
     * @Author: jiahao.pan
     * @Date: 2020-12-16 17:01:04
     */
    @ApiOperation("分页查询")
    @PreAuthorize("@el.check('sysjob:list')")
    @GetMapping
    public R listForPage(@RequestParam Map<String,Object> params) {
        PageInfo<SysJobEntity> pageInfo = sysJobService.listForPage(params);
        return R.success(pageInfo);
    }

    /*
     **
     * @Description: 保存
     * @Param: [sysJobEntity]
     * @return: me.jiahao.exception.R
     * @Author: jiahao.pan
     * @Date: 2020-12-16 17:01:04
     */
    @SysOperaLog(descrption = "保存岗位")
    @ApiOperation("保存")
    @PreAuthorize("@el.check('sysjob:list')")
    @PostMapping
    public R save(SysJobEntity sysJobEntity) {
        if (active.equals("prod")) {
            return R.error("演示环境不可操作!");
        }
        return sysJobService.save(sysJobEntity);
    }

    /*
     **
     * @Description: 更新
     * @Param: [sysJobEntity]
     * @return: me.jiahao.exception.R
     * @Author: jiahao.pan
     * @Date: 2020-12-16 17:01:04
     */
    @SysOperaLog(descrption = "更新岗位")
    @ApiOperation("更新")
    @PreAuthorize("@el.check('sysjob:list')")
    @PutMapping
    public R update(SysJobEntity sysJobEntity) {
        if (active.equals("prod")) {
            return R.error("演示环境不可操作!");
        }
        return sysJobService.update(sysJobEntity);
    }

    /*
     **
     * @Description: 删除
     * @Param: [id]
     * @return: me.jiahao.exception.R
     * @Author: jiahao.pan
     * @Date: 2020-12-16 17:01:04
     */
    @SysOperaLog(descrption = "删除岗位")
    @ApiOperation("删除")
    @PreAuthorize("@el.check('sysjob:list')")
    @DeleteMapping
    public R remove(@RequestBody Long[] ids) {
        if (active.equals("prod")) {
            return R.error("演示环境不可操作!");
        }
        return sysJobService.remove(ids);
    }

    /*
     **
     * @Description: 导出
     * @Param: []
     * @return: me.jiahao.exception.R
     * @Author: panjiahao
     * @Date: 2020/12/7
     */
    @SysOperaLog(descrption = "导出岗位")
    @ApiOperation("导出")
    @PreAuthorize("@el.check('sysjob:list')")
    @PostMapping(value = "export")
    public void exportExcel(@RequestBody Map<String,Object> params,HttpServletResponse response) throws IOException{
        List<SysJobBO> list = sysJobService.findSysJob(params);
        // 导出
        EasyExcelUtil.exportExcel(response,SysJobBO.class,list);
    }

    @ApiOperation("查询全部")
    @PreAuthorize("@el.check('sysjob:list')")
    @GetMapping(value = "findAll")
    public R findAll() {
        return R.success(sysJobService.findAll());
    }

}
