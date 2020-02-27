package cn.edu.nju.madpill.controller;

import cn.edu.nju.madpill.domain.User;
import cn.edu.nju.madpill.dto.Result;
import cn.edu.nju.madpill.exception.ExceptionSuppliers;
import cn.edu.nju.madpill.service.GroupService;
import cn.edu.nju.madpill.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static cn.edu.nju.madpill.utils.MadPillConstant.HEADER_MADPILL_TOKEN_KEY;

/**
 * <p>
 * <p>
 *
 * @author Charles
 * @date 2020/2/24
 */
@RestController
@RequestMapping(path = "/groups")
public class GroupController {

    final GroupService groupService;
    final UserService userService;

    public GroupController(GroupService groupService, UserService userService) {
        this.groupService = groupService;
        this.userService = userService;
    }


    @GetMapping(path = "")
    public Result getGroup(@RequestHeader(name = HEADER_MADPILL_TOKEN_KEY) String token) {
        Optional<User> curUser = userService.getUserByToken(token);
        if (curUser.isPresent()) {
            return Result.builder()
                    .data(groupService.getGroups(curUser.get()))
                    .code(HttpStatus.OK.value())
                    .build();
        } else {
            throw ExceptionSuppliers.INVALID_TOKEN.get();
        }
    }

    @PostMapping(path = "")
    public Result createGroup(@RequestHeader(name = HEADER_MADPILL_TOKEN_KEY) String token, @RequestBody String name) {
        Optional<User> curUser = userService.getUserByToken(token);
        if (curUser.isPresent()) {
            return Result.builder()
                    .data(groupService.newGroup(name, curUser.get(), false))
                    .code(HttpStatus.OK.value())
                    .build();
        } else {
            throw ExceptionSuppliers.INVALID_TOKEN.get();
        }
    }
}
