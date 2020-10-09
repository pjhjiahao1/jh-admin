package me.jiahao.modules.security.security;

import java.time.LocalDateTime;

/**
 * @author : panjiahao
 * @date : 13:38 2020/9/16
 */
public class CaptchaImageVO {
    private String code;
    private LocalDateTime localDateTime;
    public CaptchaImageVO(String code,int longTime) {
        this.code = code;
        this.localDateTime = LocalDateTime.now().plusSeconds(longTime);
    }
    public boolean isExpired(){
        return  LocalDateTime.now().isAfter(localDateTime);
    }
    public String getCode() {
        return code;
    }
}
