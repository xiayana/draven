package com.lab8.engine.listener;

/**
 * @Author : JCccc
 * @CreateTime : 2019-1-2
 * @Description :
 * @Point: Keep a good mood
 **/

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lab8.engine.constants.CommonConstants;
import com.lab8.engine.entity.ErrorLog;
import com.lab8.engine.entity.PersonEvent;
import com.lab8.engine.entity.ResultData;
import com.lab8.engine.service.ErrorLogService;
import com.lab8.engine.service.EsperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * redis消息处理器
 */
@Slf4j
@Component
public class Receiver {
    @Autowired
    private EsperService esperService;
    @Autowired
    private ErrorLogService errorLogService;
    /**
     * 接收到消息的方法，message就是指从主题获取的消息，主题配置在RedisMessageListener配置类做配置
     * @param message
     */
    public void receiveMessage(String message) {
        PersonEvent personEvent = JSON.parseObject(message, new TypeReference<PersonEvent>() {});
        if(personEvent.getSql().equals("")){
            esperService.removeEsperListener(personEvent.getId());
        }else{
            ResultData resultData = new ResultData();
            try {
                resultData  = esperService.addEsperListener(personEvent.getId(), personEvent.getSql());
                if(resultData.getCode() != CommonConstants.NUMBER_ZERO){
                    ErrorLog errorLog = new ErrorLog();
                    errorLog.setNumberUuid(personEvent.getUuid());
                    List<ErrorLog> list = errorLogService.queryAll(errorLog);
                    if(list.size() == CommonConstants.NUMBER_ZERO){
                        errorLog.setMsg(resultData.getMsg());
                        errorLogService.insert(errorLog);
                        log.info("为空，开始写入-");
                    }
                }
                log.info("processEsperAddMsg:[{}]",JSON.toJSONString(personEvent));
            }catch (Exception e){
                log.error("processEsperAddMsg error,msg:[{}]",JSON.toJSONString(personEvent),e);
            }
        }
        log.info("Message processing completed!!");
       // log.info("Received <" + message + ">");
    }
}