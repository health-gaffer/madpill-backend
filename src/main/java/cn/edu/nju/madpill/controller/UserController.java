package cn.edu.nju.madpill.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.edu.nju.madpill.dto.Result;
import cn.edu.nju.madpill.service.UserService;
import me.chanjar.weixin.common.error.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aneureka
 * @createdAt 2020-02-14 20:17
 * @description
 **/

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final WxMaService wxMaService;

    private final UserService userService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserController(WxMaService wxMaService, UserService userService) {
        this.wxMaService = wxMaService;
        this.userService = userService;
    }

    @PostMapping(value = "")
    public Result login(@RequestBody String code) {
        try {
            WxMaJscode2SessionResult sessionResult = wxMaService.getUserService().getSessionInfo(code);
            String openId = sessionResult.getOpenid();
            String sessionKey = sessionResult.getSessionKey();
            userService.addUserIfAbsent(openId);
            return Result.builder()
                    .code(HttpStatus.OK.value())
                    .data(userService.generateToken(openId, sessionKey))
                    .build();
        } catch (WxErrorException e) {
            logger.error(e.getMessage());
            return Result.builder()
                    .code(HttpStatus.UNAUTHORIZED.value())
                    .msg("Missed or invalid code.")
                    .build();
        }
    }
}
