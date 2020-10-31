package com.github.hobbylabs.learnspring.config;

import io.lettuce.core.ClientOptions;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

// https://mashhurs.wordpress.com/2020/03/26/jedis-vs-lettuce-java-redis-clients/
// https://www.oodlestechnologies.com/blogs/Configure-Connection-Pooling-With-Redis-In-Spring-Boot/
@Configuration
public class RedisConnectionConfig {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        if (activeProfile.equals("lettuce")) {
            LettuceClientConfiguration lettuceClientConfiguration =
                    LettuceClientConfiguration.builder()
                            .commandTimeout(Duration.ofSeconds(2000))
                            .clientOptions(ClientOptions.builder().build())
                            .build();

            RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration();
            clusterConfiguration.clusterNode(host, port);
            //new LettuceConnectionFactory(clusterConfiguration);

            return new LettuceConnectionFactory(clusterConfiguration, lettuceClientConfiguration);
        } else {
            RedisStandaloneConfiguration redisStandaloneConfiguration =
                    new RedisStandaloneConfiguration(host, port);
            redisStandaloneConfiguration.setDatabase(0);

            // Pool config
            JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpccb =
                    (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder)JedisClientConfiguration.builder();
            jpccb.poolConfig(jedisPoolConfig());

            return new JedisConnectionFactory(redisStandaloneConfiguration, jpccb.build());
        }
    }

    private JedisPoolConfig jedisPoolConfig() {
        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(128);
        poolConfig.setMaxIdle(128);
        poolConfig.setMinIdle(36);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
        poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setBlockWhenExhausted(true);
        return poolConfig;
    }
}
