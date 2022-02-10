package com.deavenapiweb.dto;

import java.io.Serializable;

import com.deavenapiweb.constants.CommonConstants;
import com.deavenapiweb.enume.ResponseCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.deavenapiweb.common.ResponseCode.FAIL_CODE;
import static com.deavenapiweb.common.ResponseCode.SUCCESS_CODE;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResDto<T> implements Serializable {


    public static final long serialVersionUID = 42L;

    private Integer code;
    private String message;
    private T data;


    public static final ResDto<String> SUCCESS = new ResDto<String>(null);
    public static final ResDto<String> FAIL = new ResDto<String>(FAIL_CODE, null);

    public ResDto(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResDto(T data) {
        this.code = SUCCESS_CODE;
        this.data = data;
    }

    /**
     * 设置data
     * @param data
     * @return ResDto
     */
    public ResDto<T> setBody(T data) {
        this.data = data;
        return this;
    }

    /**
     * 创建成功返回ResDto
     * @param data 返回数据实体
     * @param <T>
     * @return
     */
    public static <T> ResDto<T> createSuccess(T data) {
        ResDto<T> resp = new ResDto<>();
        resp.setCode(ResponseCodeEnum.SUCCESS.getCode());
        resp.setMessage(ResponseCodeEnum.SUCCESS.getMessage());
        return resp.setBody(data);
    }

    /**
     * 创建成功返回ResDto
     * @return ResDto
     */
    public static  ResDto createSuccess() {
        ResDto resp = new ResDto();
        resp.setCode(ResponseCodeEnum.SUCCESS.getCode());
        resp.setMessage(ResponseCodeEnum.SUCCESS.getMessage());
        return resp;
    }

    public static ResDto createError(int code, String message) {
        ResDto resp = new ResDto();
        resp.code = code;
        resp.message = message;
        return resp;
    }

    public static ResDto createError(ResponseCodeEnum responseCode,String extraMessage) {
        ResDto resp = new ResDto();
        resp.setCode(responseCode.getCode());
        resp.setMessage(responseCode.getMessage()+ CommonConstants.SYMBOL_COMMA+extraMessage);
        return resp;
    }

    public static ResDto createInterError() {
        ResDto resp = new ResDto();
        resp.setCode(ResponseCodeEnum.ERROR_INTERNAL.getCode());
        resp.setMessage(ResponseCodeEnum.ERROR_INTERNAL.getMessage());
        return resp;
    }


}
