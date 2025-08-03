package com.github.hobbylabs.learn_spring.modules.make_private_spring_module.service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "com.github.hobbylabs.learn-spring.modules.make-private-spring-module.application.properties")
@Component
public class LearnSpringModuleServiceProperties {
    /** A message of learnspringmodule */
    private String message;
}
