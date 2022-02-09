package com.lab8.engine.service.impl;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.lab8.engine.config.EsperConfig;
import com.lab8.engine.listener.EsperListener;
import com.lab8.engine.service.EsperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jiangzihao@kanzhun.com
 */
@Slf4j
@Service
public class EsperServiceimpl implements EsperService {


    @Resource(name = "epAdministrator")
    private EPAdministrator epAdministrator;

    @Override
    public void addEsperListener(int id, String esperSql) {
        if (null != EsperConfig.STATEMENT_MAP.get(id)) {
            log.info("esperSql already exits :{}-{}", id, esperSql);
            return;
        }

        EPStatement epStatement = epAdministrator.createEPL(esperSql,"test");
        System.out.println(epStatement.getName());
        epStatement.addListener(new EsperListener());
        epStatement.start();

        EsperConfig.STATEMENT_MAP.put(id, epStatement);
    }

    @Override
    public void removeEsperListener(int id) {
        EPStatement epStatement = EsperConfig.STATEMENT_MAP.get(id);
        if (null != epStatement) {
            epStatement.stop();
            EsperConfig.STATEMENT_MAP.remove(id);
        }
    }
}
