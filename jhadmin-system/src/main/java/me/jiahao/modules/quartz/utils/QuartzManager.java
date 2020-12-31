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
            // 暂停任务 1 启动 0 暂停
            if (quartzJob.getIsPause() == 0) {
                pauseJob(job);
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
            scheduler.pauseJob(JobKey.jobKey(job));
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
            scheduler.resumeJob(JobKey.jobKey(job));
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
            scheduler.pauseTrigger(TriggerKey.triggerKey(job));
            scheduler.unscheduleJob(TriggerKey.triggerKey(job));
            scheduler.deleteJob(JobKey.jobKey(job));
        } catch (Exception e) {
            log.error("删除定时任务失败", e);
            throw new CustomException(CustomExceptionType.FAIL, "删除定时任务失败");
        }
    }
}
