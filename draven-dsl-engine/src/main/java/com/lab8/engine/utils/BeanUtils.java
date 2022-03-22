package com.lab8.engine.utils;
import com.alibaba.fastjson.JSON;
import com.lab8.engine.entity.CityBean;

import java.lang.reflect.Type;

/**

 *

 * @ClassName: BeanUtils

 * @date: 2018-12-26 下午12:44:31

 */

public class BeanUtils {

    /**实体对象转换为json字符串*/

    public static String toJSONString(Object city){

        String jsonStr = "";

        if(city!=null){

                jsonStr = JSON.toJSONString(city);

        }

        return jsonStr;

    }

    /**将json字符串转换为指定对象*/

    public static CityBean parseObject(String text, Class clazz) {

        return JSON.parseObject(text, (Type) clazz);

    }

}
