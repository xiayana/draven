package com.deavenapiweb.exception;

import com.deavenapiweb.enume.ResponseCodeEnum;
import lombok.Data;

/**
 * 脚本执行异常
 * @author fankai
 * @since 2021-09-30
 */
@Data
public class ScriptExecException extends RuntimeException {

    private Integer code;
    private String  msg;
    private String  errorMsg;

    public ScriptExecException(ResponseCodeEnum responseCode, String errorMsg) {
        super(responseCode.getMessage());
        this.code = responseCode.getCode();
        this.msg = responseCode.getMessage();
        this.errorMsg=errorMsg;
    }
}
