package me.jiahao.modules.security.service;

import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import me.jiahao.config.RsaProperties;
import me.jiahao.exception.CustomException;
import me.jiahao.exception.CustomExceptionType;
import me.jiahao.modules.security.entity.UserDetailsEntity;
import me.jiahao.modules.security.entity.UserInfoEntity;
import me.jiahao.modules.security.mapper.UserMenuRoleMeaasgeMapper;
import me.jiahao.modules.security.security.JwtTokenUtil;
import me.jiahao.utils.RsaUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : panjiahao
 * @date : 15:45 2020/9/18
 */
@Service
@RequiredArgsConstructor
public class AuthorizationService {
    private final UserMenuRoleMeaasgeMapper userMenuRoleMeaasgeMapper;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final RedisTemplate redisTemplate;
    private final OnlineUserService onlineUserService;

    /*
     **
     * @Description: 登陆认证换取token
     * @Param: [username, password]
     * @return: java.lang.String
     * @Author: panjiahaoAjaxResponse
        CustomException
        CustomExceptionType
        WebExceptionHandler
     * @Date: 2020/9/17
     */
    public Map<String,Object> login (String username,String password,String descPassword,HttpServletRequest request) throws Exception {
        UserDetails principal;
        try {
            PageHelper.startPage(0, 10);
            UsernamePasswordAuthenticationToken uptoken = new UsernamePasswordAuthenticationToken(username,password);
            Authentication authenticate = authenticationManager.authenticate(uptoken);
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            principal = (UserDetails) authenticate.getPrincipal();
        } catch (AuthenticationException e) {
            e.printStackTrace();
            if (e.getMessage().equals("User is disabled")) {
                throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,"此账号已被禁用!");
            } else {
                throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,"用户名或密码不正确");
            }
        }
        UserDetailsEntity userDetail = userMenuRoleMeaasgeMapper.getUserDetail(username);
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setUsername(userDetail.getUsername());
        userInfoEntity.setPassword(descPassword);
        String token = jwtTokenUtil.generateToken(userDetail);
        Map<String,Object> data = new HashMap<String, Object>(){{
            put("userinfo",userInfoEntity);
            put("token",jwtTokenUtil.getTokenStartWith() + token);
        }};
        // token有效期4小时 所以redis中存储4小时
        redisTemplate.opsForValue().set(jwtTokenUtil.getOnlineKey() + token,principal,jwtTokenUtil.getExpiration()/1000 );
        // 保存在线用户信息到redis
        onlineUserService.save(userDetail,token,request);
        return data;
    }
}
