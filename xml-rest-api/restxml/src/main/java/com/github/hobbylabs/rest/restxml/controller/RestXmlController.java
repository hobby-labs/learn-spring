package com.github.hobbylabs.rest.restxml.controller;

import com.github.hobbylabs.rest.restxml.model.Country;
import com.github.hobbylabs.rest.restxml.model.ErrorResponse;
import com.github.hobbylabs.rest.restxml.model.RequestCountry;
import com.github.hobbylabs.rest.restxml.service.RestXmlService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1")
public class RestXmlController {

    private final RestXmlService restXmlService;

    public RestXmlController(RestXmlService restXmlService) {
        this.restXmlService = restXmlService;
    }

    @RequestMapping(value = {"/get"}, method = RequestMethod.GET, produces=MediaType.APPLICATION_XML_VALUE)
    public Country getCountry() {
        return restXmlService.getCountry();
    }

    @RequestMapping(value = {"/get"}, method = RequestMethod.POST, produces=MediaType.APPLICATION_XML_VALUE)
    public Country getCountry(@RequestBody RequestCountry requestCountry) throws Exception {
        return restXmlService.getCountryByRequestCountry(requestCountry);
    }

    private HttpHeaders errHeaders = new HttpHeaders();

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> exception(Exception exception) {
        ErrorResponse errorResponseBody = new ErrorResponse();
        errorResponseBody.setMessage(exception.getMessage());
        errorResponseBody.setCode("Error");

        return new ResponseEntity<>(errorResponseBody, errHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostConstruct
    public void initResources() {
        errHeaders.add("Content-type", "application/xml");
    }
}
