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
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class RestXmlController {

    private final RestXmlService restXmlService;

    public RestXmlController(RestXmlService restXmlService) {
        this.restXmlService = restXmlService;
    }

    /**
     * Get all cities of a default country.
     * @return Result of the search
     */
    @RequestMapping(value = {"/get"}, method = RequestMethod.GET, produces=MediaType.APPLICATION_XML_VALUE)
    public Country getCountry() {
        return restXmlService.getCountry();
    }

    /**
     * Get cities with POST method.
     * @param requestCountry data that requires what cities of the country
     * @return Result of the search
     * @throws Exception Some exceptions
     */
    @RequestMapping(value = {"/get"}, method = RequestMethod.POST, produces=MediaType.APPLICATION_XML_VALUE)
    public Country getCountry(@Valid @RequestBody RequestCountry requestCountry) throws Exception {
        return restXmlService.getCountryByRequestCountry(requestCountry);
    }

    private HttpHeaders errHeaders = new HttpHeaders();

//    @ExceptionHandler(value = Exception.class)
//    public ResponseEntity<ErrorResponse> exception(Exception exception) {
//        ErrorResponse errorResponseBody = new ErrorResponse();
//        errorResponseBody.setMessage(exception.getMessage());
//        errorResponseBody.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
//
//        return new ResponseEntity<>(errorResponseBody, errHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @PostConstruct
    public void initResources() {
        errHeaders.add("Content-type", "application/xml");
    }
}
