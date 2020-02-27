package cn.edu.nju.madpill.controller;

import lombok.extern.java.Log;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Log
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext
public class WarehouseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get("/warehouse/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("安多霖胶囊"))
                .andExpect(jsonPath("$.data.indication").value("{\"content\":\"\"}"))
                .andExpect(jsonPath("$.data.contraindication").value("{\"content\":\"尚不明确\"}"));

        mockMvc.perform(get("/warehouse/10000000"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(404));
    }

    @Test
    void testExist() throws Exception {
        mockMvc.perform(
                get("/warehouse")
                        .param("query", "安")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].name").value("安多霖胶囊"))
                .andExpect(jsonPath("$.data[1].name").value("安坤颗粒"))
                .andExpect(jsonPath("$.data[2].name").value("安脑片"))
                .andExpect(jsonPath("$.data[3].name").value("安神补脑片"));
    }

    @Test
    void testPageOffset() throws Exception {
        mockMvc.perform(
                get("/warehouse")
                        .param("query", "安")
                        .param("start", "2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].name").value("安脑片"))
                .andExpect(jsonPath("$.data[1].name").value("安神补脑片"));
    }

    @Test
    void testNotExist() throws Exception {
        mockMvc.perform(
                get("/warehouse")
                        .param("query", "奥")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0]").doesNotExist());
    }

}
