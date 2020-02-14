package cn.edu.nju.madpill.controller;

import cn.edu.nju.madpill.dto.DrugDTO;
import cn.edu.nju.madpill.dto.TagDTO;
import cn.edu.nju.madpill.service.DrugService;
import cn.edu.nju.madpill.service.TagService;
import com.alibaba.fastjson.JSON;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Log
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@MapperScan("cn.edu.nju.madpill.*mapper")
public class TagControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void test() throws Exception {
        // 新增
        TagDTO dto = TagDTO.builder().id(110L).name("感冒").userId(111L).build();

        mockMvc.perform(put("/tags")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JSON.toJSONString(dto)))
                .andExpect(status().isOk());

        // 获得标签
        mockMvc.perform(
                get("/tags/user?userId=111"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data[0].id").value(110))
                .andExpect(jsonPath("$.code").value(200));

        // 删除
        mockMvc.perform(
                delete("/tags/110"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));

    }
}
