package cn.edu.nju.madpill.controller;

import cn.edu.nju.madpill.dto.PageRequest;
import cn.edu.nju.madpill.dto.Result;
import cn.edu.nju.madpill.service.WarehouseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * <p>
 *
 * @author Charles
 * @date 2020/2/13
 */
@RestController
@RequestMapping(path = "/warehouse")
public class WarehouseController {

    final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping(params = {"query"})
    public Result search(@RequestParam("query") String query,
                         @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize,
                         // url 中 sort 的参数格式为 sort=field1.desc,field2.asc，field 为数据库中的字段
                         @RequestParam(name = "sort", required = false, defaultValue = "name.asc") String sort) {
        PageRequest pageRequest = PageRequest.builder()
                .page(page)
                .pageSize(pageSize)
                .sort(sort)
                .build();
        return Result.builder()
                .data(warehouseService.getWarehouses(query, pageRequest))
                .code(HttpStatus.OK.value())
                .build();
    }
}
