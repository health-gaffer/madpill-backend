package cn.edu.nju.madpill.controller;

import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Log
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@MapperScan("cn.edu.nju.madpill.*mapper")
public class WarehouseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test() throws Exception {
        mockMvc.perform(
                get("/warehouse")
                        .param("query", "奥")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].name").value("奥1"))
                .andExpect(jsonPath("$.data[1].name").value("奥2"))
                .andExpect(jsonPath("$.data[2].name").value("奥3"))
                .andExpect(jsonPath("$.data[3].name").value("charles奥4"));
    }

}
