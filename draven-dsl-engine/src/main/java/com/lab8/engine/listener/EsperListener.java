package com.lab8.engine.listener;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import com.lab8.engine.config.EsperConfig;
import com.lab8.engine.entity.Test1;
import com.lab8.engine.service.EsperService;
import com.lab8.engine.service.Test1Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Component
public class EsperListener implements UpdateListener {

    @Autowired
    private Test1Service test1Service;

    @Autowired
    private EsperService esperService;

    @PostConstruct
    public void statr1() {
        List<Test1> rules = test1Service.queryAll(new Test1());
        rules.forEach(rule ->
                esperService.addEsperListener(rule.getId(), rule.getEsperSql()));
    }

    @Override
    public void update(EventBean[] eventBeans, EventBean[] eventBeans1) {
        if (eventBeans != null) {
            System.out.println(String.format
                    ("事件1 匹配成功，匹配到的位置为：%s, 要发送的手机号是：%s,时间：%s,原始内容：%s",
                            eventBeans[0].get("cmd"),
                            eventBeans[0].get("dst"),
                            System.currentTimeMillis(),
                            eventBeans[0].getUnderlying()
                    ));
        }
    }
}
