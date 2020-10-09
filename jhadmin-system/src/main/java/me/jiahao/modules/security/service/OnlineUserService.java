package me.jiahao.modules.security.service;

import lombok.RequiredArgsConstructor;
import me.jiahao.modules.security.entity.OnlineUserEntity;
import me.jiahao.modules.security.entity.UserDetailsEntity;
import me.jiahao.modules.security.entity.UserInfoEntity;
import me.jiahao.modules.security.security.JwtTokenUtil;
import me.jiahao.utils.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author : panjiahao
 * @date : 16:50 2020/10/7
 */
@Service
@RequiredArgsConstructor
public class OnlineUserService {

    private final RedisTemplate redisTemplate;
    private final JwtTokenUtil jwtTokenUtil;
    /*
    **
    * @Description: 保存在线信息 存入redis 有效期为3天
    * @Param: [jwtUserDto, token, request]
    * @return: void
    * @Author: panjiahao
    * @Date: 2020/10/7
    */
    public void save(UserDetailsEntity userDetailsEntity, String token, HttpServletRequest request){
        String ip = StringUtils.getIp(request);
        String browser = StringUtils.getBrowser(request);
        String address = StringUtils.getCityInfo(ip);
        OnlineUserEntity onlineUserEntity = null;
        try {
            onlineUserEntity = new OnlineUserEntity();
            onlineUserEntity.setUserName(userDetailsEntity.getUsername());
            onlineUserEntity.setNickName(userDetailsEntity.getNickName());
            onlineUserEntity.setAddress(address);
            onlineUserEntity.setIp(ip);
            onlineUserEntity.setLoginTime(new Date());
            onlineUserEntity.setBrowser(browser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 保存在线用户信息3天
        redisTemplate.opsForValue().set(jwtTokenUtil.getLongTimeToken() + token,onlineUserEntity,(jwtTokenUtil.getExpiration()/1000) * 18);
    }
}
