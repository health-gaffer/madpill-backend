package cn.edu.nju.madpill.controller;

import cn.edu.nju.madpill.dto.DrugDTO;
import com.alibaba.fastjson.JSON;
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

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Log
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@MapperScan("cn.edu.nju.madpill.*mapper")
public class DrugControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test() throws Exception {
        // 新增
        DrugDTO dto = getDto();
        dto.setId(100000000L);
        mockMvc.perform(
                post("/drugs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(dto)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // 修改
        dto.setName("测试药品100000000");
        mockMvc.perform(
                put("/drugs/100000000")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(dto)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(get("/drugs/100000000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.name").value("测试药品100000000"));

        // 删除
        mockMvc.perform(
                delete("/drugs/100000000")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));

        mockMvc.perform(
                delete("/drugs/100000000000")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(404));
    }

    private DrugDTO getDto() {
        return DrugDTO.builder()
                .name("测试药品3")
                .producedDate(LocalDate.of(2019, 4, 7))
                .expireDate(LocalDate.now())
                .description("测试说明文字3")
                .indication("测试适用症3")
                .contraindication("测试禁忌3")
                .build();
    }

}
