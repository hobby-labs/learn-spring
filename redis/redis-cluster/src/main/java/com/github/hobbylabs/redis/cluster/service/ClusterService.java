package com.github.hobbylabs.redis.cluster.service;

import com.github.hobbylabs.redis.cluster.data.DataSomething;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClusterService {

    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public ClusterService(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public DataSomething getSomething(String key) {
        String result = stringRedisTemplate.opsForValue().get(key);
        return new DataSomething(1, result);
    }

    public DataSomething setSomething(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
        return new DataSomething(1, "Success");
    }
}
