package com.github.hobbylabs.learn_spring.modules.make_private_spring_module.service;

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
