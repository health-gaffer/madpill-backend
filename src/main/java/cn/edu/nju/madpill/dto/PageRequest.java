package cn.edu.nju.madpill.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * <p>
 * <p>
 *
 * @author Shenmiu
 * @date 2020/2/14
 */
@Data
@Builder
public class PageRequest {
    private int page;
    private int pageSize;
    private String sort;

    public PageRequest(int page, int pageSize, String sort) {
        this.page = page;
        this.pageSize = pageSize;
        this.sort = Arrays.stream(sort.trim().split(","))
                .map(str -> str.replace(".", " "))
                .collect(Collectors.joining(","));
    }
}
