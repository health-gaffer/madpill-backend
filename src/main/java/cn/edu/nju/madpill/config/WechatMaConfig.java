package cn.edu.nju.madpill.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.WxMaInMemoryConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Aneureka
 * @createdAt 2020-02-14 20:22
 * @description
 **/
@Configuration
public class WechatMaConfig {

    @Autowired
    private WechatAccountConfig weChatAccountConfig;

    @Bean
    public WxMaService wxMaService(){
        WxMaService wxMaService = new WxMaServiceImpl();
        wxMaService.setWxMaConfig(wxMaConfig());
        return wxMaService;
    }

    @Bean
    public WxMaConfig wxMaConfig(){
        WxMaInMemoryConfig wxMaInMemoryConfig = new WxMaInMemoryConfig();
        wxMaInMemoryConfig.setAppid(weChatAccountConfig.getAppId());
        wxMaInMemoryConfig.setSecret(weChatAccountConfig.getAppSecret());
        return wxMaInMemoryConfig;
    }
}
