package com.github.hobbylabs.rest.restxml.service;

import com.github.hobbylabs.rest.restxml.model.Country;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = { "request.fetchSizeLimit=2" })
public class RestXmlServiceTest {
    @Autowired
    @InjectMocks
    private RestXmlService restXmlService;

    @Test
    @DisplayName("getCountry() should return some Countries")
    public void test0001() {
        Country country = restXmlService.getCountry();
        assertEquals(2, country.getCities().size());
    }
}
