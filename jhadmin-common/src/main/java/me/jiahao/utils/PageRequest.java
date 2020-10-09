package me.jiahao.utils;

import lombok.Data;

/**
 * @author : panjiahao
 * @date : 23:16 2020/10/2
 */
@Data
public class PageRequest {
    /**
     * 当前页码
     */
    private int pageNum;
    /**
     * 每页数量
     */
    private int pageSize;

}
