package me.jiahao.modules.quartz.utils;

import lombok.RequiredArgsConstructor;
import me.jiahao.modules.quartz.entity.SysQuartzJobEntity;
import me.jiahao.modules.quartz.entity.SysQuartzLogEntity;
import me.jiahao.modules.quartz.service.SysQuartzLogService;
import me.jiahao.utils.SpringContextHolder;
import me.jiahao.utils.ThrowableUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**参考人人开源，https://gitee.com/renrenio/renren-security
 * @author : panjiahao
 * @date : 13:33 2020/12/25
 */
@Async
public class ExecutionJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        SysQuartzJobEntity quartzJob = (SysQuartzJobEntity) context.getMergedJobDataMap().get(SysQuartzJobEntity.JOB_KEY);
        // 获取spring bean
//        QuartzLogRepository quartzLogRepository = SpringContextHolder.getBean(QuartzLogRepository.class);
        SysQuartzLogService sysQuartzLogService = SpringContextHolder.getBean(SysQuartzLogService.class);
//        RedisTemplate redisUtils = SpringContextHolder.getBean(RedisTemplate.class);

//        String uuid = quartzJob.getUuid();

        SysQuartzLogEntity log = new SysQuartzLogEntity();
        log.setJobName(quartzJob.getJobName());
        log.setBeanName(quartzJob.getBeanName());
        log.setMethodName(quartzJob.getMethodName());
        log.setParams(quartzJob.getParams());
        log.setPid(quartzJob.getUid());
        long startTime = System.currentTimeMillis();
        log.setCronExpression(quartzJob.getCronExpression());
        try {
            // 执行任务
            System.out.println("--------------------------------------------------------------");
            System.out.println("任务开始执行，任务名称：" + quartzJob.getJobName());
            QuartzRunnable task = new QuartzRunnable(quartzJob.getBeanName(), quartzJob.getMethodName(),
                    quartzJob.getParams());
//            Future<?> future = EXECUTOR.submit(task);
//            future.get();
            long times = System.currentTimeMillis() - startTime;
            log.setTime(times);
//            if(StringUtils.isNotBlank(uuid)) {
////                redisUtils.set(uuid, true);
//                redisTemplate.opsForValue().set(uuid,true);
//            }
            // 任务状态
            log.setIsSuccess(1);
            System.out.println("任务执行完毕，任务名称：" + quartzJob.getJobName() + ", 执行时间：" + times + "毫秒");
            System.out.println("--------------------------------------------------------------");

        } catch (Exception e) {
            System.out.println("任务执行失败，任务名称：" + quartzJob.getJobName());
            System.out.println("--------------------------------------------------------------");
            long times = System.currentTimeMillis() - startTime;
            log.setTime(times);
            // 任务状态 0：成功 1：失败
            log.setIsSuccess(0);
            log.setExceptionDetail(ThrowableUtil.getStackTrace(e));
            // 任务如果失败了则暂停
//            if(quartzJob.getPauseAfterFailure() != null && quartzJob.getPauseAfterFailure()){
//                quartzJob.setIsPause(false);
//                //更新状态
////                quartzJobService.updateIsPause(quartzJob);
//            }
//            if(quartzJob.getEmail() != null){
//                EmailService emailService = SpringContextHolder.getBean(EmailService.class);
//                // 邮箱报警
//                EmailVo emailVo = taskAlarm(quartzJob, ThrowableUtil.getStackTrace(e));
//                emailService.send(emailVo, emailService.find());
//            }
        } finally {
            sysQuartzLogService.save(log);
        }
    }
}
