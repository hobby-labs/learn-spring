package com.github.hobbylabs.learnspring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class LearnspringService {

    private static Logger logger = LoggerFactory.getLogger(LearnspringService.class);

    // You can use StringRedisTemplate instead of RedisTemplate<String, String>
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String KEY_COUNTER = "counter";

    public long incrementCounter() {
        // increment(key) can increment the value even if it was saved as String
        long counter = redisTemplate.opsForValue().increment(KEY_COUNTER);

        System.out.println(
                new StringBuilder("Num of connections: ")
                        .append(redisTemplate.getClientList().size())
                        .append(", counter: ").append(counter));

        return counter;
    }

    @PostConstruct
    public void init() {
        if (!redisTemplate.hasKey(KEY_COUNTER)) {
            redisTemplate.opsForValue().set(KEY_COUNTER, "0");
        }
    }
}
