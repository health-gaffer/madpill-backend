package cn.edu.nju.madpill.controller;

import cn.edu.nju.madpill.dto.DrugDTO;
import cn.edu.nju.madpill.dto.GroupBriefDTO;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
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
public class DrugTagTest {

    private static final String HEADER_MADPILL_TOKEN_VALUE = "AlgNG1FZKjpXLSkqPz5cDDw/Cw04CQIGQxAvOU0uFSIcIEc1E10gWS0HLlIPOyI0AiAwWU0=";
    private DrugDTO drugDTO = getDrugDto();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<TagDTO> tagJson;

    @Autowired
    private JacksonTester<DrugDTO> drugJson;

    private final long toAddDrugId = 120;

    @Test
    @Order(1)
    void addTag() throws Exception {
        // 新增标签
        TagDTO dto1 = getTagDto(1);
        mockMvc.perform(
                post("/tags")
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tagJson.write(dto1).getJson()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(1));

        TagDTO dto2 = getTagDto(2);
        mockMvc.perform(
                post("/tags")
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tagJson.write(dto2).getJson()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(2));

        TagDTO dto3 = getTagDto(3);
        mockMvc.perform(
                post("/tags")
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tagJson.write(dto3).getJson()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(3));

        TagDTO dto4 = getTagDto(4);
        mockMvc.perform(
                post("/tags")
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tagJson.write(dto4).getJson()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(4));
    }

    @Test
    @Order(2)
    void addDrug() throws Exception {
        // 新增药品
        mockMvc.perform(
                post("/drugs")
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(drugJson.write(drugDTO).getJson()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    void checkDrugTag() throws Exception {
        // 检查标签
        mockMvc.perform(
                get("/drugs/" + toAddDrugId)
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.tags").exists())
                .andExpect(jsonPath("$.data.tags[0].id").value(1))
                .andExpect(jsonPath("$.data.tags[0].name").value("测试标签"))
                .andExpect(jsonPath("$.data.tags[1].id").value(2))
                .andExpect(jsonPath("$.data.tags[1].name").value("测试标签"))
                .andExpect(jsonPath("$.data.tags[2].id").doesNotExist());
    }


    @Test
    @Order(4)
    void modifyDrug() throws Exception {
        // 修改标签
        List<TagDTO> modifiedTags = new ArrayList<>();
        modifiedTags.add(getTagDto(3));
        modifiedTags.add(getTagDto(4));

        drugDTO.setTags(modifiedTags);
        mockMvc.perform(
                put("/drugs/" + toAddDrugId)
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(drugJson.write(drugDTO).getJson()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    void checkModifyDrugTag() throws Exception {
        // 检查标签
        mockMvc.perform(
                get("/drugs/" + toAddDrugId)
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.tags[0].id").value(3))
                .andExpect(jsonPath("$.data.tags[0].name").value("测试标签"))
                .andExpect(jsonPath("$.data.tags[1].id").value(4))
                .andExpect(jsonPath("$.data.tags[1].name").value("测试标签"))
                .andExpect(jsonPath("$.data.tags[2].id").doesNotExist());
    }

    @Test
    @Order(6)
    void deleteDrug() throws Exception {
        // 删除药品
        mockMvc.perform(
                delete("/drugs/" + toAddDrugId)
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));

        // 删除标签
        mockMvc.perform(
                delete("/tags/1")
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
        mockMvc.perform(
                delete("/tags/2")
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
        mockMvc.perform(
                delete("/tags/3")
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
        mockMvc.perform(
                delete("/tags/4")
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }

    private TagDTO getTagDto(long tagId) {
        return TagDTO.builder()
                .id(tagId)
                .name("测试标签")
                .build();
    }

    private DrugDTO getDrugDto() {
        List<TagDTO> tags = new ArrayList<>();
        tags.add(getTagDto(1));
        tags.add(getTagDto(2));

        return DrugDTO.builder()
                .name("测试药品" + toAddDrugId)
                .producedDate(LocalDate.of(2019, 4, 7))
                .expireDate(LocalDate.now())
                .description("测试说明文字" + toAddDrugId)
                .indication("{\"content\":\"适用症\"}")
                .contraindication("{\"content\":\"禁忌\"}")
                .tags(tags)
                .group(GroupBriefDTO.builder().id(1L).build())
                .build();
    }

}
