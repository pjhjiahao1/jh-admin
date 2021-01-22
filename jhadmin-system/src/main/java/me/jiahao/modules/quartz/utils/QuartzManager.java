package me.jiahao.modules.quartz.utils;

import lombok.extern.slf4j.Slf4j;
import me.jiahao.exception.CustomException;
import me.jiahao.exception.CustomExceptionType;
import me.jiahao.modules.quartz.entity.SysQuartzJobEntity;
import me.jiahao.utils.StringUtils;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author : panjiahao
 * @date : 11:49 2020/12/25
 */
@Slf4j
@Component
public class QuartzManager {

    private static final String JOB_NAME = "TASK_";

    @Autowired
    private Scheduler scheduler;

    public void addJob(SysQuartzJobEntity quartzJob) {
        try {
            quartzJob.setUid(StringUtils.createUUID());
            // 构建job信息
            String job = JOB_NAME + quartzJob.getUid();
            String id = quartzJob.getUid();
            JobDetail jobDetail = JobBuilder.newJob(ExecutionJob.class).
                    withIdentity(job).build();
            //通过触发器名和cron 表达式创建 Trigger
            Trigger cronTrigger = newTrigger()
                    .withIdentity(job)
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule(quartzJob.getCronExpression()))
                    .build();
            cronTrigger.getJobDataMap().put(SysQuartzJobEntity.JOB_KEY, quartzJob);
            //重置启动时间
            ((CronTriggerImpl) cronTrigger).setStartTime(new Date());
            //执行定时任务
            scheduler.scheduleJob(jobDetail, cronTrigger);
            // 暂停任务 1 暂停 0 启用
            if (quartzJob.getIsPause() == 1) {
                pauseJob(id);
            }
        } catch (Exception e) {
            log.error("创建定时任务失败", e);
            throw new CustomException(CustomExceptionType.FAIL, "创建定时任务失败");
        }
    }

    /**
     * 暂停job
     *
     * @param job 任务id
     * @throws SchedulerException
     * @author xpf
     * @date 2018/5/30.
     */
    public void pauseJob(String job) {
        try {
            String jobid = JOB_NAME + job;
            scheduler.pauseJob(JobKey.jobKey(jobid));
        } catch (Exception e) {
            log.error("暂停定时任务失败", e);
            throw new CustomException(CustomExceptionType.FAIL, "暂停定时任务失败");
        }

    }

    /**
     * 恢复job
     *
     * @param job 任务id
     * @throws SchedulerException
     * @author xpf
     * @date 2018/5/30.
     */
    public void resumeJob(String job) {
        try {
            String jobid = JOB_NAME + job;
            scheduler.resumeJob(JobKey.jobKey(jobid));
        } catch (Exception e) {
            log.error("恢复定时任务失败", e);
            throw new CustomException(CustomExceptionType.FAIL, "恢复定时任务失败");
        }

    }

    /**
     * job 删除
     *
     * @param job 任务id
     * @throws Exception
     * @author xpf
     * @date 2018/5/30.
     */
    public void deleteJob(String job) {
        try {
            String jobid = JOB_NAME + job;
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobid));
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobid));
            scheduler.deleteJob(JobKey.jobKey(jobid));
        } catch (Exception e) {
            log.error("删除定时任务失败", e);
            throw new CustomException(CustomExceptionType.FAIL, "删除定时任务失败");
        }
    }

    /**
     * 立即执行job
     *
     * @param job 任务id
     * @throws SchedulerException
     */
    public void runAJobNow(SysQuartzJobEntity job){
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(JOB_NAME + job.getUid());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
//            // 如果不存在则创建一个定时任务
            if(trigger == null) {
                addJob(job);
            }
            JobDataMap dataMap = new JobDataMap();
            dataMap.put(SysQuartzJobEntity.JOB_KEY, job);
            JobKey jobKey = JobKey.jobKey(JOB_NAME + job.getUid());
            scheduler.triggerJob(jobKey,dataMap);
        } catch (Exception e){
            log.error("定时任务执行失败", e);
            throw new CustomException(CustomExceptionType.FAIL, "执行定时任务失败");
        }
    }
}
