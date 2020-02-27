package cn.edu.nju.madpill.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户的群组简要信息
 * <p>
 *
 * @author Charles
 * @date 2020/2/21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupBriefDTO {

    private Long id;

    private String name;
}
