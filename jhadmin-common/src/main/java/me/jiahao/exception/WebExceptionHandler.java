package me.jiahao.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : panjiahao
 * @date : 13:38 2020/9/17
 */
@ControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public R customerException(CustomException e) {
        if(e.getCode() == CustomExceptionType.SYSTEM_ERROR.getCode()){
            //400异常不需要持久化，将异常信息以友好的方式告知用户就可以
            //TODO 将500异常信息持久化处理，方便运维人员处理
        }
        return R.warn(e.getMessage());
    }

    @ExceptionHandler(ClassCastException.class)
    @ResponseBody
    public R classCastException(ClassCastException e) {
        //TODO 将异常信息持久化处理，方便运维人员处理
        e.printStackTrace();
        //没有被程序员发现，并转换为CustomException的异常，都是其他异常或者未知异常.
        return R.error(403,"数据转换异常");
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R exception(Exception e) {
        //TODO 将异常信息持久化处理，方便运维人员处理
        e.printStackTrace();
        //没有被程序员发现，并转换为CustomException的异常，都是其他异常或者未知异常.
        return R.error(500,"未知异常");
    }
}
