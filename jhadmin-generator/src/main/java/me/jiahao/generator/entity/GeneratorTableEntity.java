package me.jiahao.generator.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import me.jiahao.base.BaseEntity;

import java.io.Serializable;

/**
 * @author : panjiahao
 * @date : 13:01 2020/11/23
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY) // json数据中为空不返回
public class GeneratorTableEntity extends BaseEntity implements Serializable {
    private String tableName; // 表名
    private String engine; // 数据库引擎
    private String tableCollation; // 字符编码集
    private String tableComment; // 备注
}
