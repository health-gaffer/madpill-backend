package cn.edu.nju.madpill.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * <p>
 *
 * @author Charles
 * @date 2020/2/14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseBriefDTO {
    private Long id;

    private String name;

    private String manufacture;
}
