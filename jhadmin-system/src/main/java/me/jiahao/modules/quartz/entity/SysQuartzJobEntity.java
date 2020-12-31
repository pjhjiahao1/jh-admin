package me.jiahao.modules.quartz.entity;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import me.jiahao.base.BaseEntity;

/**
 * 定时任务
 *
 * @author jiahao.pan
 * @email 1342939721@qq.com
 * @date 2020-12-25 15:26:16
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY) // json数据中为空不返回
public class SysQuartzJobEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String JOB_KEY = "JOB_KEY";

    /**
     * ID
     */
    private Long id;

    /**
     * 随机id
     */
    private String uid;

    /**
     * Spring Bean名称
     */
    private String beanName;


    /**
     * cron 表达式
     */
    private String cronExpression;


    /**
     * 状态：1暂停、0启用
     */
    private int isPause;


    /**
     * 任务名称
     */
    private String jobName;


    /**
     * 方法名称
     */
    private String methodName;


    /**
     * 参数
     */
    private String params;


    /**
     * 备注
     */
    private String description;


    /**
     * 负责人
     */
    private String personInCharge;


    /**
     * 报警邮箱
     */
    private String email;


    /**
     * 子任务ID
     */
    private String subTask;


    /**
     * 任务失败后是否暂停
     */
    private int pauseAfterFailure;


}
