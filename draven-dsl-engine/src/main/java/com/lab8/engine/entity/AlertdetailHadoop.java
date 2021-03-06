package com.lab8.engine.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
public class AlertdetailHadoop implements Serializable {
    private static final long serialVersionUID = 213724921152226069L;
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
            private Integer alertType;//1策略2画像
            private Date updateTime;
            private Date createTime;

            private Integer isMark;//0否1是
            private String markValues;
            private String alertUser;
            private String alertMessage;
            private Date alertMessageTime;

}
