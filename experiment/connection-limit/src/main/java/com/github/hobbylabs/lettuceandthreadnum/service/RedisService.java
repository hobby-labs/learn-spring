package com.github.hobbylabs.lettuceandthreadnum.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.Instant;

@Service
public class RedisService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisService.class);

    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public RedisService(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public String getRedisMessage(String key) {

        LOGGER.info("Requesting to Redis with key=" + key);
        Instant start = Instant.now();
        String result = stringRedisTemplate.opsForValue().get(key);
        Instant finish = Instant.now();

        DecimalFormat formatter = new DecimalFormat("###,###");
        LOGGER.info("Consuming Redis data key=" + key + ", value=" + result
                + ", duration=" + formatter.format(Duration.between(start, finish).toMillis()) + "ms");

        return result;
    }

    public void setRedisMessage(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }
}
