package com.lab8.engine.controller;

import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lab8.engine.constants.CommonConstants;
import com.lab8.engine.entity.AlertdetailHadoop;
import com.lab8.engine.enume.ResponseCodeEnum;
import com.lab8.engine.enume.SendRedisMessageTypeEnum;
import com.lab8.engine.service.AlertdetailHadoopService;
import com.lab8.engine.utils.FindJsonUtil;
import com.lab8.engine.utils.redismq.RedisQueueSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Ueba告警日志(AlertdetailHadoop)表控制层
 *
 * @author xy
 * @since 2022-02-07 15:56:09
 */
@Slf4j
@RestController
@RequestMapping("eagle-service")
public class AlertdetailHadoopController {
    /**
     * 服务对象
     */
    @Autowired
    private AlertdetailHadoopService alertdetailHadoopService;
    @Autowired
    private RedisQueueSender redisQueueSender;
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public AlertdetailHadoop selectOne(Long id) {
        return this.alertdetailHadoopService.queryById(id);
    }

    private static boolean isBase64(String str) {
        String base64Pattern = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
        return Pattern.matches(base64Pattern, str);
    }

    @PostMapping("/rest/entities")
    public void entities(String  serviceName,@RequestBody String body) {
        log.info("Received the request--");
        List<String> list = FindJsonUtil.format(body);
        if(list.size() > CommonConstants.NUMBER_ZERO){
            AlertdetailHadoop alertdetailHadoop=new AlertdetailHadoop();
            try {
                JSONArray arrayJson=JSON.parseArray(list.get(CommonConstants.NUMBER_ZERO));
                JSONObject s1 = (JSONObject) arrayJson.get(CommonConstants.NUMBER_ZERO);
                JSONObject s2 = (JSONObject) s1.get("alertContext");
                String str = (String) ((Map<?, ?>) s2.get("properties")).get("alertMessage");
                if(((Map<?, ?>)s2.get("properties")).get("alertEvent") != null ){
                    if(!((Map<?, ?>)s2.get("properties")).get("alertEvent").equals("None")){
                        if(isBase64(((Map<?, ?>)s2.get("properties")).get("alertEvent").toString())){
                            //是base64
                            alertdetailHadoop.setMarkValues(Base64.decodeStr((String) ((Map<?, ?>)s2.get("properties")).get("alertEvent")).intern());
                        }else{
                            alertdetailHadoop.setMarkValues(((Map<?, ?>)s2.get("properties")).get("alertEvent").toString().intern());
                        }
                    }
                }
                if (str.contains(CommonConstants.IDENTIFY_SERVICE)){
                    alertdetailHadoop.setIsMark(CommonConstants.NUMBER_ONE);
                    ((Map<String, String>) s2.get("properties")).put("alertMessage",str.replaceAll("\\[service\\]",""));
                }else{
                    alertdetailHadoop.setIsMark(CommonConstants.NUMBER_ZERO);
                }
                alertdetailHadoop.setPolicyid((String) ((Map<?, ?>) s1.get("tags")).get("policyId"));
                alertdetailHadoop.setAlertUser((((Map<?, ?>) s2.get("properties")).get("user")).toString().intern());
                alertdetailHadoop.setAlertMessage((((Map<?, ?>) s2.get("properties")).get("alertMessage")).toString().intern());
                alertdetailHadoop.setSite((String) ((Map<?, ?>) s1.get("tags")).get("site"));
                alertdetailHadoop.setHostname((String) ((Map<?, ?>)s2.get("properties")).get("host"));
                alertdetailHadoop.setApplication((String) ((Map<?, ?>) s1.get("tags")).get("application"));
                alertdetailHadoop.setAlertexecutorid((String) ((Map<?, ?>) s1.get("tags")).get("alertExecutorId"));
                alertdetailHadoop.setAlertsource((String) ((Map<?, ?>) s1.get("tags")).get("alertSource"));
                alertdetailHadoop.setSourcestreams((String) ((Map<?, ?>) s1.get("tags")).get("sourceStreams"));
                alertdetailHadoop.setAlertcontext(String.valueOf(s1.get("alertContext")));
                alertdetailHadoop.setTimestamp(String.valueOf(s1.get("timestamp")));
                alertdetailHadoop.setAlertType(CommonConstants.NUMBER_TWO);
                alertdetailHadoopService.insert(alertdetailHadoop);
                log.info("userProFile insert success!!");
                alertdetailHadoop = null;
            }catch (Exception e){
                log.error("body json error" + e.getMessage(), e);
            }
        }
    }

    /**
     * 通过主键查询单条数据
     *
     * @param alertdetailHadoop 主键
     * @return 单条数据
     */
    @PostMapping("/insert")
    public AlertdetailHadoop insert(@RequestBody AlertdetailHadoop alertdetailHadoop) {
        return this.alertdetailHadoopService.insert(alertdetailHadoop);
    }
}
