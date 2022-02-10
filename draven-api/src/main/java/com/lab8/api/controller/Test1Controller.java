package com.lab8.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test1")
public class Test1Controller {
    @GetMapping("/test")
    public String test(String t) {
       System.out.println();
        return "success";
    }
}
