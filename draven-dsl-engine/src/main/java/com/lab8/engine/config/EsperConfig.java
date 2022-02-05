package com.lab8.engine.config;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.lab8.engine.entity.Test1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class EsperConfig {

    public final static Map<Integer, EPStatement> STATEMENT_MAP = new HashMap<>();

    @Bean
    public EPServiceProvider epServiceProvider() {
        com.espertech.esper.client.Configuration config = new com.espertech.esper.client.Configuration();
        Map<String, Object> mobillocaltion = new HashMap<>();
        Field[] f = Test1.class.getDeclaredFields(); //获取该类的字段
        for(Field ff:f) //遍历字段
        {
            mobillocaltion.put(ff.getName(), ff.getType());
        }
        config.addEventType("mobillocaltion", mobillocaltion);
        //  config.addEventType(PersonEvent.class);

        EPServiceProvider epServiceProvider = EPServiceProviderManager.getDefaultProvider(config);
        // epServiceProvider.initialize();
        return epServiceProvider;
    }

    @Bean
    public EPAdministrator epAdministrator() {
        return epServiceProvider().getEPAdministrator();
    }
}

