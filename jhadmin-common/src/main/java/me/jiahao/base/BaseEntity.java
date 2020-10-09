package me.jiahao.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import me.jiahao.utils.SecurityUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author : panjiahao
 * @date : 15:55 2020/9/22
 */
@Getter
@Setter
public class BaseEntity implements Serializable {

    private String createBy;

    private String updateBy;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp createTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp updateTime;

    // 自动设置 创建人 创建时间 更新人 更新时间 使代码更简洁
//    public BaseEntity() {
//        String username = SecurityUtils.getCurrentUser().getUsername();
//        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
//        this.createBy = username;
//        this.createTime = currentDate;
//        this.updatedBy = username;
//        this.updateTime = currentDate;
//    }

}
