package me.jiahao.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : panjiahao
 * @date : 16:23 2020/12/9
 * 类型转换
 */
@Component
public class Conversion<K,T> {

    /*
     **
     * @Description:
     * @Param:
     * @return:
     * @Author: panjiahao
     * @Date: 2020/12/9
     */
    public List<K> typeConversion(K k, List<T> list) {
        List<K> userCheckDataList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(list)){
            userCheckDataList = list.stream().map(userCheckPO -> {
                BeanUtils.copyProperties(userCheckPO, k);
                return k;
            }).collect(Collectors.toList());
        }
        return userCheckDataList;
    }
}
