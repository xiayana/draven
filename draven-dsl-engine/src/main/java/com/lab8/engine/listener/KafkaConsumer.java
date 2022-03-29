package com.lab8.engine.listener;

import com.alibaba.fastjson.JSONObject;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.lab8.engine.utils.DateFormateUtil;
import com.lab8.engine.utils.SoftHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {
    @Autowired
    private EPServiceProvider epServiceProvider;
    //下面的主题是一个数组，可以同时订阅多主题，只需按数组格式即可，也就是用“，”隔开
    @KafkaListener(containerFactory = "kafkaListenerContainerFactory", topics = {"${kafka.consumer.topic}"})
    public void receive(String records) {
       // log.info("消费得到的消息---: " + records);
        try {
            EPRuntime epRuntime = epServiceProvider.getEPRuntime();
          //  Map<String,String> json=new ConcurrentHashMap<String,String>();
            SoftHashMap<String,String> map = JSONObject.parseObject(records,SoftHashMap.class);
            map.put("allowed",map.remove("flag"));
            map.put("host",map.get("ip"));
            map.put("timestamp",DateFormateUtil.formate( map.get("time")).getTime()/ 1000+"000");
            epRuntime.sendEvent(map,"mobillocaltion");

        } catch (Exception e) {
            log.error("Kafka监听异常" + e.getMessage(), e);
        }
    }
}
