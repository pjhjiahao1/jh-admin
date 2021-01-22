package me.jiahao.modules.quartz.config;

import lombok.RequiredArgsConstructor;
import me.jiahao.modules.quartz.entity.SysQuartzJobEntity;
import me.jiahao.modules.quartz.service.SysQuartzJobService;
import me.jiahao.modules.quartz.utils.QuartzManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JobRunner implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(JobRunner.class);
    private final SysQuartzJobService sysQuartzJobService;
    private final QuartzManager quartzManager;

    /**
     * 项目启动时重新激活启用的定时任务
     *
     * @param applicationArguments /
     */
    @Override
    public void run(ApplicationArguments applicationArguments) {
        log.info("--------------------开始注入---------------------");
        List<SysQuartzJobEntity> jobEntities = sysQuartzJobService.list(new HashMap<String, Object>() {{
            put("isPause", 0);
        }});
        jobEntities.forEach(quartzManager::addJob);
        log.info("--------------------注入完成---------------------");
    }
}
