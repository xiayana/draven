package com.lab8.engine.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lab8.engine.constants.CommonConstants;
import com.lab8.engine.entity.BaseRedisMessage;
import com.lab8.engine.entity.ErrorLog;
import com.lab8.engine.entity.PersonEvent;
import com.lab8.engine.entity.ResultData;
import com.lab8.engine.service.ErrorLogService;
import com.lab8.engine.service.EsperService;
import com.lab8.engine.utils.redismq.MsgConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 场景提取redis消息消费
 */
@Slf4j
@Component("sceneExtraceConsumer")
public class SceneExtraceConsumer implements MsgConsumer {

    @Autowired
    private EsperService esperService;
    @Autowired
    private ErrorLogService errorLogService;
    /**
     * 处理消息
     * @param message
     */
    @Override
    public void onMessage(Object message) {
        if(null==message){
            return;
        }
        log.info("accept  message:[{}]",message.toString());
        BaseRedisMessage baseRedisMessage=JSON.parseObject(message.toString(),BaseRedisMessage.class);
        processMsg(baseRedisMessage);
    }

    /**
     * 处理消息
     * @param baseRedisMessage
     * @return
     */
    private ResultData processMsg(BaseRedisMessage baseRedisMessage){
        ResultData resultData = new ResultData();
        if(baseRedisMessage.getSendRedisMessageType().getMessageType().equals("esper_add")){
            return processEsperAddMsg(baseRedisMessage);
        }
        if(baseRedisMessage.getSendRedisMessageType().getMessageType().equals("esper_delete")){
            return processEsperDeleteMsg(baseRedisMessage);
        }
        return resultData;
    }

    /**
     * 添加策略
     * @param baseRedisMessage
     * @return
     */
    private  ResultData processEsperAddMsg(BaseRedisMessage baseRedisMessage){
        log.info("processSceneExtraceMsg  message:[{}]",JSON.toJSONString(baseRedisMessage));
        PersonEvent personEvent = JSON.parseObject(baseRedisMessage.getMsgDataJson(), new TypeReference<PersonEvent>() {});
        ResultData resultData = new ResultData();
        try {
             resultData  = esperService.addEsperListener(personEvent.getId(), personEvent.getSql());
             if(resultData.getCode() != CommonConstants.NUMBER_ZERO){
                 ErrorLog errorLog = new ErrorLog();
                 errorLog.setNumberUuid(baseRedisMessage.getMsgId());
                 List<ErrorLog> list = errorLogService.queryAll(errorLog);
                 if(list.size() == CommonConstants.NUMBER_ZERO){
                     errorLog.setMsg(resultData.getMsg());
                     errorLogService.insert(errorLog);
                     log.info("为空，开始写入-");
                 }
             }
            log.info("processEsperAddMsg:[{}]",JSON.toJSONString(baseRedisMessage));
        }catch (Exception e){
            log.error("processEsperAddMsg error,msg:[{}]",JSON.toJSONString(baseRedisMessage),e);
            return resultData;
        }
        return resultData;
    }

    /**
     * 移除策略
     * @param
     * @return
     */
    private  ResultData processEsperDeleteMsg(BaseRedisMessage baseRedisMessage){
        log.info("processSceneExtraceMsg  message:[{}]",JSON.toJSONString(baseRedisMessage));
        PersonEvent personEvent = JSON.parseObject(baseRedisMessage.getMsgDataJson(), new TypeReference<PersonEvent>() {});
        ResultData resultData = new ResultData();

        try {
            esperService.removeEsperListener(personEvent.getId());
            log.info("processEsperDeleteMsg:[{}]",JSON.toJSONString(baseRedisMessage));
        }catch (Exception e){
            log.error("processEsperDeleteMsg error,msg:[{}]",JSON.toJSONString(baseRedisMessage),e);
            return resultData;
        }
        return resultData;
    }



    @Override
    public void onError(Object message, Exception e) {
        log.error("EsperConsumer error:", e);
    }


}
