package cn.edu.nju.madpill.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * 药品详细信息
 * <p>
 *
 * @author Shenmiu
 * @date 2020/2/6
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DrugDTO {

    private Long id;

    private String name;

    private LocalDate producedDate;

    private LocalDate expireDate;

    private String description;

    private String indication;

    private String contraindication;

    private List<TagDTO> tags;

    private GroupBriefDTO group;
}
