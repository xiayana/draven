package com.lab8.engine.service.impl;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.lab8.engine.config.EsperConfig;
import com.lab8.engine.constants.CommonConstants;
import com.lab8.engine.entity.ResultData;
import com.lab8.engine.listener.EsperListener;
import com.lab8.engine.service.EsperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xy
 */
@Slf4j
@Service
public class EsperServiceimpl implements EsperService {


    @Resource(name = "epAdministrator")
    private EPAdministrator epAdministrator;

    @Override
    public ResultData addEsperListener(int id, String esperSql) {
        ResultData resultData = new ResultData();
        if (null != EsperConfig.STATEMENT_MAP.get(id)) {
            log.info("esperSql already exist :{}-{}", id, esperSql);
            resultData.setCode(CommonConstants.NUMBER_ONE);
            resultData.setMsg("esperSql already exist");
            return resultData;
        }
        try {
            EPStatement epStatement = epAdministrator.createEPL(esperSql);
            epStatement.addListener(new EsperListener());
            epStatement.start();
            EsperConfig.STATEMENT_MAP.put(id, epStatement);
        }catch (Exception e){
            log.error("esperSql Exception-{}", e.getMessage());
            resultData.setCode(CommonConstants.NUMBER_ONE);
            resultData.setMsg(e.getMessage());
            return resultData;
        }
        return resultData;
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
