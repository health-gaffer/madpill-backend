package cn.edu.nju.madpill.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * 药品简要信息
 * <p>
 *
 * @author Shenmiu
 * @date 2020/2/6
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DrugBriefDTO {

    private Long id;

    private String name;

    private LocalDate expireDate;

    private Long day;

    private List<TagDTO> tags;

}
