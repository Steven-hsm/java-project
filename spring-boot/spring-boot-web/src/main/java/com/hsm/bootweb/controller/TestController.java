package com.hsm.bootweb.controller;

import com.hsm.bootweb.entity.UserVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/8/4 14:33
 */
@RestController
@RequestMapping("/")
public class TestController {

    @RequestMapping("")
    public String index(@Valid @RequestBody UserVO user){
        return "";
    }

    @RequestMapping("/test")
    public String index2(@Validated @RequestBody UserVO user){
        return "";
    }
}
