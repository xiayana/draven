package com.lab8.engine.utils.redismq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 消息生产者
 * @author xy
 * @since 2022-03-21 16:10:57
 */
@Slf4j
@Component
public class RedisQueueSender {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 往redis发送消息
     * @param queueName 队列名称
     * @param msg 消息json
     * @return
     */
    public boolean sendMsg(String queueName, String msg) {
        try {
            redisTemplate.opsForList().leftPush(queueName, msg);
            log.info("send message to redis succes,queueName:[{}],msg:[{}]",queueName,msg);
        }catch (Exception e){
            log.error("sendMsg to redis error,queueName:[{}],msg:[{}]",queueName,msg,e);
            return false;
        }
        return true;
    }

}
