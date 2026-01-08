package com.hs.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import tools.jackson.databind.ObjectMapper;


@Configuration
public class RedisConfig {

    /**
     * 配置并返回RedisTemplate，用于操作Redis。
     *
     * @param factory Redis连接工厂，用于创建Redis连接。
     * @return 配置好的RedisTemplate实例，用于字符串和对象之间的序列化和反序列化操作。
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        // String序列化
        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        // JSON序列化
        GenericJacksonJsonRedisSerializer jsonSerializer = new GenericJacksonJsonRedisSerializer(new ObjectMapper());

        // key采用String序列化
        template.setKeySerializer(stringSerializer);
        // value采用JSON序列化
        template.setValueSerializer(jsonSerializer);
        // hash的key采用String序列化
        template.setHashKeySerializer(stringSerializer);
        // hash的value采用JSON序列化
        template.setHashValueSerializer(jsonSerializer);

        template.afterPropertiesSet();

        return template;
    }

}
