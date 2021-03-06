package cn.edu.nju.madpill.controller;

import cn.edu.nju.madpill.dto.DrugDTO;
import cn.edu.nju.madpill.dto.GroupBriefDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static cn.edu.nju.madpill.utils.MadPillConstant.HEADER_MADPILL_TOKEN_KEY;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Log
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@DirtiesContext
public class DrugControllerTest {

    private static final String HEADER_MADPILL_TOKEN_VALUE = "AlgNG1FZKjpXLSkqPz5cDDw/Cw04CQIGQxAvOU0uFSIcIEc1E10gWS0HLlIPOyI0AiAwWU0=";
    @Autowired
    private MockMvc mockMvc;
    private DrugDTO dto = getDto();
    @Autowired
    private JacksonTester<DrugDTO> json;

    @Autowired
    private ObjectMapper objectMapper;

    private final long toAddDrugId = 120;

    @Test
    @Order(1)
    void addDrug() throws Exception {
        // 新增
        mockMvc.perform(
                post("/drugs")
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.write(dto).getJson()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    void testGetDrugDetail() throws Exception {
        mockMvc.perform(
                get("/drugs/" + toAddDrugId)
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.name").value("测试药品" + toAddDrugId));
    }


    @Test
    @Order(3)
    void modifyDrug() throws Exception {
        // 修改
        dto.setName("测试药品" + toAddDrugId + "修改");
        mockMvc.perform(
                put("/drugs/" + toAddDrugId)
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.write(dto).getJson()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(
                get("/drugs/" + toAddDrugId)
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.name").value("测试药品" + toAddDrugId + "修改"));
    }

    @Test
    @Order(4)
    void deleteDrug() throws Exception {
        // 删除
        mockMvc.perform(
                delete("/drugs/"+toAddDrugId)
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));

        mockMvc.perform(
                delete("/drugs/10")
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(404));
    }

    @Test
    @Order(5)
    void testInvalidToken() throws Exception {
        final String INVALID_TOKEN = "invalid_token";
        mockMvc.perform(
                post("/drugs")
                        .header(HEADER_MADPILL_TOKEN_KEY, INVALID_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.write(dto).getJson()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(401));

        mockMvc.perform(
                get("/drugs/1")
                        .header(HEADER_MADPILL_TOKEN_KEY, INVALID_TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(401));

        mockMvc.perform(
                put("/drugs/1")
                        .header(HEADER_MADPILL_TOKEN_KEY, INVALID_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.write(dto).getJson()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(401));

        mockMvc.perform(
                delete("/drugs/1")
                        .header(HEADER_MADPILL_TOKEN_KEY, INVALID_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(401));
    }

    @Test
    @Order(6)
    void testNoPermission() throws Exception {
        mockMvc.perform(
                get("/drugs/100")
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(403));

        mockMvc.perform(
                put("/drugs/100")
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.write(dto).getJson()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(403));

        mockMvc.perform(
                delete("/drugs/100")
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(403));
    }

    @Test
    @Order(7)
    void testGroupForeignerKeyWrong() throws Exception {
        mockMvc.perform(
                get("/drugs/2")
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(500));
    }

    @Test
    @Order(8)
    void testDeleteDrugs() throws Exception {
        List<Long> selectedDrugsId = Arrays.asList(2L, 119L);
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(selectedDrugsId);
        mockMvc.perform(
                delete("/drugs")
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));

        mockMvc.perform(
                get("/drugs")
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE)
                        .param("group", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..expired.length()").value(0))
                .andExpect(jsonPath("$..expiring.length()").value(0))
                .andExpect(jsonPath("$..notExpired.length()").value(0));
    }

    @Test
    @Order(9)
    void testBatchChangeGroup() throws Exception {
        String myToken = "AlgNG1FZKwQsEEk2KCgBAlEcOSI7DwwKMigAH004LyUAWhpcEFApXl9fAC8OJFsdKVowWU0=";
        List<Long> selectedDrugsId = Arrays.asList(3L, 4L);
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(selectedDrugsId);
        mockMvc.perform(
                put("/drugs")
                        .header(HEADER_MADPILL_TOKEN_KEY, myToken)
                        .param("destGroup", "4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
        mockMvc.perform(
                get("/drugs")
                        .header(HEADER_MADPILL_TOKEN_KEY, myToken)
                        .param("group", "4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..expired.length()").value(2))
                .andExpect(jsonPath("$..expiring.length()").value(0))
                .andExpect(jsonPath("$..notExpired.length()").value(0));
    }

    private DrugDTO getDto() {
        return DrugDTO.builder()
                .name("测试药品" + toAddDrugId)
                .producedDate(LocalDate.of(2019, 4, 7))
                .expireDate(LocalDate.now())
                .description("测试说明文字" + toAddDrugId)
                .indication("{\"content\":\"适用症\"}")
                .contraindication("{\"content\":\"禁忌\"}")
                .group(GroupBriefDTO.builder().id(1L).build())
                .build();
    }

}
