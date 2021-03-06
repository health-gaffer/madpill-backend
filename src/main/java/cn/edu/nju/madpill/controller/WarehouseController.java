package cn.edu.nju.madpill.controller;

import cn.edu.nju.madpill.dto.Result;
import cn.edu.nju.madpill.service.WarehouseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "")
    public Result search(@RequestParam("query") String query,
                         @RequestParam(name = "start", defaultValue = "0") long start) {
        return Result.builder()
                .data(warehouseService.getWarehouses(query, start))
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping(path = "/{warehouseId}")
    public Result getWarehouse(@PathVariable("warehouseId") Long warehouseId) {
        return Result.builder()
                .data(warehouseService.getWarehouse(warehouseId))
                .code(HttpStatus.OK.value())
                .build();
    }
}
