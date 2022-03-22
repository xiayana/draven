package com.lab8.engine.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lab8.engine.constants.CommonConstants;
import com.lab8.engine.entity.BaseRedisMessage;
import com.lab8.engine.entity.ErrorLog;
import com.lab8.engine.entity.PersonEvent;
import com.lab8.engine.entity.ResultData;
import com.lab8.engine.enume.ResponseCodeEnum;
import com.lab8.engine.enume.SendRedisMessageTypeEnum;
import com.lab8.engine.service.ErrorLogService;
import com.lab8.engine.service.EsperService;
import com.lab8.engine.utils.redismq.RedisQueueSender;
import com.mysql.jdbc.log.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/esperListener")
public class EsperListenerController {
    @Autowired
    private RedisQueueSender redisQueueSender;
    @Autowired
    private ErrorLogService errorLogService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @PostMapping("/addListener")
    public ResultData addListener(@RequestBody PersonEvent personEvent) {
        ResultData resultData = new ResultData();
        if (personEvent.getId() == null || StringUtils.isEmpty(personEvent.getSql())) {
            resultData.setCode(ResponseCodeEnum.ERROR_DATA_NULL.getCode());
            resultData.setMsg(ResponseCodeEnum.ERROR_DATA_NULL.getMessage());
            return resultData;
        }
        personEvent.setUuid(UUID.randomUUID().toString());
        stringRedisTemplate.convertAndSend("chat", JSONObject.toJSONString(personEvent));
      /*  boolean result = redisQueueSender.sendMsg(CommonConstants.ESPER_QUEUENAME, message);
        if ( result == false) {
            resultData.setMsg(ResponseCodeEnum.ERROR_DATA_FORMAT.getMessage());
            resultData.setCode(ResponseCodeEnum.ERROR_DATA_FORMAT.getCode());
            return resultData;
        }*/
        System.out.println("主线程休眠1秒");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ErrorLog errorLog = new ErrorLog();
        errorLog.setNumberUuid(personEvent.getUuid());
        List<ErrorLog> list = errorLogService.queryAll(errorLog);
        if(list.size() > CommonConstants.NUMBER_ZERO){
            resultData.setCode(ResponseCodeEnum.ERROR_DATA_FORMAT.getCode());
            resultData.setMsg(list.get(0).getMsg());
        }

        log.info(">>>>>> successSQL:ID= {}, SQL= {}", personEvent.getId(), personEvent.getSql());
        return resultData;
    }

    @PostMapping("/removeListener")
    public ResultData removeListener(@RequestBody PersonEvent personEvent) {
        ResultData resultData = new ResultData();
        if (personEvent.getId() == null) {
            resultData.setCode(ResponseCodeEnum.ERROR_DATA_NULL.getCode());
            resultData.setMsg(ResponseCodeEnum.ERROR_DATA_NULL.getMessage());
            return resultData;
        }
        BaseRedisMessage<PersonEvent> messageObj = new BaseRedisMessage<PersonEvent>();
        messageObj.setMsgId(UUID.randomUUID().toString());
        messageObj.setMsgSendTime(System.currentTimeMillis());
        messageObj.setSendRedisMessageType(SendRedisMessageTypeEnum.Esper_DELETE);
        messageObj.setMsgDataJson(JSONObject.toJSONString(personEvent));
        String message = JSON.toJSONString(messageObj);
        boolean result = redisQueueSender.sendMsg(CommonConstants.ESPER_QUEUENAME, message);
        if ( result == false) {
            resultData.setMsg(ResponseCodeEnum.ERROR_DATA_FORMAT.getMessage());
            resultData.setCode(ResponseCodeEnum.ERROR_DATA_FORMAT.getCode());
            return resultData;
        }
        return resultData;
    }
    @GetMapping("/addMetaData")
    public ResultData addMetaData(String Name) {
        ResultData resultData = new ResultData();
       /* if (id == null) {
            resultData.setCode(ResponseCodeEnum.ERROR_DATA_NULL.getCode());
            resultData.setMsg(ResponseCodeEnum.ERROR_DATA_NULL.getMessage());
            return resultData;
        }
        esperService.removeEsperListener(Integer.valueOf(id));*/
        return resultData;
    }
}
