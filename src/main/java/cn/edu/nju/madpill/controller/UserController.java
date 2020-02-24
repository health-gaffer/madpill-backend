package cn.edu.nju.madpill.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.edu.nju.madpill.domain.User;
import cn.edu.nju.madpill.dto.Result;
import cn.edu.nju.madpill.dto.TagDTO;
import cn.edu.nju.madpill.exception.ExceptionSuppliers;
import cn.edu.nju.madpill.service.UserService;
import me.chanjar.weixin.common.error.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static cn.edu.nju.madpill.utils.MadPillConstant.HEADER_MADPILL_TOKEN_KEY;

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

    @GetMapping(path = "/groups")
    public Result getGroup(@RequestHeader(name = HEADER_MADPILL_TOKEN_KEY) String token) {
        Optional<User> curUser = userService.getUserByToken(token);
        if (curUser.isPresent()) {
            return Result.builder()
                    .data(userService.getGroups(curUser.get()))
                    .code(HttpStatus.OK.value())
                    .build();
        } else {
            throw ExceptionSuppliers.INVALID_TOKEN.get();
        }
    }

    @PostMapping(path = "/groups")
    public Result createGroup(@RequestHeader(name = HEADER_MADPILL_TOKEN_KEY) String token, @RequestBody String name) {
        System.out.println(token  + "   " + name);
        Optional<User> curUser = userService.getUserByToken(token);
        if (curUser.isPresent()) {
            return Result.builder()
                    .data(userService.newGroup(name, curUser.get(), false))
                    .code(HttpStatus.OK.value())
                    .build();
        } else {
            throw ExceptionSuppliers.INVALID_TOKEN.get();
        }
    }

}
