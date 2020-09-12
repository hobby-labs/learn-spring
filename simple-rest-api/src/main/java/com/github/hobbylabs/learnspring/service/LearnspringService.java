package com.github.hobbylabs.learnspring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class LearnspringService {

    private static Logger logger = LoggerFactory.getLogger(LearnspringService.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String KEY_COUNTER = "counter";

    public int incrementCounter() {
        String resultString = redisTemplate.opsForValue().get(KEY_COUNTER);

        if (resultString == null) {
            resultString = "0";
        }
        int result = Integer.parseInt(resultString);
        ++result;

        redisTemplate.opsForValue().set(KEY_COUNTER, String.valueOf(result));

        logger.info("======================================================");
        logger.info(String.valueOf(result));
        logger.info("======================================================");

        return result;
    }
}
