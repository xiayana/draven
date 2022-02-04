package com.lab8.engine.config;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class EsperConfig {
    public final static Map<String, EPStatement> STATEMENT_MAP = new HashMap<>();

    @Bean
    public EPServiceProvider epServiceProvider() {
        com.espertech.esper.client.Configuration config = new com.espertech.esper.client.Configuration();
        Map<String, Object> mobillocaltion = new HashMap<>();
        mobillocaltion.put("name", String.class);
        mobillocaltion.put("number", String.class);
        config.addEventType("mobillocaltion", mobillocaltion);
        //add event type
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

