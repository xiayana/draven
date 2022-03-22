package com.lab8.engine.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (ErrorLog)实体类
 *
 * @author xy
 * @since 2022-03-21 17:51:14
 */
@Data
public class ErrorLog implements Serializable {
    private static final long serialVersionUID = -87558828454886921L;
    
    private Integer id;
    
    private String numberUuid;
    
    private String msg;
    
    private Date createTime;

}

