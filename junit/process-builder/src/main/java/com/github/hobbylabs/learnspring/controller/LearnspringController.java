package com.github.hobbylabs.learnspring.controller;

import com.github.hobbylabs.learnspring.service.LearnspringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class LearnspringController {

    @Autowired
    private LearnspringService service;

    @RequestMapping(value = {"/something"}, method = RequestMethod.GET)
    public ResponseEntity<Void> getSomething() {
        ResponseEntity<Void> response = new ResponseEntity(HttpStatus.OK);

        return response;
    }
}