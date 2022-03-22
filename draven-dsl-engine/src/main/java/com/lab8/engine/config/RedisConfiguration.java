package com.lab8.engine.config;

import com.lab8.engine.constants.CommonConstants;
import com.lab8.engine.listener.SceneExtraceConsumer;
import com.lab8.engine.utils.redismq.RedisMqConsumerContainer;
import com.lab8.engine.utils.redismq.RedisQueueConfiguration;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.ObjectUtils;

import java.util.concurrent.Executor;

/**
 * redis配置类
 *
 * @author xy
 * @since 2022-03-21 16:09:17
 */
@Configuration
public class RedisConfiguration {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.database}")
    private int database;

    /**
     * springboot2.x 使用LettuceConnectionFactory 代替 RedisConnectionFactory
     * application.yml配置基本信息后,springboot2.x  RedisAutoConfiguration能够自动装配
     * LettuceConnectionFactory 和 RedisConnectionFactory 及其 RedisTemplate
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }


    @Bean
    public RedissonClient getRedisson() {
        Config config = new Config();
        String url = "redis://" + host + ":" + port;
        SingleServerConfig singleServerConfig = config.useSingleServer().setAddress(url).setDatabase(database);
        if (!ObjectUtils.isEmpty(password)) {
            singleServerConfig.setPassword(password);
        }
        // 添加主从配置
        //
        // config.useMasterSlaveServers().setMasterAddress("").setPassword("").addSlaveAddress(new
        // String[]{"",""});
        return Redisson.create(config);
    }


    /**
     * 配置消息监听
     *
     * @return 消费者容器
     */
    @Bean(initMethod = "init")
    public RedisMqConsumerContainer redisMqConsumerContainer(Executor testExecutor, RedisTemplate redisTemplate, SceneExtraceConsumer sceneExtraceConsumer) {
        RedisMqConsumerContainer config = new RedisMqConsumerContainer(redisTemplate, testExecutor);
        config.addConsumer(RedisQueueConfiguration.builder().queue(CommonConstants.ESPER_QUEUENAME)
                .consumer(sceneExtraceConsumer).build());
        return config;
    }
}
