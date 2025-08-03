package com.example.demo.controller;

import com.github.hobbylabs.learn_spring.modules.make_private_spring_module.service.LearnSpringModuleService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "com.github.hobbylabs.spring.learnspringmodule")
@RestController
@RequestMapping("/demo")
public class DemoController {
    @Data
    private static class DemoResponse {
        private int id;
        private String message1;
        private String message2;
    }

    @Autowired
    private LearnSpringModuleService learnSpringModuleService;

    @GetMapping
    public DemoResponse hello() {
        DemoResponse response = new DemoResponse();
        response.setId(1);
        response.setMessage1(learnSpringModuleService.exec());

        return response;
    }
}
