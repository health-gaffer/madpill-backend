package cn.edu.nju.madpill.controller;

import cn.edu.nju.madpill.domain.User;
import cn.edu.nju.madpill.dto.Result;
import cn.edu.nju.madpill.dto.TagDTO;
import cn.edu.nju.madpill.exception.ExceptionSuppliers;
import cn.edu.nju.madpill.service.TagService;
import cn.edu.nju.madpill.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static cn.edu.nju.madpill.utils.MadPillConstant.HEADER_MADPILL_TOKEN_KEY;

/**
 * <p>
 * <p>
 *
 * @author gyyyy
 * @date 2020/2/11
 */
@RestController
@RequestMapping(path = "/tags")
public class TagController {

    final TagService tagService;
    final UserService userService;

    public TagController(TagService tagService, UserService userService) {
        this.tagService = tagService;
        this.userService = userService;
    }

    @GetMapping(path = "")
    public Result getTagsOfUser(@RequestHeader(name = HEADER_MADPILL_TOKEN_KEY) String token) {
        Optional<User> curUser = userService.getUserByToken(token);
        if (curUser.isPresent()) {
            return Result.builder()
                    .data(tagService.getTagsOfUser(curUser.get().getId()))
                    .code(HttpStatus.OK.value())
                    .build();
        } else {
            throw ExceptionSuppliers.INVALID_TOKEN.get();
        }

    }

    @DeleteMapping(path = "/{tagId}")
    public Result deleteTag(@RequestHeader(name = HEADER_MADPILL_TOKEN_KEY) String token,
                            @PathVariable("tagId") Long tagId) {
        Optional<User> curUser = userService.getUserByToken(token);
        if (curUser.isPresent()) {
            tagService.deleteTag(tagId, curUser.get().getId());
            return Result.builder()
                    .code(HttpStatus.OK.value())
                    .build();
        } else {
            throw ExceptionSuppliers.INVALID_TOKEN.get();
        }
    }

    @PostMapping(path = "")
    public Result addNewTag(@RequestHeader(name = HEADER_MADPILL_TOKEN_KEY) String token, @RequestBody TagDTO tagDTO) {
        Optional<User> curUser = userService.getUserByToken(token);
        if (curUser.isPresent()) {
            return Result.builder()
                    .data(tagService.addNewTag(tagDTO, curUser.get().getId()))
                    .code(HttpStatus.OK.value())
                    .build();
        } else {
            throw ExceptionSuppliers.INVALID_TOKEN.get();
        }
    }
}
