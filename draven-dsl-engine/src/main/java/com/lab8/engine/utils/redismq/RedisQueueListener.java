package com.lab8.engine.utils.redismq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * 消息队列监听
 *
 * @author xy
 * @since 2022-03-21 16:10:49
 */
@Slf4j
public class RedisQueueListener implements Runnable {
    private RedisTemplate<String, Object> redisTemplate;
    private String queue;
    private MsgConsumer consumer;

    public RedisQueueListener(RedisTemplate<String, Object> redisTemplate, String queue, MsgConsumer consumer) {
        this.redisTemplate = redisTemplate;
        this.queue = queue;
        this.consumer = consumer;
    }

    @Override
    public void run() {
        log.info("RedisQueueListener start...queue:{}", queue);
      /*  while (true) {
            try {
                Object msg = redisTemplate.opsForList().rightPop(queue, 3, TimeUnit.SECONDS);
                if (msg != null) {
                    try {
                        consumer.onMessage(msg);
                    } catch (Exception e) {
                        consumer.onError(msg, e);
                    }
                }
            } catch (Exception e) {
                log.error("异常 Queue:{}", queue, e);
            }
        }*/
    }
}
