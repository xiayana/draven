package com.lab8.engine.entity;

import com.lab8.engine.enume.SendRedisMessageTypeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 发送到redis消息基础类
 * @author xy
 * @since 2022-03-21 16:18:52
 */
@Data
public class BaseRedisMessage<T> implements Serializable {

    //消息id
    private String msgId;

    //消息发送时间
    private long msgSendTime;

    //消息类型
    private SendRedisMessageTypeEnum sendRedisMessageType;

    //实体json
    private String msgDataJson;

    //消息实体
    private T msgData;
}
