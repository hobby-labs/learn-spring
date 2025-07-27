package com.github.hobbylabs.spring.learnspringmodule.service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "learnspringmodule")
@Component
public class LearnSpringModuleServiceProperties {
    /** A message of learnspringmodule */
    private String message;
}
