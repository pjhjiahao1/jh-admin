package me.jiahao.modules.quartz.entity;


import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import me.jiahao.base.BaseEntity;

/**
 * 定时任务日志
 *
 * @author jiahao.pan
 * @email 1342939721@qq.com
 * @date 2020-12-25 16:44:42
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY) // json数据中为空不返回
public class SysQuartzLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * IDid
     */
    private Long id;


    /**
     *
     */
    private String beanName;


    /**
     *
     */
    private String cronExpression;


    /**
     *
     */
    private String exceptionDetail;


    /**
     *
     */
    private Integer isSuccess;


    /**
     *
     */
    private String jobName;


    /**
     *
     */
    private String methodName;


    /**
     *
     */
    private String params;


    /**
     *
     */
    private Long time;


    /**
     * quartz表id
     */
    private String pid;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp createTime;


}
