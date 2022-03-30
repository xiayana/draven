package com.lab8.engine.controller;

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

import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    static  AlertdetailHadoop alertdetailHadoop=new AlertdetailHadoop();

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
    @PostMapping("/rest/entities")
    public void entities(String  serviceName,@RequestBody String body) {
        log.info("接到请求--");
        List<String> list = FindJsonUtil.format(body);
        if(list.size() > CommonConstants.NUMBER_ZERO){
            try {
                JSONArray arrayJson=JSON.parseArray(list.get(CommonConstants.NUMBER_ZERO));
                JSONObject s1 = (JSONObject) arrayJson.get(CommonConstants.NUMBER_ZERO);
                alertdetailHadoop.setPolicyid((String) ((Map<?, ?>) s1.get("tags")).get("policyId"));
                alertdetailHadoop.setSite((String) ((Map<?, ?>) s1.get("tags")).get("site"));
                alertdetailHadoop.setApplication((String) ((Map<?, ?>) s1.get("tags")).get("application"));
                alertdetailHadoop.setAlertexecutorid((String) ((Map<?, ?>) s1.get("tags")).get("alertExecutorId"));
                alertdetailHadoop.setAlertsource((String) ((Map<?, ?>) s1.get("tags")).get("alertsource"));
                alertdetailHadoop.setSourcestreams((String) ((Map<?, ?>) s1.get("tags")).get("sourcestreams"));
                alertdetailHadoop.setAlertcontext(String.valueOf(s1.get("alertContext")));
                alertdetailHadoop.setTimestamp(String.valueOf(s1.get("timestamp")));
                alertdetailHadoopService.insert(alertdetailHadoop);
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
