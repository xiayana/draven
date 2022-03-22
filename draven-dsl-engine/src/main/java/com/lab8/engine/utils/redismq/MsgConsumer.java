package com.lab8.engine.utils.redismq;

/**
 * 消费者接口
 * @author xy
 * @since 2022-03-21 16:10:21
 */
public interface MsgConsumer {

    void onMessage(Object message);

    void onError(Object message, Exception e);
}
