package com.github.hobbylabs.learnspring.controller;

import com.github.hobbylabs.learnspring.data.DataSomething;
import com.github.hobbylabs.learnspring.service.LearnSpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ArrayList;


@RestController
@RequestMapping("/api/v1")
public class LearnspringController {

    @Autowired
    private LearnSpringService service;

    @RequestMapping(value = {"/something"}, method = RequestMethod.GET)
    public DataSomething getSomething() {

        List<String> list = new ArrayList<>();
        service.validateCustomers(list);

        return null;
    }
}
