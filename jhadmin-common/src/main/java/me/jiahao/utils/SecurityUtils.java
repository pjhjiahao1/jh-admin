package me.jiahao.utils;

import cn.hutool.json.JSONObject;
import me.jiahao.exception.CustomException;
import me.jiahao.exception.CustomExceptionType;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author : panjiahao
 * @date : 16:54 2020/9/19
 */
public class SecurityUtils {

    /**
     * 获取当前登录的用户
     * @return UserDetails
     */
    public static UserDetails getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new CustomException(CustomExceptionType.INTERNAL_SERVER_ERROR, "当前登录状态过期");
        }
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            UserDetailsService userDetailsService = null;
            try {
                userDetailsService = SpringContextHolder.getBean(UserDetailsService.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return userDetailsService.loadUserByUsername(userDetails.getUsername());
        }
        throw new CustomException(CustomExceptionType.INTERNAL_SERVER_ERROR, "找不到当前登录的信息");

    }

    /**
     * 获取当前登录的用户id
     * @return UserDetails
     */
    public static String getCurrentUserName() {
        UserDetails currentUser = getCurrentUser();
        return currentUser.getUsername();
    }
}
