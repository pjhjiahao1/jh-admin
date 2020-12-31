package me.jiahao.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author : panjiahao
 * @date : 16:51 2020/12/25
 */
public class ThrowableUtil {
    /**
     * 获取堆栈信息
     */
    public static String getStackTrace(Throwable throwable){
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }
}
