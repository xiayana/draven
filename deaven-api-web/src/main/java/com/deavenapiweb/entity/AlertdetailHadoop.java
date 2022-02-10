package com.deavenapiweb.entity;


import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * Ueba告警日志(AlertdetailHadoop)实体类
 *
 * @author xy
 * @since 2022-02-09 11:44:38
 */
@Data
public class AlertdetailHadoop extends Page implements Serializable {
    private static final long serialVersionUID = 174967988319205987L;
    
    private Integer id;
    
    private String timestamp;
    
    private String site;
    
    private String application;
    
    private String hostname;
    
    private String policyid;
    
    private String alertsource;
    
    private String sourcestreams;
    
    private String alertexecutorid;
    
    private String description;
    
    private String remediationid;
    
    private String remediationcallback;
    
    private String alertcontext;
    
    private String streamid;
    
    private Date createTime;
    
    private Date updateTime;


}

