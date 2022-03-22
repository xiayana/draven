package com.lab8.engine.utils.redismq;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * @author xy
 * @since 2022-03-21 16:10:33
 */
@Slf4j
public class RedisMqConsumerContainer {
    private RedisTemplate<String, Object> redisTemplate;
    private Executor testExecutor;

    public RedisMqConsumerContainer(RedisTemplate<String, Object> redisTemplate, Executor testExecutor) {
        this.redisTemplate = redisTemplate;
        this.testExecutor = testExecutor;
    }

    private Map<String, RedisQueueConfiguration> consumerMap = new HashMap<>();

    public void addConsumer(RedisQueueConfiguration configuration) {
        if (consumerMap.containsKey(configuration.getQueue())) {
            log.warn("Key:{} 已经存在，", configuration.getQueue());
        }
        if (configuration.getConsumer() == null) {
            log.warn("Key:{} consumer 为空，无法对其进行配置", configuration.getQueue());
        }
        consumerMap.put(configuration.getQueue(), configuration);
    }

    public void init() {
        consumerMap = Collections.unmodifiableMap(consumerMap);
        log.info("init redis quene consumerMap:[{}]", JSON.toJSONString(consumerMap));
        consumerMap.forEach((k, v) -> testExecutor.execute(new RedisQueueListener(redisTemplate, v.getQueue(), v.getConsumer())));
    }
}
