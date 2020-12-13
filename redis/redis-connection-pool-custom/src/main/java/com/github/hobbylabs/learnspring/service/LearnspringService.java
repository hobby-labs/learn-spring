package com.github.hobbylabs.learnspring.service;

import org.hibernate.validator.constraints.ConstraintComposition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LearnspringService {

    private static Logger logger = LoggerFactory.getLogger(LearnspringService.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String KEY_COUNTER = "counter";

    public int incrementCounter() {
        String resultString = redisTemplate.opsForValue().get(KEY_COUNTER);
        System.out.println("Num of connection: " + redisTemplate.getClientList().size());

        if (resultString == null) {
            resultString = "0";
        }
        int result = Integer.parseInt(resultString);
        ++result;

        redisTemplate.opsForValue().set(KEY_COUNTER, String.valueOf(result));
        logger.info("Redis counter: {}", String.valueOf(result));

        return result;
    }
}
