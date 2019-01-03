package com.chinasoft.tax.config.redis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Description: redis配置类
 * @Date: Created in 19:12 2018/10/29
 * @Author: yaochenglong
 */

@Configuration
public class RedisConfig {

    @Autowired
    JedisConnectionFactory jedisConnectionFactory;


    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(1000);
        jedisConnectionFactory.afterPropertiesSet();
        return new JedisPool(jedisPoolConfig,
                jedisConnectionFactory.getHostName(), jedisConnectionFactory.getPort(),
                jedisConnectionFactory.getTimeout(), jedisConnectionFactory.getPassword());
    }
}