package cn.edu.nju.madpill.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aneureka
 * @createdAt 2020-01-17 20:09
 * @description
 **/
@RestController
public class PingController {

    @GetMapping("/")
    @ResponseBody
    public String ping() {
        return "Hi, this is mad pill!";
    }

}
