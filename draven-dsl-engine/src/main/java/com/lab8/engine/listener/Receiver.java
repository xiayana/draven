package com.lab8.engine.listener;

/**
 * @Author : xy
 * @CreateTime : 2022-03-23 11:58:35
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
import com.lab8.engine.utils.RedisUtil;
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
    @Autowired
    private RedisUtil redisUtil;
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
                    boolean b = redisUtil.setNxEx(personEvent.getUuid(),personEvent.getUuid(),50);
                    if(b){
                        ErrorLog errorLog = new ErrorLog();
                        errorLog.setNumberUuid(personEvent.getUuid());
                        errorLog.setMsg(resultData.getMsg());
                        errorLogService.insert(errorLog);
                        log.info("errorLog strategyInsert success !!");
                    }else{
                        log.info("errorLog strategyInsert setNxExFail !!");
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