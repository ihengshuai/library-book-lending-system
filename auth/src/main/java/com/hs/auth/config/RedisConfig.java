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
     * @param redisConnectionFactory Redis连接工厂，用于创建Redis连接。
     * @return 配置好的RedisTemplate实例，用于字符串和对象之间的序列化和反序列化操作。
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // 配置值序列化器
        // 使用GenericJacksonJsonRedisSerializer对值进行JSON序列化
        GenericJacksonJsonRedisSerializer serializer = new GenericJacksonJsonRedisSerializer(new ObjectMapper());
        template.setValueSerializer(serializer);
        template.setHashValueSerializer(serializer);

        // 配置键序列化器
        // 使用StringRedisSerializer对键进行序列化
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());

        // 设置属性值并初始化
        template.afterPropertiesSet();

        return template;
    }

}
