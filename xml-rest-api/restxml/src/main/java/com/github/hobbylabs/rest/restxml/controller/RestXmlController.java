package com.github.hobbylabs.rest.restxml.controller;

import com.github.hobbylabs.rest.restxml.model.City;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RestXmlController {

    @RequestMapping(value = {"/get"}, method = RequestMethod.GET, produces= MediaType.APPLICATION_XML_VALUE)
    public City getSomething() {
        return new City(1L, "foo", 10);
    }
}
