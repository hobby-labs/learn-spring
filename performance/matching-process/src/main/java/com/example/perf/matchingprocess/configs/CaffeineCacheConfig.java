package com.example.perf.matchingprocess.configs;

import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

public class CaffeineCacheConfig {
    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCacheSpecification("expireAfterWrite=60s");
        cacheManager.setCacheNames(Arrays.asList("persons-name-cache"));

        return cacheManager;
    }
}
