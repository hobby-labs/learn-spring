package com.github.hobbylabs.learnspring.controller;

import com.github.hobbylabs.learnspring.data.DataSomething;
import com.github.hobbylabs.learnspring.service.LearnspringService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class LearnspringController {

    @Autowired
    private LearnspringService service;

    @RequestMapping(value = {"/something"}, method = RequestMethod.GET)
    public DataSomething getSomething() {

        int result = service.incrementCounter();

        DataSomething response = new DataSomething();
        response.setId(result);
        response.setDescription("foo");

        return response;
    }
}
