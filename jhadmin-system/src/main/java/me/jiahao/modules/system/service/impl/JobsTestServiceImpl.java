package me.jiahao.modules.system.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.jiahao.modules.system.service.JobsTestService;
import me.jiahao.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author : panjiahao
 * @date : 13:50 2020/12/25
 */
@Service(value = "jobsTestService")
@Slf4j
public class JobsTestServiceImpl implements JobsTestService {

    @Override
    public void testJobs(String s) {
        System.out.println(s);
    }
}
