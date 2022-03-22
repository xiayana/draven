package com.lab8.engine.listener;

import org.apache.commons.lang3.StringUtils;

import redis.clients.jedis.Jedis;

import com.lab8.engine.entity.CityBean;

import com.lab8.engine.utils.BeanUtils;
/**

 *

 * @ClassName: SimpleRedisClient

 * @date: 2018-12-26 下午12:53:00

 */

public class SimpleRedisClient {

    /**队列名称*/

    public static final String cityQueue= "CITY_QUEUE";

    /**redis服务器IP*/

    public static final String redisIp= "101.42.242.227";

    /**redis服务器端口*/

    public static final int redisPort= 6379;

    /**测试主函数

     * @throws InterruptedException */

    public static void main(String[] args) throws InterruptedException{

        System.out.println("测试开始......");

        writeRedisQueue();

        System.out.println("主线程休眠10秒");

        Thread.sleep(10000);

        String jsonStr = ReadRedisQueue();
        String jsonStr1 = ReadRedisQueue1();
        parseObject(jsonStr);
        parseObject(jsonStr1);

        System.out.println("测试结束......");

    }

    public static void writeRedisQueue(){

        System.out.println("测试写redis队列开始......");

        String cityStr = getCity(getCityBean());

        System.out.println("写入队列的数据: ");

        System.out.println(cityStr);

/** 1.创建jedis实例,构造函数传入redis的IP地址和端口号 */

        Jedis jedis = new Jedis(redisIp,redisPort);
        jedis.auth("123456");
/**2.字符串写入队列中*/

        jedis.lpush(cityQueue,cityStr);


/** 3.关闭连接 */

        jedis.close();

        System.out.println("测试写redis队列结束......");

    }

    public static String ReadRedisQueue() {

        System.out.println("测试读redis列开始......");

/** 1.创建jedis实例,构造函数传入redis的IP地址和端口号 */

        Jedis jedis = new Jedis(redisIp,redisPort);
        jedis.auth("123456");

/** 2.在队列中读取数据*/

        String result = jedis.lpop(cityQueue);

/** 3.关闭连接 */

        jedis.close();

        System.out.println("打印从队列获取的数据:");

        System.out.println(result);

        System.out.println("测试读redis列结束......");

        return result;

    }
    public static String ReadRedisQueue1() {

        System.out.println("测试读redis列开始......");

/** 1.创建jedis实例,构造函数传入redis的IP地址和端口号 */

        Jedis jedis = new Jedis(redisIp,redisPort);
        jedis.auth("123456");

/** 2.在队列中读取数据*/

        String result = jedis.lpop(cityQueue);

/** 3.关闭连接 */

        jedis.close();

        System.out.println("打印从队列获取的数据:");

        System.out.println(result);

        System.out.println("测试读redis列结束......");

        return result;

    }
    /**json字符串转换为对象*/

    public static void parseObject(String jsonStr){

        if(StringUtils.isBlank(jsonStr)) return ;

        CityBean cityBean = BeanUtils.parseObject(jsonStr, CityBean.class);

        System.out.println("输出从字符串转换后的对象:");

        System.out.println(cityBean.toString());

    }

    /**获取城市json字符串*/

    public static String getCity(CityBean city){

        return BeanUtils.toJSONString(city);

    }

    /**获取一个城市对象*/

    public static CityBean getCityBean(){

        CityBean cityBean = new CityBean();

        cityBean.setCITY_NAME("厦门");

        cityBean.setAREA_CODE("0592");

        cityBean.setPOSTAL_CODE("361000");

        cityBean.setAREA_NUMBER("350200");

        cityBean.setDESCRIBE("适合居住");

        return cityBean;

    }

}