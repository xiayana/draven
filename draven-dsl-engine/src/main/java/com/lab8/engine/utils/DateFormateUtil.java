package com.lab8.engine.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间格式化工具类
 * @author xy
 * @since 2022-03-23 18:17:42
 */
@Slf4j
@Component
public class DateFormateUtil {

    public static Date formate(String date){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dateStart = simpleDateFormat.parse(date);
            return dateStart;
        }catch (Exception e){
            log.error("date formate error,date:[{}],formate:[{}]",date,date,e);
            return null;
        }
    }
}
