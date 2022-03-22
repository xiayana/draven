package com.lab8.engine.enume;

/**
 * 发送都redis消息类型
 *
 * @author xy
 * @since 2022-03-21 16:34:28
 */
public enum SendRedisMessageTypeEnum {
    ESPER_ADD("esper_add", "添加策略"),
    Esper_DELETE("esper_delete", "移除策略");
    private final String messageType;
    private final String memo;

    private static final SendRedisMessageTypeEnum[] messageTypes = SendRedisMessageTypeEnum.values();

    public String getMessageType() {
        return messageType;
    }

    public String getMemo() {
        return memo;
    }

    SendRedisMessageTypeEnum(String messageType, final String memo) {
        this.messageType = messageType;
        this.memo = memo;
    }

    public static SendRedisMessageTypeEnum getValueOf(String messageType) {
        for (SendRedisMessageTypeEnum bean : messageTypes) {
            if (bean.messageType.equalsIgnoreCase(messageType)) {
                return bean;
            }
        }
        return null;
    }
}
