package cn.edu.nju.madpill.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Log
@SpringBootTest
@AutoConfigureMockMvc
@ComponentScan(basePackages = "cn.binarywang.wx.miniapp")
@AutoConfigureMybatis
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