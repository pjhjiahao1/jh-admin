package me.jiahao.modules.security.security;

import com.fasterxml.jackson.core.filter.TokenFilter;
import lombok.RequiredArgsConstructor;
import me.jiahao.modules.security.service.impl.UserDetailServiceImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author : panjiahao
 * @date : 10:24 2020/9/22
 */
@RequiredArgsConstructor //会生成一个包含常量，和标识了NotNull的变量的构造方法。生成的构造方法是private，如何想要对外提供使用可以使用staticName选项生成一个static方法。
public class TokenConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtTokenUtil jwtTokenUtil;

    private final UserDetailServiceImpl userDetailService;

    private final RedisTemplate redisTemplate;

    @Override
    public void configure(HttpSecurity http) {
        JwtAuthenticationTokenFilter customFilter = new JwtAuthenticationTokenFilter(jwtTokenUtil, userDetailService, redisTemplate);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
