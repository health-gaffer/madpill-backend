package cn.edu.nju.madpill.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.edu.nju.madpill.dto.Result;
import cn.edu.nju.madpill.service.UserService;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    public UserController(WxMaService wxMaService, UserService userService) {
        this.wxMaService = wxMaService;
        this.userService = userService;
    }

    @PostMapping(value = "/")
    @ResponseBody
    public Result login(String code){
        try {
            WxMaJscode2SessionResult sessionResult = wxMaService.getUserService().getSessionInfo(code);
            String openId = sessionResult.getOpenid();
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return new Result();
    }

}
