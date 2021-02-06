package me.jiahao.modules.security.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.jiahao.exception.R;

import me.jiahao.logging.annotation.SysOperaLog;
import me.jiahao.modules.security.entity.SysOnlineEntity;
import me.jiahao.modules.security.entity.bo.SysOnlineBO;
import me.jiahao.modules.security.service.SysOnlineService;
import me.jiahao.utils.EasyExcelUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;



/**
 * 在线用户表
 *
 * @author jiahao.pan
 * @email 1342939721@qq.com
 * @date 2020-12-18 15:51:00
 */
@Api(tags = "在线用户表")
@RestController
@RequestMapping("api/sysonline")
@RequiredArgsConstructor
public class SysOnlineController {

    private final SysOnlineService sysOnlineService;

    /*
     **
     * @Description: 分页查询
     * @Param: [pageQuery]
     * @return: me.jiahao.exception.R
     * @Author: jiahao.pan
     * @Date: 2020-12-18 15:51:00
     */
    @ApiOperation("分页查询")
    @PreAuthorize("@el.check('sysonline:list')")
    @GetMapping
    public R listForPage(@RequestParam Map<String,Object> params) {
        PageInfo<SysOnlineEntity> pageInfo = sysOnlineService.listForPage(params);
        return R.success(pageInfo);
    }

    /*
     **
     * @Description: 保存
     * @Param: [sysOnlineEntity]
     * @return: me.jiahao.exception.R
     * @Author: jiahao.pan
     * @Date: 2020-12-18 15:51:00
     */
    @SysOperaLog(descrption = "保存")
    @ApiOperation("保存")
    @PreAuthorize("@el.check('sysonline:list')")
    @PostMapping
    public R save(SysOnlineEntity sysOnlineEntity) {
        return sysOnlineService.save(sysOnlineEntity);
    }

    /*
     **
     * @Description: 更新
     * @Param: [sysOnlineEntity]
     * @return: me.jiahao.exception.R
     * @Author: jiahao.pan
     * @Date: 2020-12-18 15:51:00
     */
    @SysOperaLog(descrption = "更新")
    @ApiOperation("更新")
    @PreAuthorize("@el.check('sysonline:list')")
    @PutMapping
    public R update(SysOnlineEntity sysOnlineEntity) {
        return sysOnlineService.update(sysOnlineEntity);
    }

    /*
     **
     * @Description: 删除
     * @Param: [id]
     * @return: me.jiahao.exception.R
     * @Author: jiahao.pan
     * @Date: 2020-12-18 15:51:00
     */
    @SysOperaLog(descrption = "删除")
    @ApiOperation("删除")
    @PreAuthorize("@el.check('sysonline:list')")
    @DeleteMapping
    public R remove(@RequestBody Long[] ids) {
        return sysOnlineService.remove(ids);
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
    @PreAuthorize("@el.check('sysonline:list')")
    @PostMapping(value = "export")
    public void exportExcel(@RequestBody Map<String,Object> params,HttpServletResponse response) throws IOException{
        List<SysOnlineBO> list = sysOnlineService.findSysOnline(params);
        // 导出
        EasyExcelUtil.exportExcel(response,SysOnlineBO.class,list);
    }

}
