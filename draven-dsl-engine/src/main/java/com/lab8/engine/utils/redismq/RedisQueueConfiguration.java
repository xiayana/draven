package com.lab8.engine.utils.redismq;

import lombok.Builder;
import lombok.Data;

/**
 * @author xy
 * @since 2022-03-21 16:10:40
 */
@Data
@Builder
public class RedisQueueConfiguration {
    /**
     * 队列名称
     */
    private String queue;
    /**
     * 消费者
     */
    private MsgConsumer consumer;
}
