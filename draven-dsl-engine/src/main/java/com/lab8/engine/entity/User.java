package com.lab8.engine.entity;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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


    public static void main(String[] args) {
        Calendar c=Calendar.getInstance();

        int y=2022;//年

        int M=3;//月

        int d=17;//日

        int H=10;//时

        int m=43;//分

        int s=0;//秒

        c.set(Calendar.YEAR, y);

        c.set(Calendar.MONTH, M-1);

        c.set(Calendar.DATE, d);

        c.set(Calendar.HOUR_OF_DAY, H);

        c.set(Calendar.MINUTE, m);

        c.set(Calendar.SECOND, s);

        Calendar now=Calendar.getInstance();

        long aTime=now.getTimeInMillis();

        long bTime=c.getTimeInMillis();

        long cTime=aTime-bTime;

        long sTime=cTime/1000;//时间差，单位：秒

        long mTime=sTime/60;
            System.out.println("-===="+mTime);
        long hTime=mTime/60;

        long dTime=hTime/24;

        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println("当前时间："+f.format(now.getTime()));

        System.out.println("设定时间："+f.format(c.getTime()));
        if(dTime >= 0 || hTime%24 >= 0 || mTime%60 >= 5){
            System.out.println("差5");
        }
        System.out.println("时间差："+dTime+"天"+hTime%24+"时"+mTime%60+"分"+sTime%60+"秒");

    }
}
