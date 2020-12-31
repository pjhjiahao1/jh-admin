package me.jiahao.modules.system.service.impl;

import me.jiahao.modules.system.service.JobsTestService;
import me.jiahao.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author : panjiahao
 * @date : 13:50 2020/12/25
 */
@Service(value = "jobsTestService")
public class JobsTestServiceImpl implements JobsTestService {

    @Override
    public void testJobs() {
        System.out.println("test ---->" + DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
    }
}
