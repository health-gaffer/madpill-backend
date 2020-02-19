package cn.edu.nju.madpill.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Aneureka
 * @createdAt 2020-02-14 20:21
 * @description
 **/
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    private String appId;

    private String appSecret;

}
