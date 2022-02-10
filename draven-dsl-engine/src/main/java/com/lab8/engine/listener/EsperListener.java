package com.lab8.engine.listener;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import com.lab8.engine.entity.AlertdetailHadoop;
import com.lab8.engine.entity.DravenMetadata;
import com.lab8.engine.service.AlertdetailHadoopService;
import com.lab8.engine.service.EsperService;
import com.lab8.engine.service.StrategyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Component
public class EsperListener implements UpdateListener {
    private static EsperListener esperListener;  // 静态初使化当前类

    @Autowired
    private StrategyService test1Service;
    @Autowired
    private EsperService esperService;
    @Autowired
    private AlertdetailHadoopService alertdetailHadoopService;
    @PostConstruct
    public void statr1() {
        List<DravenMetadata> rules = test1Service.queryAll(new DravenMetadata());
        rules.forEach(rule ->
                esperService.addEsperListener(rule.getId(), rule.getEsperSql()));
    }
    @PostConstruct
    public void init() {
        esperListener = this;
    }
    @Override
    public void update(EventBean[] eventBeans, EventBean[] eventBeans1) {
        if (eventBeans != null) {
            AlertdetailHadoop alertdetailHadoop = AlertdetailHadoop.builder()
                    .site("sanbox")
                    .timestamp(eventBeans[0].get("time").toString())
                    .hostname(eventBeans[0].get("ip").toString())
                    .alertcontext(eventBeans[0].getUnderlying().toString())
                    .alertsource(eventBeans[0].get("user").toString())
                    .alertexecutorid("hdfsAuditLogAlertExecutor")
                    .policyid(eventBeans[0].get("id").toString())
                    .build();
            /*esperListener.alertdetailHadoopService.insert(alertdetailHadoop);*/
            String eventType =   eventBeans[0].getClass().getSimpleName();
            System.out.println(String.format
                    ("匹配成功，匹配到的cmd为：%s, dst为：%s,src为：%s,user为：%s,时间：%s,原始内容：%s,备用：%s",
                            eventBeans[0].get("cmd"),
                            eventBeans[0].get("dst"),
                            eventBeans[0].get("src"),
                            eventBeans[0].get("user"),
                            System.currentTimeMillis(),
                            eventBeans[0].getUnderlying(),
                            eventBeans[0].get("id")
                    ));
        }
    }
}
