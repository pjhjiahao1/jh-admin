package me.jiahao.handler;

import me.jiahao.utils.SecurityUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;

/**
 * @author : panjiahao
 * @date : 11:03 2020/10/3
 * sql拦截，拦截插入和更新方法 将创建时间创建人更新时间更新人赋值
 */
@Component
@Intercepts(@Signature(type = Executor.class, method = "update", args = {MappedStatement.class,
        Object.class}))
public class MybatisIntercepter implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement)invocation.getArgs()[0];
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        Object parameter = invocation.getArgs()[1];
        Class<?> clazz = parameter.getClass();
        String clazzName = clazz.getName();
//        if (clazzName.substring(clazzName.length() - 2,clazzName.length()).equals("Vo")){
        if (!clazz.getSuperclass().isInstance(Object.class)){
            Class<?> superclass = clazz.getSuperclass();
            updateFeild(superclass.getDeclaredFields(),parameter,sqlCommandType);
        }else {
            updateFeild(parameter.getClass().getDeclaredFields(),parameter,sqlCommandType);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    private void updateFeild(Field[] declaredFields, Object parameter, SqlCommandType sqlCommandType) throws IllegalAccessException {
        for (Field field: declaredFields){
            if (SqlCommandType.INSERT.equals(sqlCommandType)){
                if (field.getName().equals("createTime")){
                    field.setAccessible(true);
                    field.set(parameter,new Timestamp(System.currentTimeMillis()));
                }
                if (field.getName().equals("createBy")){
                    field.setAccessible(true);
                    field.set(parameter, SecurityUtils.getCurrentUser().getUsername());
                }
            }else if (SqlCommandType.UPDATE.equals(sqlCommandType)){
                if (field.getName().equals("updateTime")){
                    field.setAccessible(true);
                    field.set(parameter,new Timestamp(System.currentTimeMillis()));
                }
                if (field.getName().equals("updateBy")){
                    field.setAccessible(true);
                    field.set(parameter, SecurityUtils.getCurrentUser().getUsername());
                }
            }
        }
    }
}
