package com.github.hobbylabs.spring.learnspringmodule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@EnableConfigurationProperties(LearnSpringModuleServiceProperties.class)
public class LearnSpringModuleService {
    @Autowired
    private LearnSpringModuleServiceProperties learnSpringModuleServiceProperties;

    public String exec() {
        String result = learnSpringModuleServiceProperties.getMessage();
        return result;
    }
}