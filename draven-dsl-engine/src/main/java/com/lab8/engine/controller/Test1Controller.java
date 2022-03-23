package com.lab8.engine.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab8.engine.entity.DravenMetadata;
import com.lab8.engine.entity.User;
import com.lab8.engine.service.StrategyService;
import com.lab8.engine.utils.PropertyAppender;
import com.lab8.engine.utils.RedisUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * (Test1)表控制层
 *
 * @author xy
 * @since 2022-02-04 18:48:58
 */
@RestController
@RequestMapping("test1")
public class Test1Controller {
    /**
     * 服务对象
     */
    @Autowired
    private StrategyService test1Service;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisUtil redisUtil;
    @GetMapping("sendRedisMessageTest")
    public String SendRedisMessage() {
      int j = 0;
        for (int i = 0; i < 10; i++) {

        }

        //第一个参数是，消息推送的主题名称；第二个参数是，要推送的消息信息
        //"chat"->主题
        //"我是一条消息"->要推送的消息
        stringRedisTemplate.convertAndSend("chat", "我是一条消息");
        return  "Send Success" ;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public DravenMetadata selectOne(Integer id) {
        return this.test1Service.queryById(id);
    }

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 暂留动态生成元数据
     * @param args
     * @throws JsonProcessingException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void main(String[] args) throws JsonProcessingException, InvocationTargetException, IllegalAccessException {

        User user = new User();
        user.setCmd("Daisy");
        System.out.println(MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(user));

        System.out.println("=====================================");

        Map<String, Object> propertiesMap = new HashMap<>(1);
        propertiesMap.put("bababababa", "asdasd");
        propertiesMap.put("tutututu", "asdasd");
        Object obj = PropertyAppender.generate(user, propertiesMap);
        Field[] f = obj.getClass().getDeclaredFields(); //获取该类的字段
        for(Field ff:f) //遍历字段
        {
            System.out.println(ff.getName() +"----"+ ff.getType());

        }
        System.err.println(MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
    }


}
