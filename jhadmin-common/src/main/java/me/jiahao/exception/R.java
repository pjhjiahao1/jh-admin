package me.jiahao.exception;

import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class R {


    private boolean isok;
    private int code;   
    private String message;
    private Object data;

    private R() {

    }

    public static R common(int count) {
        if (count > 0) {
            return R.success("操作成功!");
        } else {
            return R.warn("操作失败");
        }
    }

    //请求出现异常时的响应数据封装
    public static R error(String msg) {
        R resultBean = new R();
        resultBean.setIsok(false);
        resultBean.setCode(500);
        if(500 == CustomExceptionType.USER_INPUT_ERROR.getCode()){
            resultBean.setMessage(msg + ",系统异常");
        }else{
            resultBean.setMessage("系统出现未知异常!");
        }
        return resultBean;
    }

    //请求出现异常时的响应数据封装
    public static R error(Integer code, String msg) {
        R resultBean = new R();
        resultBean.setIsok(false);
        resultBean.setCode(code);
        if(500 == CustomExceptionType.USER_INPUT_ERROR.getCode()){
            resultBean.setMessage(msg + ",系统异常");
        } else if (403 == CustomExceptionType.OTHER_ERROR.getCode()) {
            resultBean.setMessage(msg);
        }
        else{
            resultBean.setMessage("系统出现未知异常!");
        }
        return resultBean;
    }

    public static R warn(String msg) {
        R resultBean = new R();
        resultBean.setIsok(false);
        resultBean.setCode(400);
        if(400 == CustomExceptionType.USER_INPUT_ERROR.getCode()){
            resultBean.setMessage(msg);
        }else{
            resultBean.setMessage("系统出现未知异常!");
        }
        return resultBean;
    }

    public static R success(String message) {
        R resultBean = new R();
        resultBean.setIsok(true);
        resultBean.setCode(200);
        resultBean.setMessage(message);
        return resultBean;
    }

    public static R success() {
        R resultBean = new R();
        resultBean.setIsok(true);
        resultBean.setCode(200);
        resultBean.setMessage("success");
        return resultBean;
    }

    public static R success(Object data,String message) {
        R resultBean = new R();
        resultBean.setIsok(true);
        resultBean.setCode(200);
        resultBean.setMessage(message);
        resultBean.setData(data);
        return resultBean;
    }

    public static R success(Object data) {
        R resultBean = new R();
        resultBean.setIsok(true);
        resultBean.setCode(200);
        resultBean.setMessage("success");
        resultBean.setData(data);
        return resultBean;
    }


}