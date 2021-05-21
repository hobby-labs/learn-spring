package com.github.hobbylabs.rest.restxml.controller;

import com.github.hobbylabs.rest.restxml.model.City;
import com.github.hobbylabs.rest.restxml.model.Country;
import com.github.hobbylabs.rest.restxml.service.RestXmlService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RestXmlController {

    private final RestXmlService restXmlService;

    public RestXmlController(RestXmlService restXmlService) {
        this.restXmlService = restXmlService;
    }

    @RequestMapping(value = {"/get"}, method = RequestMethod.GET, produces= MediaType.APPLICATION_XML_VALUE)
    public Country getSomething() {
        restXmlService.getCountry();
        return restXmlService.getCountry();
    }
}
