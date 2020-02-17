package cn.edu.nju.madpill.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Aneureka
 * @createdAt 2020-02-17 20:58
 * @description
 **/
@Data
@Component
@ConfigurationProperties(prefix = "codec")
public class CodecConfig {

    private String key;

}