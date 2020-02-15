package cn.edu.nju.madpill.controller;

import cn.edu.nju.madpill.dto.Result;
import cn.edu.nju.madpill.dto.TagDTO;
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

    @DeleteMapping(path = "/{tagId}")
    public Result deleteTag(@PathVariable("tagId") Long tagId) {
        tagService.deleteTag(tagId);
        return Result.builder()
                .code(HttpStatus.OK.value())
                .build();
    }

    @PutMapping(path = "")
    public Result addNewTag(@RequestBody TagDTO tagDTO) {
        return Result.builder()
                .data(tagService.addNewTag(tagDTO))
                .code(HttpStatus.OK.value())
                .build();
    }


//    @PostMapping(params = "/updateDrugTag")
//    public Result updateTagsOfDrug(@RequestParam("drugId") Long drugId, @RequestParam("drugId") Long[] tagIds) {
//        return Result.builder()
//                .data(tagService.updateTagsOfDrug(drugId,tagIds))
//                .code(HttpStatus.OK.value())
//                .build();
//    }
}
