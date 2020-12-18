package me.jiahao.utils;

import lombok.Data;

/**
 * @author : panjiahao
 * @date : 16:08 2020/12/18
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
