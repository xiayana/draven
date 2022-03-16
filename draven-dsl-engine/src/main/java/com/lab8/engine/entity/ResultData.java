package com.lab8.engine.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

@Data
public class ResultData implements Serializable {
    private Integer code = 0;
    private String msg = "成功";
    private Object data;

}
