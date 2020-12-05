package me.jiahao.modules.security.security;

import me.jiahao.modules.security.service.impl.UserDetailServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : panjiahao
 * @date : 15:33 2020/9/17
 */

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    JwtTokenUtil jwtTokenUtil;

    UserDetailServiceImpl userDetailService;

    RedisTemplate redisTemplate;

    public JwtAuthenticationTokenFilter(JwtTokenUtil jwtTokenUtil, UserDetailServiceImpl userDetailService, RedisTemplate redisTemplate) {
        this.userDetailService = userDetailService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.redisTemplate = redisTemplate;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String token = resolveToken(request);
        if (StringUtils.isNotBlank(token)) {
            UserDetails userinfo = (UserDetails) redisTemplate.opsForValue().get(jwtTokenUtil.getOnlineKey() + token);
            // redis中有缓存数据
            if (userinfo == null && StringUtils.isNotBlank(token)) {
                String usernameFromToken = jwtTokenUtil.getUsernameFromToken(token);
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                userinfo = userDetailService.loadUserByUsername(usernameFromToken);
            }
            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(userinfo,null,userinfo.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            jwtTokenUtil.refreshToken(token);
        }
        filterChain.doFilter(request,response);
    }

    /**
     * 初步检测Token
     *
     * @param request /
     * @return /
     */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(jwtTokenUtil.getHeader());
        if (StringUtils.isNotBlank(bearerToken) && bearerToken.startsWith(jwtTokenUtil.getTokenStartWith())) {
            // 去掉令牌前缀
            return bearerToken.replace(jwtTokenUtil.getTokenStartWith(), "");
        } else {
            System.out.println("token为空");
        }
        return null;
    }
}
