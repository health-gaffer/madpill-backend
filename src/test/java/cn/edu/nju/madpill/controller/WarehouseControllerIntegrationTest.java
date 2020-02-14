package cn.edu.nju.madpill.controller;

import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Log
@SpringBootTest
@AutoConfigureMockMvc
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
                .andExpect(jsonPath("$.data.list[0].name").value("aneureka奥8"))
                .andExpect(jsonPath("$.data.list[1].name").value("arthur奥10"))
                .andExpect(jsonPath("$.data.list[2].name").value("charles奥4"));
    }

}
