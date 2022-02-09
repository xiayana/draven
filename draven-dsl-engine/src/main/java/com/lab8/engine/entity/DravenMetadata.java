package com.lab8.engine.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DravenMetadata implements Serializable {
    private static final long serialVersionUID = 795421817778567919L;
    private Integer id;
    private String allowed;
    private String cmd;
    private String dst;
    private String host;
    private String src;
    private Date timestamp;
    private String user;
    private String pid;
    private String ppid;
    private String time;
    private String ip;
    private String esperSql;
    private Date updateTime;
    private Date createTime;

}
