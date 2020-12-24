package me.jiahao.logging.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jiahao.logging.entity.SysLogEntity;
import me.jiahao.logging.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author : panjiahao
 * @date : 15:55 2020/12/23
 * 注解形式的监听 异步监听日志事件
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SysLogListener {
    private final SysLogService sysLogService;

    @Async
    @Order
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event) {
        SysLogEntity sysLog = (SysLogEntity) event.getSource();
        // 保存日志
        sysLogService.save(sysLog);
    }
}
