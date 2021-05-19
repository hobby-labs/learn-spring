package com.github.hobbylabs.redis.cluster.controller;

import com.github.hobbylabs.redis.cluster.data.DataSomething;
import com.github.hobbylabs.redis.cluster.service.ClusterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ClusterController {

    @Autowired
    private ClusterService service;

    @RequestMapping(value = {"/get"}, method = RequestMethod.GET)
    public DataSomething getSomething() {
        return service.getSomething("Foo");
    }
    @RequestMapping(value = {"/set"}, method = RequestMethod.POST)
    public DataSomething setSomething() {
        return service.setSomething("Foo", "Bar");
    }
}