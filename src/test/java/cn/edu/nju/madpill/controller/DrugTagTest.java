package cn.edu.nju.madpill.controller;

import cn.edu.nju.madpill.dto.DrugDTO;
import cn.edu.nju.madpill.dto.GroupBriefDTO;
import cn.edu.nju.madpill.dto.TagDTO;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

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
@ActiveProfiles("test")
@Rollback
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

    private final long lastTagId = 1005;

    @Test
    void addTag() throws Exception {

        // 新增标签
        TagDTO dto1 = getTagDto();
        mockMvc.perform(
                post("/tags")
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tagJson.write(dto1).getJson()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(lastTagId + 1));
    }

    @Test
    void testModifyDrug() throws Exception {
        // 新增药品
        MvcResult result = mockMvc.perform(
                post("/drugs")
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(drugJson.write(drugDTO).getJson()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        long newDrugId = JsonPath.parse(result.getResponse().getContentAsString()).read("$.data", Long.class);

        // 修改标签
        TagDTO thirdTag = getTagDto();
        thirdTag.setId(1004L);
        TagDTO fourthTag = getTagDto();
        fourthTag.setId(1005L);
        List<TagDTO> modifiedTags = Arrays.asList(thirdTag, fourthTag);

        drugDTO.setTags(modifiedTags);
        mockMvc.perform(
                put("/drugs/" + newDrugId)
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(drugJson.write(drugDTO).getJson()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // 检查标签
        mockMvc.perform(
                get("/drugs/" + newDrugId)
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.tags[0].id").value(1004))
                .andExpect(jsonPath("$.data.tags[0].name").value("测试标签 3"))
                .andExpect(jsonPath("$.data.tags[1].id").value(1005))
                .andExpect(jsonPath("$.data.tags[1].name").value("测试标签 4"))
                .andExpect(jsonPath("$.data.tags[2].id").doesNotExist());
    }

    @Test
    void deleteTag() throws Exception {
        mockMvc.perform(
                delete("/tags/" + (1003))
                        .header(HEADER_MADPILL_TOKEN_KEY, HEADER_MADPILL_TOKEN_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }

    private TagDTO getTagDto() {
        return TagDTO.builder()
                .name("测试标签")
                .build();
    }

    private DrugDTO getDrugDto() {
        TagDTO tag3 = getTagDto();
        tag3.setId(1002L);
        TagDTO tag4 = getTagDto();
        tag4.setId(1003L);
        List<TagDTO> tags = Arrays.asList(tag3, tag4);

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
