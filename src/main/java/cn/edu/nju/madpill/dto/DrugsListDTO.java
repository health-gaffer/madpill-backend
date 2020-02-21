package cn.edu.nju.madpill.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 药品列表信息
 * <p>
 *
 * @author Shenmiu
 * @date 2020/2/19
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DrugsListDTO {
    /**
     * 已过期
     */
    private List<DrugBriefDTO> expired;
    /**
     * 即将过期
     */
    private List<DrugBriefDTO> expiring;
    /**
     * 未过期
     */
    private List<DrugBriefDTO> notExpired;
}
