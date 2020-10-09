package me.jiahao.exception;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : panjiahao
 * @date : 16:39 2020/9/18
 */
@Component
@ControllerAdvice
public class GlobalResponseAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter,
                                  MediaType mediaType, Class aClass,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        if (body == null) {
            return body;
        }
        //匹配R
        if (body instanceof R) {
            response.setStatusCode(
                    HttpStatus.valueOf(
                            ((R) body).getCode()
                    )
            );
            return body;
        }
        try {
            LinkedHashMap<String,Object> map = (LinkedHashMap) body;
            response.setStatusCode(
                    HttpStatus.valueOf(
                            map.get("status").toString()
                    )
            );
        } catch (ClassCastException e) {
//            e.printStackTrace();
        }
        return body;
    }
}
