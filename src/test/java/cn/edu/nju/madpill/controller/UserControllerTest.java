package cn.edu.nju.madpill.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.edu.nju.madpill.dto.DrugDTO;
import cn.edu.nju.madpill.service.DrugService;
import cn.edu.nju.madpill.service.UserService;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Log
@WebMvcTest(UserController.class)
@ComponentScan(basePackages = "cn.binarywang.wx.miniapp")
@AutoConfigureMybatis
@MapperScan("cn.edu.nju.madpill.*mapper")
@MockBeans({@MockBean(WxMaService.class)})
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void login() throws Exception {
//        mockMvc.perform(post("/users").content("013xjpFw1oEKWe0AXnIw1h5hFw1xjpFN"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data").exists());
    }
}