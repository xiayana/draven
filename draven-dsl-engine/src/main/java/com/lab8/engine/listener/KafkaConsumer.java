package com.lab8.engine.listener;

import com.alibaba.fastjson.JSONObject;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Slf4j
@Component
public class KafkaConsumer {
    @Autowired
    private EPServiceProvider epServiceProvider;
    //下面的主题是一个数组，可以同时订阅多主题，只需按数组格式即可，也就是用“，”隔开
    @KafkaListener(containerFactory = "kafkaListenerContainerFactory", topics = {"${kafka.consumer.topic}"})
    public void receive(String records) {
        log.info("消费得到的消息---: " + records);
        try {
            EPRuntime epRuntime = epServiceProvider.getEPRuntime();
            Map<String,String> json = JSONObject.parseObject(records, Map.class);
            json.put("allowed",json.remove("flag"));
            json.put("host",json.get("ip"));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dateStart = format.parse(json.get("time"));
            json.put("timestamp",dateStart.getTime() / 1000+"000");
            epRuntime.sendEvent(json,"mobillocaltion");
        } catch (Exception e) {
            log.error("Kafka监听异常" + e.getMessage(), e);
        }
    }
}
