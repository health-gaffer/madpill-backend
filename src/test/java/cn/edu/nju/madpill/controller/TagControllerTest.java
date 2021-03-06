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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static cn.edu.nju.madpill.utils.MadPillConstant.HEADER_MADPILL_TOKEN_KEY;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Log
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@DirtiesContext
public class TagControllerTest {
    private static final String HEADER_MADPILL_TOKEN_VALUE = "AlgNG1FZKjpXLSkqPz5cDDw/Cw04CQIGQxAvOU0uFSIcIEc1E10gWS0HLlIPOyI0AiAwWU0=";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<TagDTO> json;

    private final long lastTagId = 1005;

    @Test
    @Order(1)
    void testAddTag() throws Exception {
        // 新增
        TagDTO dto = TagDTO.builder().name("感冒").build();

        mockMvc.perform(
                post("/tags")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE)
                        .content(json.write(dto).getJson()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(lastTagId + 1))
                .andExpect(jsonPath("$.code").value(200));
    }

    @Test
    @Order(2)
    void testGetTag() throws Exception {
        // 获得标签
        mockMvc.perform(
                get("/tags")
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data[0].id").exists())
                .andExpect(jsonPath("$.code").value(200));
    }

    @Test
    @Order(3)
    void testDeleteTag() throws Exception {
        // 删除
        mockMvc.perform(
                delete("/tags/" + (lastTagId + 1))
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }

    @Test
    @Order(4)
    void testInvalidToken() throws Exception {
        final String INVALID_TOKEN = "invalid_token";
        final TagDTO dto = TagDTO.builder().name("感冒").build();
        mockMvc.perform(
                post("/tags")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HEADER_MADPILL_TOKEN_KEY, INVALID_TOKEN)
                        .content(json.write(dto).getJson()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(401));

        mockMvc.perform(
                get("/tags")
                        .header(HEADER_MADPILL_TOKEN_KEY, INVALID_TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(401));

        mockMvc.perform(
                delete("/tags/101")
                        .header(HEADER_MADPILL_TOKEN_KEY, INVALID_TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(401));
    }

    @Test
    @Order(5)
    void testNoPermission() throws Exception {
        mockMvc.perform(
                delete("/tags/101")
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(403));
    }
}
