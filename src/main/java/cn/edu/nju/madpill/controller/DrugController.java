package cn.edu.nju.madpill.controller;

import cn.edu.nju.madpill.domain.User;
import cn.edu.nju.madpill.dto.DrugDTO;
import cn.edu.nju.madpill.dto.Result;
import cn.edu.nju.madpill.exception.ExceptionSuppliers;
import cn.edu.nju.madpill.service.DrugService;
import cn.edu.nju.madpill.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static cn.edu.nju.madpill.utils.MadPillConstant.HEADER_MADPILL_TOKEN_KEY;

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
    final UserService userService;

    public DrugController(DrugService drugService, UserService userService) {
        this.drugService = drugService;
        this.userService = userService;
    }

    @PostMapping(path = "")
    public Result createNewDrug(@RequestHeader(name = HEADER_MADPILL_TOKEN_KEY) String token,
                                @RequestBody DrugDTO drugDTO) {
        Optional<User> curUser = userService.getUserByToken(token);
        if (curUser.isPresent()) {
            drugService.createNewDrug(drugDTO, curUser.get());
            return Result.builder()
                    .code(HttpStatus.OK.value())
                    .build();
        } else {
            throw ExceptionSuppliers.INVALID_TOKEN.get();
        }
    }

    @GetMapping(path = "/{drugId}")
    public Result getDrugDetail(@RequestHeader(name = HEADER_MADPILL_TOKEN_KEY) String token,
                                @PathVariable Long drugId) {
        Optional<User> curUser = userService.getUserByToken(token);
        if (curUser.isPresent()) {
            return Result.builder()
                    .data(drugService.getDrugDetail(drugId, curUser.get()))
                    .code(HttpStatus.OK.value())
                    .build();
        } else {
            throw ExceptionSuppliers.INVALID_TOKEN.get();
        }
    }

    @PutMapping(path = "/{drugId}")
    public Result modifyDrug(@RequestHeader(name = HEADER_MADPILL_TOKEN_KEY) String token,
                             @PathVariable Long drugId,
                             @RequestBody DrugDTO drugDTO) {

        Optional<User> curUser = userService.getUserByToken(token);
        if (curUser.isPresent()) {
            drugDTO.setId(drugId);
            drugService.modifyDrug(drugDTO, curUser.get());
            return Result.builder()
                    .code(HttpStatus.OK.value())
                    .build();
        } else {
            throw ExceptionSuppliers.INVALID_TOKEN.get();
        }
    }

    @DeleteMapping(path = "/{drugId}")
    public Result deleteDrug(@RequestHeader(name = HEADER_MADPILL_TOKEN_KEY) String token,
                             @PathVariable Long drugId) {
        Optional<User> curUser = userService.getUserByToken(token);
        if (curUser.isPresent()) {
            drugService.deleteDrug(drugId, curUser.get());
            return Result.builder()
                    .code(HttpStatus.OK.value())
                    .build();
        } else {
            throw ExceptionSuppliers.INVALID_TOKEN.get();
        }
    }

    @GetMapping(params = {"userId"})
    public Result getDrugs(@RequestParam("userId") Long userId) {
        return Result.builder()
                .data(drugService.getUserDrugs(userId))
                .code(HttpStatus.OK.value())
                .build();
    }
}
