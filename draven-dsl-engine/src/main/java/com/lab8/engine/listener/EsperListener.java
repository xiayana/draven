package com.lab8.engine.listener;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import com.lab8.engine.config.EsperConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Slf4j
@Component
public class EsperListener implements UpdateListener {

    @Resource(name = "epAdministrator")
    private EPAdministrator epAdministrator;

    @PostConstruct
    public String statr1() {
        if (null != EsperConfig.STATEMENT_MAP.get("1")) {
            return "esper配置已经启动！";
        }
        String epl = "select name,number from mobillocaltion where name in ('1','2','3')";
        EPStatement epStatement = epAdministrator.createEPL(epl);
        epStatement.addListener(new EsperListener());
        epStatement.start();
        EsperConfig.STATEMENT_MAP.put("1", epStatement);
        System.out.println("事件1启动成功");
        return "启动事件1成功";
    }

    @Override
    public void update(EventBean[] eventBeans, EventBean[] eventBeans1) {
        if (eventBeans != null) {
            System.out.println(String.format
                    ("事件1 匹配成功，匹配到的位置为：%s, 要发送的手机号是：%s,原始内容：%s",
                            eventBeans[0].get("name"),
                            eventBeans[0].get("number"),
                            eventBeans[0].getUnderlying()
                    ));
        }
    }
}
