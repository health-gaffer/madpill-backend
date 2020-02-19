package cn.edu.nju.madpill.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一响应
 * <p>
 *
 * @author Shenmiu
 * @date 2020/2/6
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Object data;
    private int code;
    private String msg;
}
