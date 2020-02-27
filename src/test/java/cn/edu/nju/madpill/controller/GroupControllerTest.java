package cn.edu.nju.madpill.controller;

import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static cn.edu.nju.madpill.utils.MadPillConstant.HEADER_MADPILL_TOKEN_KEY;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Log
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext
class GroupControllerTest {

    private static final String HEADER_MADPILL_TOKEN_VALUE = "AlgNG1FZKjpXLSkqPz5cDDw/Cw04CQIGQxAvOU0uFSIcIEc1E10gWS0HLlIPOyI0AiAwWU0=";
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getGroups() throws Exception {
        mockMvc.perform(
                get("/groups")
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[0].name").value("测试群组1"))
                .andExpect(jsonPath("$.data[1].id").value(2))
                .andExpect(jsonPath("$.data[1].name").value("测试群组2"));
    }
    @Test
    void createGroup() throws Exception {
        mockMvc.perform(
                post("/groups")
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE)
                        .content("老家的药箱"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }
}