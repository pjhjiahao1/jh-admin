package me.jiahao.annotation;

import java.lang.annotation.*;

/**
 * @author : panjiahao
 * @date : 9:58 2020/9/22
 */
@Inherited //表示该注解会被子类继承，注意，仅针对类，成员属性、方法并不受此注释的影响
@Documented // Documented注解表明这个注解是由 javadoc记录的，在默认情况下也有类似的记录工具。 如果一个类型声明被注解了文档化，它的注解成为公共API的一部分。
@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AnonymousAccess {
}
