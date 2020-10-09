package me.jiahao.modules.security.controller;

import cn.hutool.core.util.IdUtil;
import com.wf.captcha.base.Captcha;
import me.jiahao.annotation.AnonymousAccess;
import me.jiahao.annotation.rest.AnonymousDeleteMapping;
import me.jiahao.config.LoginCodeEnum;
import me.jiahao.config.LoginProperties;
import me.jiahao.config.RsaProperties;
import me.jiahao.exception.R;
import me.jiahao.modules.security.entity.AuthUserEntity;
import me.jiahao.modules.security.entity.UserDetailsEntity;
import me.jiahao.modules.security.security.JwtTokenUtil;
import me.jiahao.modules.security.service.AuthorizationService;
import me.jiahao.utils.RsaUtils;
import me.jiahao.utils.SecurityUtils;
import me.jiahao.utils.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author : panjiahao
 * @date : 13:46 2020/9/10
 */
@RestController
@RequestMapping("/auth")
public class AuthorizationController {

    @Resource
    AuthorizationService authorizationService;
    @Resource
    RedisTemplate redisTemplate;
    @Resource
    private LoginProperties loginProperties;
    @Resource
    JwtTokenUtil jwtTokenUtil;

    @AnonymousAccess
    @GetMapping(value = "/code")
    public R getCode() {
        // 获取运算的结果
        Captcha captcha = loginProperties.getCaptcha();
        Map<String,Object> imgResult = new HashMap<>();
        String uuid = IdUtil.simpleUUID();
        //当验证码类型为 arithmetic时且长度 >= 2 时，captcha.text()的结果有几率为浮点型
        String captchaValue = captcha.text();
        if (captcha.getCharType() - 1 == LoginCodeEnum.arithmetic.ordinal() && captchaValue.contains(".")) {
            captchaValue = captchaValue.split("\\.")[0];
        }
        String base64 = captcha.toBase64();
        imgResult = new HashMap<String,Object>(){{
            put("img", base64);
            put("uuid", uuid);
        }};
        // 保存验证码为两分钟
        redisTemplate.opsForValue().set(jwtTokenUtil.getCodeKey() + uuid,captchaValue, loginProperties.getLoginCode().getExpiration(), TimeUnit.MINUTES);
        return R.success(imgResult);
    }

    @AnonymousAccess
    @PostMapping(value = "/login")
    public R login(@RequestBody AuthUserEntity authUser, HttpServletRequest request) throws Exception{
        String username =authUser.getUsername();
        String password = authUser.getPassword();
        String captcode = authUser.getCaptcode();
        String uuid = authUser.getUuid();
        // 根据uuid 获取redis中存储的验证码
        String redisCaptCode = (String) redisTemplate.opsForValue().get(jwtTokenUtil.getCodeKey() + uuid);
        if (StringUtils.isNotBlank(redisCaptCode)) {
            if (!captcode.equals(redisCaptCode)) {
                return R.warn("验证码输入错误");
            }
        } else {
            return R.warn("验证码已过期,请重新刷新验证码!");
        }
        //解密后密码
        String decryPassword = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey, password);
        Map<String, Object> userMap = authorizationService.login(username, decryPassword,password,request);
        return R.success(userMap);
    }

    @GetMapping(value = "/info")
    public R userInfo (HttpServletRequest request) {
        UserDetailsEntity currentUser = (UserDetailsEntity) SecurityUtils.getCurrentUser();
        return R.success(currentUser);
    }

    @DeleteMapping(value = "/logout")
    @AnonymousDeleteMapping
    public R logout(HttpServletRequest request) {
        String header = request.getHeader(jwtTokenUtil.getHeader());
        redisTemplate.delete(jwtTokenUtil.getOnlineKey() + header);
        return R.success();
    }
}
