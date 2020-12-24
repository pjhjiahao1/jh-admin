package me.jiahao.logging.event;

import me.jiahao.logging.entity.SysLogEntity;
import org.springframework.context.ApplicationEvent;

/**
 * @author : panjiahao
 * @date : 15:47 2020/12/23
 *
 */
public class SysLogEvent extends ApplicationEvent {

    public SysLogEvent(SysLogEntity sysLog) {
        super(sysLog);
    }
}
