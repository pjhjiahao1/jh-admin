package me.jiahao.modules.quartz.utils;

import lombok.extern.slf4j.Slf4j;
import me.jiahao.modules.quartz.entity.SysQuartzJobEntity;
import me.jiahao.modules.quartz.entity.SysQuartzLogEntity;
import me.jiahao.modules.quartz.service.SysQuartzJobService;
import me.jiahao.modules.quartz.service.SysQuartzLogService;
import me.jiahao.tools.service.SendMailService;
import me.jiahao.utils.SpringContextHolder;
import me.jiahao.utils.ThrowableUtil;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.lang.reflect.Method;

/**参考人人开源，https://gitee.com/renrenio/renren-security
 * @author : panjiahao
 * @date : 13:33 2020/12/25
 */
@Slf4j
@Async
public class ExecutionJob extends QuartzJobBean {


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        SysQuartzJobEntity quartzJob = (SysQuartzJobEntity) context.getMergedJobDataMap().get(SysQuartzJobEntity.JOB_KEY);
        SysQuartzLogService sysQuartzLogService = SpringContextHolder.getBean(SysQuartzLogService.class);
        SysQuartzJobService sysQuartzJobService = SpringContextHolder.getBean(SysQuartzJobService.class);
        SysQuartzLogEntity syslog = new SysQuartzLogEntity();
        syslog.setJobName(quartzJob.getJobName());
        syslog.setBeanName(quartzJob.getBeanName());
        syslog.setMethodName(quartzJob.getMethodName());
        syslog.setParams(quartzJob.getParams());
        syslog.setPid(quartzJob.getUid());
        long startTime = System.currentTimeMillis();
        syslog.setCronExpression(quartzJob.getCronExpression());
        try {
            // 执行任务
            log.info("--------------------------------------------------------------");
            log.info("任务开始执行，任务名称：" + quartzJob.getJobName());
            Object target = SpringContextHolder.getBean(quartzJob.getBeanName());
            String params = quartzJob.getParams();
            if (StringUtils.isBlank(params)) {
                Method method = target.getClass().getDeclaredMethod(quartzJob.getMethodName());
                method.invoke(target);
            } else {
                Method method = target.getClass().getDeclaredMethod(quartzJob.getMethodName(),String.class);
                method.invoke(target, params);
            }
            long times = System.currentTimeMillis() - startTime;
            syslog.setTime(times);
            // 任务状态
            syslog.setIsSuccess(0);
            log.info("任务执行完毕，任务名称：" + quartzJob.getJobName() + ", 执行时间：" + times + "毫秒");
            log.info("--------------------------------------------------------------");


        } catch (Exception e) {
            log.info("任务执行失败，任务名称：" + quartzJob.getJobName());
            log.info("--------------------------------------------------------------");
            long times = System.currentTimeMillis() - startTime;
            syslog.setTime(times);
            // 任务状态 0：成功 1：失败
            syslog.setIsSuccess(1);
            syslog.setExceptionDetail(ThrowableUtil.getStackTrace(e));
            // 任务如果失败了则暂停
            if(quartzJob.getPauseAfterFailure() == 1){
                quartzJob.setIsPause(1);
                //更新状态
                sysQuartzJobService.update(quartzJob);
            }
            if(quartzJob.getEmail() != null){
                SendMailService sendMailService = SpringContextHolder.getBean(SendMailService.class);
                String subject = "定时任务【"+ quartzJob.getJobName() +"】执行失败，请尽快处理！";
                sendMailService.sendSimpleMail(quartzJob.getEmail(),subject,ThrowableUtil.getStackTrace(e));
            }
        } finally {
            sysQuartzLogService.save(syslog);
        }
    }
}
