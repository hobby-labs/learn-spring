package com.github.hobbylabs.learnspring.controller;

import com.github.hobbylabs.learnspring.data.DataSomething;
import com.github.hobbylabs.learnspring.service.LearnSpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class LearnSpringController {

    @Autowired
    private LearnSpringService learnSpringService;

    @RequestMapping(value = {"/something"}, method = RequestMethod.GET)
    public DataSomething getSomething() {

        List<String> list = new ArrayList<>();
        learnSpringService.validateCustomers(list);

        return null;
    }
}
