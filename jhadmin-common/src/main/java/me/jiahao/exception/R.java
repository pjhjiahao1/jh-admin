package me.jiahao.exception;

import io.swagger.models.auth.In;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

@Data
public class R {
    private int code;
    private String msg;
    private Object data;

    public R setCode(CustomExceptionType retCode) {
        this.code = retCode.code;
        return this;
    }

    public int getCode() {
        return code;
    }

    public R setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public R setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public R setData(Object data) {
        this.data = data;
        return this;
    }


//
//    //请求出现异常时的响应数据封装
//    public static R error(String msg) {
//        R resultBean = new R();
//        resultBean.setCode(500);
//        if(500 == CustomExceptionType.INTERNAL_SERVER_ERROR){
//            resultBean.setMessage(msg + ",系统异常");
//        }else{
//            resultBean.setMessage("系统出现未知异常!");
//        }
//        return resultBean;
//    }

//    //请求出现异常时的响应数据封装
//    public static R error(Integer code, String msg) {
//        R resultBean = new R();
//        resultBean.setIsok(false);
//        resultBean.setCode(code);
//        if(500 == CustomExceptionType.USER_INPUT_ERROR.getCode()){
//            resultBean.setMessage(msg + ",系统异常");
//        } else if (403 == CustomExceptionType.OTHER_ERROR.getCode()) {
//            resultBean.setMessage(msg);
//        }
//        else{
//            resultBean.setMessage("系统出现未知异常!");
//        }
//        return resultBean;
//    }

    public static R warn(String msg) {

        return new R().setCode(CustomExceptionType.FAIL).setMsg(msg);
    }

    public static R error(String msg) {
        return new R().setCode(CustomExceptionType.INTERNAL_SERVER_ERROR).setMsg(msg);
    }

    public static R common(int count) {
        if (count > 0) {
            return R.success("操作成功!");
        } else {
            return R.warn("操作失败");
        }
    }

    public static R success(String msg) {

        return new R().setCode(CustomExceptionType.SUCCESS).setMsg(msg);
    }

    public static R success(Object data) {

        return new R().setCode(CustomExceptionType.SUCCESS).setData(data);
    }

    public static R success() {
        return new R().setCode(CustomExceptionType.SUCCESS).setMsg("操作成功!");
    }

//    public static R success(Object data,String message) {
//        R resultBean = new R();
//        resultBean.setIsok(true);
//        resultBean.setCode(200);
//        resultBean.setMessage(message);
//        resultBean.setData(data);
//        return resultBean;
//    }

//    public static R success(Object data) {
//        R resultBean = new R();
//        resultBean.setIsok(true);
//        resultBean.setCode(200);
//        resultBean.setMessage("success");
//        resultBean.setData(data);
//        return resultBean;
//    }


}