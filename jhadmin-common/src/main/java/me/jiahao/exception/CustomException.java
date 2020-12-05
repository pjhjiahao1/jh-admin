package me.jiahao.exception;

import java.io.Serializable;

public class CustomException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1213855733833039552L;

    //异常错误编码
    private int code ;
    //异常信息
    private String message;

    public CustomException(CustomExceptionType exceptionTypeEnum, String message) {
        this.code = exceptionTypeEnum.code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
