package cn.edu.nju.madpill.controller;

import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Log
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class WarehouseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get("/warehouse/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("奥硝唑片"))
                .andExpect(jsonPath("$.data.manufacture").value("潇然"))
                .andExpect(jsonPath("$.data.indication").value("{\"content\":\"1.治疗原虫感染-毛滴虫感染（泌尿生殖感染）阿米巴原虫感染（肠、肝阿米巴虫病、阿米巴痢疾、阿米巴脓肿），贾第鞭毛虫病；2.治疗厌氧菌感染（如败血症脑膜炎、腹膜炎、手术后伤口感染、产后脓毒病、脓毒性流产、子宫内膜炎以及敏感菌引起的其它感染），预防各种手术后厌氧菌感染。\"}"))
                .andExpect(jsonPath("$.data.contraindication").value("{\"content\":\"对硝基咪唑类药物过敏的患者对此药也过敏，禁用于对此药过敏的患者；也禁用于脑和脊髓发生病原菌变的患者，羊癫疯及各种器官硬化症患者。\"}"));

        mockMvc.perform(get("/warehouse/10000000"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(404));
    }

    @Test
    void testExist() throws Exception {
        mockMvc.perform(
                get("/warehouse")
                        .param("query", "奥")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].name").value("奥硝唑片"))
                .andExpect(jsonPath("$.data[1].name").value("奥2"))
                .andExpect(jsonPath("$.data[2].name").value("奥3"))
                .andExpect(jsonPath("$.data[3].name").value("charles奥4"));
    }

    @Test
    void testNotExist() throws Exception {
        mockMvc.perform(
                get("/warehouse")
                        .param("query", "奥")
                        .param("start", "10000000")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].name").doesNotExist());
    }

}
