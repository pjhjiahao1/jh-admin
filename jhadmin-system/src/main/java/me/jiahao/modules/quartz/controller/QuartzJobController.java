package me.jiahao.modules.quartz.controller;

/**
 * @author : panjiahao
 * @date : 11:27 2020/12/25
 */

import me.jiahao.exception.R;

/**
 * @author : panjiahao
 * @date : 13:46 2020/9/10
 */
public class QuartzJobController {

//    private final QuartzManager quartzManager;

    public R save () {
//        QuartzJob quartzJob = new QuartzJob();
//        quartzJob.setId(Long.parseLong("111111"));
//        quartzJob.setJobName("测试1");
//        quartzJob.setDescription("ceshi");
//        quartzJob.setBeanName("jobsTestService");
//        quartzJob.setMethodName("testJobs");
//        quartzJob.setCronExpression("*/60 * * * * ?");
//        quartzJob.setPersonInCharge("admin");
//        quartzManager.addJob(quartzJob);
        return R.success();
    }

    public R restore() {
//        quartzManager.resumeJob("TASK_111111");
        return R.success();
    }

    public R pause () {
//        quartzManager.pauseJob("TASK_111111");
        return R.success();
    }

    public R remove () {
//        quartzManager.deleteJob("TASK_111111");
        return R.success();
    }
}
