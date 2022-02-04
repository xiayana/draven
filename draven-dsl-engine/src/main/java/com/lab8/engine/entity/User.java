package com.lab8.engine.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private  Integer id;
    private  String allowed;
    private String cmd;
    private String dst;
    private String host;
    private String src;
    private String timestamp;
    private String user;
    private String esperSql;
    private Date update_time;
    private Date create_time;



}
