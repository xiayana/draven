package com.lab8.draven.engine.listener;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * kafka监听
 * @author shangzz
 *
 */
@Slf4j
@Component
public class RawDataListener {

    /**
     * 实时获取kafka数据(生产一条，监听生产topic自动消费一条)
     * @param record
     * @throws IOException
     */
   // @KafkaListener(topics = {"${kafka.consumer.topic}"})
    public void listen(ConsumerRecord<?, ?> record) throws IOException {
        String value = (String) record.value();
        JSONObject jsonObject = JSONObject.parseObject(value);
            System.out.println(jsonObject.toJSONString());
    }

}