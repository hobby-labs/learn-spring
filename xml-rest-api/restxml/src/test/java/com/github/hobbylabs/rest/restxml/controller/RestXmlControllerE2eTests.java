package com.github.hobbylabs.rest.restxml.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.hobbylabs.rest.restxml.model.City;
import com.github.hobbylabs.rest.restxml.model.Country;
import com.github.hobbylabs.rest.restxml.model.Foo;
import com.github.hobbylabs.rest.restxml.service.RestXmlService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = { "request.fetchSizeLimit=2" }
)
public class RestXmlControllerE2eTests {
    @LocalServerPort
    private int port;

    @InjectMocks
    RestXmlController restXmlController;

    @Mock
    RestXmlService restXmlService;

    @Autowired
    private TestRestTemplate restTemplate;

    private XmlMapper mapper = new XmlMapper();

    @Test
    @DisplayName("App should return Country object if requesting to /get endpoint with method GET")
    public void test0001() throws JsonProcessingException {
        String result = restTemplate.getForObject(
                "http://localhost:" + port + "/api/v1/get",
                String.class
        );

//        System.out.println(result);
        Country country = mapper.readValue(result, Country.class);
        assertEquals(1, country.getId());
        assertEquals(2, country.getCities().size());
    }

    @Test
    @DisplayName("foo")
    public void test0002() {


        Country c = new Country();
        c.setId(100);
        Mockito.when(restXmlService.getCountry()).thenReturn(c);

        String result = restTemplate.getForObject(
                "http://localhost:" + port + "/api/v1/get",
                String.class
        );

        System.out.println(result);

    }
}
