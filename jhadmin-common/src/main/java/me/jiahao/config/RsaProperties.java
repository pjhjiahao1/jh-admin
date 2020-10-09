package me.jiahao.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author : panjiahao
 * @date : 15:13 2020/9/18
 */
@Data
@Component
public class RsaProperties {

    public static String privateKey;
    public static String publickey;

    @Value("${rsa.private_key}")
    public void setPrivateKey(String privateKey) {
        RsaProperties.privateKey = privateKey;
    }

    @Value("${rsa.public_key}")
    public void setPublicKey(String publickey) {
        RsaProperties.publickey = publickey;
    }
}
