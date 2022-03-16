package com.lab8.engine.enume;

/**
 * 该类表示业务错误
 *
 * @author xy
 * @date 2022-03-14 17:01:52
 */
public enum ResponseCodeEnum {

    SUCCESS(0,"成功"),

    /**
     * 内部错误
     */
    ERROR_INTERNAL(10010, "internal server error"),

    /**
     * 数据格式错误
     */
    ERROR_DATA_FORMAT(10011, "error data format"),

    /**
     * 数据取值不合法
     */
    ERROR_DATA_RESTRICTION(10012, "error data value"),

    /**
     * 入参错误
     */
    ERROR_DATA_INCOMPLETE(10013, "error data format"),
    /**
     * 权限不足
     */
    ERROR_INSUFFICIENT_PERMISSIONS(10014, "error insufficient permissions"),

    /**
     * 操作太频繁
     */
    ERROR_OPERATION_TOO_FREQUENTLY(10015, "operation too frequently"),
    /**
     * 入参有空
     */
    ERROR_DATA_NULL(10016, "error data null");


    private static final ResponseCodeEnum[] errors = ResponseCodeEnum.values();

    private final int code;
    private final String message;

    public String getMessage() {
        return message;
    }

    ResponseCodeEnum(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public static ResponseCodeEnum valueOf(int code) {
        for (ResponseCodeEnum error : errors) {
            if (error.code == code) {
                return error;
            }
        }
        return ResponseCodeEnum.ERROR_INTERNAL;
    }

}
