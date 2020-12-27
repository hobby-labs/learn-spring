package com.github.hobbylabs.learnspring.controller;

import com.github.hobbylabs.learnspring.data.Stores;
import com.github.hobbylabs.learnspring.service.LearnSpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class LearnSpringController {

    @Autowired
    private LearnSpringService learnSpringService;

    @RequestMapping(value = {"/something"}, method = RequestMethod.GET)
    public ResponseEntity<Stores> getSomething() {

        Stores stores = learnSpringService.getAllStores();

        HttpHeaders headers = new HttpHeaders();
        headers.add("header1", "value1");

        return new ResponseEntity<>(stores, headers, HttpStatus.OK);
    }
}
