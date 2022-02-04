package com.lab8.engine.controller;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author jiangzihao@kanzhun.com
 */
@RestController
public class testController {

    @Resource(name = "epAdministrator")
    private EPAdministrator epAdministrator;

    @Autowired
    EPServiceProvider epServiceProvider;

    @RequestMapping(value = "test")
    public String test(String name, String number) {
        EPRuntime epRuntime = epServiceProvider.getEPRuntime();
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("number", number);
        epRuntime.sendEvent(map,"mobillocaltion");
        return "success";
    }

}
