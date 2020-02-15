package cn.edu.nju.madpill.controller;

import cn.edu.nju.madpill.dto.TagDTO;
import lombok.extern.java.Log;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Log
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TagControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<TagDTO> json;

    @Test
    @Order(1)
    void testAddTag() throws Exception {
        // 新增
        TagDTO dto = TagDTO.builder().id(110L).name("感冒").userId(111L).build();

        mockMvc.perform(put("/tags")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.write(dto).getJson()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.code").value(200));
    }

    @Test
    @Order(2)
    void testGetTag() throws Exception {
        // 获得标签
        mockMvc.perform(
                get("/tags/user?userId=111"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data[0].id").value(110))
                .andExpect(jsonPath("$.code").value(200));
    }

    @Test
    @Order(3)
    void testDeleteTag() throws Exception {
        // 删除
        mockMvc.perform(
                delete("/tags/110"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }
}
