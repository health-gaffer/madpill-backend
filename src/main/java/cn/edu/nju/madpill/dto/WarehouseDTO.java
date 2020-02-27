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
 * @date 2020/2/13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseDTO {
    private String name;

    private String ingredient;

    private String character;

    private String function;

    private String indication;

    private String usage;

    private String adverseEffect;

    private String contraindication;

    private String warning;

    private String storage;

    private String indate;

    private String specification;
}
