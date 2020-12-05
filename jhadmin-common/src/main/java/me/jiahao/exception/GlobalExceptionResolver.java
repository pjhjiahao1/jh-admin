package me.jiahao.exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import javax.security.auth.login.AccountExpiredException;
import javax.security.auth.login.CredentialExpiredException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : panjiahao
 * @date : 14:16 2020/11/27
 */
@ControllerAdvice
public class GlobalExceptionResolver {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionResolver.class);

//    @ExceptionHandler(HttpClientErrorException.class)
//    public void httpClientErrorException(HttpServletResponse response, HttpClientErrorException e) {
//        R result = new R();
//        result.setCode(CustomExceptionType.UNAUTHORIZED).setMsg("登录过期").setData(null);
//        responseResult(response, result);
//    }
//
//    @ExceptionHandler(UsernameNotFoundException.class)
//    public void usernameNotFoundException(HttpServletResponse response, UsernameNotFoundException e) {
//        R result = new R();
//        result.setCode(CustomExceptionType.UNAUTHORIZED).setMsg("用户名不存在").setData(null);
//        responseResult(response, result);
//    }
//    @ExceptionHandler(LockedException.class)
//    public void lockedException(HttpServletResponse response, LockedException e) {
//        R result = new R();
//        result.setCode(CustomExceptionType.UNAUTHORIZED).setMsg("帐户被锁").setData(null);
//        responseResult(response, result);
//    }
//
//    @ExceptionHandler(DisabledException.class)
//    public void disabledException(HttpServletResponse response, DisabledException e) {
//        R result = new R();
//        result.setCode(CustomExceptionType.UNAUTHORIZED).setMsg("帐户未启动").setData(null);
//        responseResult(response, result);
//    }
//    @ExceptionHandler(CredentialExpiredException.class)
//    public void credentialExpiredException(HttpServletResponse response, CredentialExpiredException e) {
//        R result = new R();
//        result.setCode(CustomExceptionType.UNAUTHORIZED).setMsg("密码过期").setData(null);
//        responseResult(response, result);
//    }
//    @ExceptionHandler(AccountExpiredException.class)
//    public void accountExpiredException(HttpServletResponse response, AccountExpiredException e) {
//        R result = new R();
//        result.setCode(CustomExceptionType.UNAUTHORIZED).setMsg("账户过期").setData(null);
//        responseResult(response, result);
//    }
    @ExceptionHandler(AccessDeniedException.class)
    public void accessDeniedException(HttpServletResponse response, AccessDeniedException e) {
        R result = new R();
        result.setCode(CustomExceptionType.UNAUTHORIZED).setMsg("接口不允许访问").setData(null);
        responseResult(response, result);
    }
//
//    @ExceptionHandler(UnauthorizedException.class)
//    public void page403(HttpServletResponse response) {
//        RetResult<Object> result = new RetResult<>();
//        result.setCode(RetCode.UNAUTHZ).setMsg("用户没有访问权限").setData(null);
//        responseResult(response, result);
//    }
    /**
     * 业务异常的处理
     */
    @ExceptionHandler(value = CustomException.class)
    public void customExceptionHandler(HttpServletResponse response, CustomException e) {
        R result = new R();
        result.setCode(CustomExceptionType.FAIL).setMsg(e.getMessage()).setData(null);
        responseResult(response, result);
    }
    /**
     * 其他异常统一处理
     */
    @ExceptionHandler(value = Exception.class)
    public void exceptionHandler(HttpServletResponse response, Exception e) {
        R result = new R();
        result.setCode(CustomExceptionType.INTERNAL_SERVER_ERROR).setMsg("服务器打酱油了，请稍后再试~");
        logger.error(e.getMessage(), e);
        responseResult(response, result);
    }
    /**
     * @param response
     * @param result
     * @Title: responseResult
     * @Description: 响应结果
     * @Reutrn void
     */
    private void responseResult(HttpServletResponse response, R result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(result.getCode());
        try {
            response.getWriter().write(JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }


}
