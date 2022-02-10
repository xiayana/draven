package com.deavenapiweb.exception;

import com.deavenapiweb.common.ResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 业务异常处理类
 *
 * @author xy
 * @since 2022-02-09 12:02:18
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ServiceException extends RuntimeException {
    private Integer code;
    private String msg;

    public ServiceException(Integer code, String msg) {
        super(msg);
        if (ResponseCode.PARAMETER_IS_NULL == code) {
            msg = "参数" + msg + "为空！";
        }
        this.code = code;
        this.msg = msg;
    }

}
