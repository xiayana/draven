package com.lab8.engine.controller;

import com.lab8.engine.service.EsperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("x")
public class EsperListenerController {
    @Autowired
    private EsperService esperService;
    @GetMapping("/addListener")
    public String addListener(Integer id ,String sql) {
        esperService.addEsperListener(id,sql);
        return "success";
    }
    @GetMapping("/removeListener")
    public String removeListener(Integer id) {
        esperService.removeEsperListener(id);
        return "success";
    }

}
