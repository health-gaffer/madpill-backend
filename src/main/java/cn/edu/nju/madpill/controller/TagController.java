package cn.edu.nju.madpill.controller;

import cn.edu.nju.madpill.dto.Result;
import cn.edu.nju.madpill.service.DrugService;
import cn.edu.nju.madpill.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping(path = "/user")
    public Result getTagsOfUser(@RequestParam("userId") Long userId) {
        return Result.builder()
                .data(tagService.getTagsOfUser(userId))
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping(path = "/remove")
    public Result deleteTag(@RequestParam("userId") Long userId, @RequestParam("tagId") Long tagId) {
        return Result.builder()
                .data(tagService.deleteTag(userId, tagId))
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping(path = "/add")
    public Result getTagsOfDrug(@RequestParam("userId") Long userId, @RequestParam("name") String name) {
        return Result.builder()
                .data(tagService.addNewTag(userId, name))
                .code(HttpStatus.OK.value())
                .build();
    }


    @GetMapping(params = "/updateDrugTag")
    public Result updateTagsOfDrug(@RequestParam("drugId") Long drugId, @RequestParam("drugId") Long[] tagIds) {
        return Result.builder()
                .data(tagService.updateTagsOfDrug(drugId,tagIds))
                .code(HttpStatus.OK.value())
                .build();
    }
}
