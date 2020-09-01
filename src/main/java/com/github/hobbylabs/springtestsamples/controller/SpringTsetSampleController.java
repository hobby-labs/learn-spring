package com.github.hobbylabs.springtestsamples.controller;

import com.github.hobbylabs.springtestsamples.entity.ResponseData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1")
public class SpringTsetSampleController {
    @GetMapping("/user")
    public ResponseData getData() {
        ResponseData response = new ResponseData();
        response.setId(1);
        response.setName("foo");
        
        return response;
    }
}
