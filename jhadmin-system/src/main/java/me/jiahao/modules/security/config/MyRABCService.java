package me.jiahao.modules.security.config;

import me.jiahao.modules.security.mapper.UserMenuRoleMeaasgeMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author : panjiahao
 * @date : 15:41 2020/9/17
 */
@Component("rabcService")
public class MyRABCService {
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Resource
    private UserMenuRoleMeaasgeMapper userMenuRoleMeaasgeMapper;

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            List<String> urls = userMenuRoleMeaasgeMapper.getObjectFromName(username);
            String uri = request.getRequestURI();
            boolean flag= urls.stream().anyMatch(
                    url -> antPathMatcher.match(url,uri)
            );
            return flag;
        }
        return false;
    }
}
