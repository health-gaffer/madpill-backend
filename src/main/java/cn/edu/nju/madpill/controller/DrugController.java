package cn.edu.nju.madpill.controller;

import cn.edu.nju.madpill.dto.Result;
import cn.edu.nju.madpill.service.DrugService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * <p>
 *
 * @author Shenmiu
 * @date 2020/2/6
 */
@RestController
@RequestMapping(path = "/drugs")
public class DrugController {

    final DrugService drugService;

    public DrugController(DrugService drugService) {
        this.drugService = drugService;
    }

    @GetMapping(path = "/{drugId}")
    public Result getDrugDetail(@PathVariable Long drugId) {
        return Result.builder()
                .data(drugService.getDrugDetail(drugId))
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping(params = {"userId"})
    public Result getDrugs(@RequestParam("userId") Long userId) {
        return Result.builder()
                .data(drugService.getUserDrugs(userId))
                .code(HttpStatus.OK.value())
                .build();
    }
}
