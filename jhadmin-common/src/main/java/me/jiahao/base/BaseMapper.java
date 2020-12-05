package me.jiahao.base;

import java.util.List;

/**
 * @author : panjiahao
 * @date : 13:34 2020/11/19
 */
public abstract interface BaseMapper <T>{
    abstract int save(T paramT); // 保存
    abstract int update(T paramT); // 更新
    abstract int remove(Object paramObject); // 删除
    abstract int batchRemove(Object[] paramObjects); // 批量删除
    abstract List<T> listForPage(); // 分页
    abstract List<T> list(); // 返回全部
    abstract List<T> list(Object paramObject); // 根据参数返回数据
    abstract T getObjectById(Object id);
}
