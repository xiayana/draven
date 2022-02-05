package com.lab8.engine.listener;

import com.alibaba.fastjson.JSONObject;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

/**
 * kafka监听
 *
 * @author shangzz
 */
@Slf4j
@Component
public class RawDataListener {
    @Autowired
  private EPServiceProvider epServiceProvider;
    /**
     * 实时获取kafka数据(生产一条，监听生产topic自动消费一条)
     */
    @KafkaListener(containerFactory = "kafkaListenerContainerFactory", topics = {"demo"})
    public void bizTagListener(List<ConsumerRecord<?, ?>> records, Acknowledgment ack) {
        long offset = 0;
        int partition = 0;
        try {
            EPRuntime epRuntime = epServiceProvider.getEPRuntime();
            for (ConsumerRecord<?, ?> record : records) {
                String s = record.value().toString();
                offset = record.offset();
                partition = record.partition();
                try {
                    Map<String,String> json = JSONObject.parseObject(s, Map.class);
                    epRuntime.sendEvent(json,"mobillocaltion");
                    log.info(json.toString());
                } catch (Exception e) {
                    log.error("deal error, message: " + s, e);
                }
            }
        } catch (Exception e) {
            log.error("Kafka监听异常" + e.getMessage(), e);
            log.error("Kafka异常数据, partition:" + partition + ",offset：" + offset + ", records: " + records, e);
        } finally {
            ack.acknowledge(); // 手动提交偏移量
//            if (records.size() > 30) {
//                log.info("ack partition:" + partition + ",offset:" + offset + ", size: " + records.size());
//            }
        }
    }


}