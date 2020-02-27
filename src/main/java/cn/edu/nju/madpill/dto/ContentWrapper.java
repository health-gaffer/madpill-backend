package cn.edu.nju.madpill.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 包装适用症和禁忌
 * <p>
 *
 * @author Charles
 * @date 2020/2/27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContentWrapper {
    private String content;
}
