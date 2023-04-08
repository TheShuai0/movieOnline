package com.example.movieonlinedemo.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @ClassName redisConfig
 * @Description TODO
 * @Author 加辣椒了吗?
 * @Date 2022/4/28 2:33
 * @Version 1.0
 **/
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
        // 将redis注入工厂
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        // 设置key的序列化方式
        template.setKeySerializer (RedisSerializer.string());
        //设置value的序列化方式
        template.setValueSerializer (RedisSerializer.json());
        // 设置hash的key的序列化方式
        template. setHashKeySerializer (RedisSerializer.string());
        // 设置hash的value的序列化方式
        template.setHashValueSerializer (RedisSerializer.json());
        // 使设置生效
        template.afterPropertiesSet();

        return template;
    }
}


