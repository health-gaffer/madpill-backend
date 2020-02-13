package cn.edu.nju.madpill.controller;

import cn.edu.nju.madpill.dto.DrugDTO;
import cn.edu.nju.madpill.service.DrugService;
import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Log
@WebMvcTest(DrugController.class)
@AutoConfigureMybatis
@MapperScan("cn.edu.nju.madpill.mapper")
class DrugControllerTest {

    private static DrugDTO dto;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DrugService drugService;

    @BeforeAll
    static void before() {
        dto = DrugDTO.builder()
                .name("测试药品")
                .producedDate(LocalDate.of(2019, 4, 7))
                .expireDate(LocalDate.now())
                .description("测试说明文字")
                .indication("测试适用症")
                .contraindication("测试禁忌")
                .build();
    }

    @Test
    void createNewDrug() throws Exception {
    }


    @Test
    void getDrugDetail() throws Exception {
        given(drugService.getDrugDetail(1L)).willReturn(DrugDTO.builder()
                .id(1L)
                .name("999")
                .build());

        mockMvc.perform(get("/drugs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.id").value(1));
    }

    @Test
    void ModifyDrug() throws Exception {
    }

    @Test
    void getDrugs() {
    }
}