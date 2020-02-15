package cn.edu.nju.madpill.controller;

import cn.edu.nju.madpill.dto.DrugDTO;
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

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Log
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DrugControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private DrugDTO dto = getDto();

    @Autowired
    private JacksonTester<DrugDTO> json;

    @Test
    @Order(1)
    void addDrug() throws Exception {
        // 新增
        dto.setId(1L);
        mockMvc.perform(
                post("/drugs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.write(dto).getJson()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    void testGetDrugDetail() throws Exception {
        mockMvc.perform(get("/drugs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.name").value("测试药品1"));
    }


    @Test
    @Order(3)
    void modifyDrug() throws Exception {
        // 修改
        dto.setName("测试药品1修改");
        mockMvc.perform(
                put("/drugs/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.write(dto).getJson()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(get("/drugs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.name").value("测试药品1修改"));
    }

    @Test
    @Order(4)
    void deleteDrug() throws Exception {

        // 删除
        mockMvc.perform(
                delete("/drugs/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));

        mockMvc.perform(
                delete("/drugs/10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(404));
    }

    private DrugDTO getDto() {
        return DrugDTO.builder()
                .name("测试药品1")
                .producedDate(LocalDate.of(2019, 4, 7))
                .expireDate(LocalDate.now())
                .description("测试说明文字1")
                .indication("测试适用症1")
                .contraindication("测试禁忌1")
                .build();
    }

}
