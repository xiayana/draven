package com.lab8.engine.controller;

import com.lab8.engine.service.EsperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/esperListener")
public class EsperListenerController {
    @Autowired
    private EsperService esperService;
    @GetMapping("/addListener")
    public String addListener(Integer id ,String sql) {
        esperService.addEsperListener(id,sql);
        return "success";
    }
    @GetMapping("/removeListener")
    public String removeListener(String id) {
        esperService.removeEsperListener(Integer.valueOf(id));
        return "success";
    }

}
