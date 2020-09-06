package com.github.hobbylabs.learnspring.controller;

import com.github.hobbylabs.learnspring.data.DataSomething;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class LearnspringController {
    @RequestMapping(value = {"/something"}, method = RequestMethod.GET)
    public DataSomething getSomething() {
        DataSomething response = new DataSomething();
        response.setId(1);
        response.setDescription("foo");

        return response;
    }
}
