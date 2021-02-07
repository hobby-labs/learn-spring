package com.github.hobbylabs.spring.example.service;

import com.github.hobbylabs.spring.example.data.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ExampleService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Cacheable(cacheNames="product", sync=true)
    public Product getProductByName(String name) {
        System.out.println("getProductByName(): Getting the product from the name " + name + ": " + new Date());
        return getRawProductByName(name);
    }

    public Product getRawProductByName(String name) {
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long price = Long.parseLong(stringRedisTemplate.opsForValue().get(name));

        return new Product(name, price);
    }

    @CacheEvict("product")
    public Product evictCache(String name) {
        return getRawProductByName(name);
    }

    public long setProduct(Product product) {
        stringRedisTemplate.opsForValue().set(product.getName(), Long.toString(product.getPrice()));
        return 1L;
    }
}
