package com.github.hobbylabs.learn_spring.modules.make_private_spring_module.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = LearnSpringModuleService.class, properties = {"om.github.hobbylabs.learn_spring.modules.make_private_spring_module.application.properties.message=Foo Bar"})
public class LearnSpringModuleServiceTest {

    @Autowired
    private LearnSpringModuleService learnSpringModuleService;

    @Test
    public void contextLoads() {
        assertThat(learnSpringModuleService).isNotNull();
    }

    @Test
    public void serviceShouldReturnValueOfProperty() {
        assertEquals("Foo Bar", learnSpringModuleService.exec());
    }
}
