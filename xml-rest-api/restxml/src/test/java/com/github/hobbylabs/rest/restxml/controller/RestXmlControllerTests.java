package com.github.hobbylabs.rest.restxml.controller;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.hobbylabs.rest.restxml.model.City;
import com.github.hobbylabs.rest.restxml.model.Country;
import com.github.hobbylabs.rest.restxml.model.ErrorResponse;
import com.github.hobbylabs.rest.restxml.service.RestXmlService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestXmlController.class)
public class RestXmlControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestXmlService restXmlService;

    private XmlMapper mapper = new XmlMapper();

    @Test
    @DisplayName("/get (GET) should return empty country object if service returns no cities")
    public void test0001() throws Exception {
        Country country = new Country();
        country.setId(1);

        Mockito.when(restXmlService.getCountry()).thenReturn(country);

        // // Assert a result directly.
        //this.mockMvc.perform(get("/api/v1/get")).andDo(print())
        //        .andExpect(status().isOk()).andExpect(content().string("<Country id=\"1\"/>"));

        String responseBody = this.mockMvc.perform(get("/api/v1/get"))
                .andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        Country c = mapper.readValue(responseBody, Country.class);

        assertEquals(1, c.getId());
        assertEquals(null, c.getCities());
    }

    @Test
    @DisplayName("/get (GET) should return country object that contains some cities if service returns some cities")
    public void test0002() throws Exception {
        List<City> cities = new ArrayList<>();
        City city = new City(); city.setId(1); city.setName("Foo City"); city.setPopulation(100);
        cities.add(city);
        city = new City(); city.setId(2); city.setName("Bar City"); city.setPopulation(200);
        cities.add(city);

        Country country = new Country();
        country.setId(1);
        country.setCities(cities);

        Mockito.when(restXmlService.getCountry()).thenReturn(country);
        String responseBody = this.mockMvc.perform(get("/api/v1/get"))
                .andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        //System.out.println(responseBody);
        Country c = mapper.readValue(responseBody, Country.class);

        assertEquals(1, c.getId());
        assertEquals(2, c.getCities().size());
        assertEquals(1, c.getCities().get(0).getId());
        assertEquals("Foo City", c.getCities().get(0).getName());
        assertEquals(100, c.getCities().get(0).getPopulation());
        assertEquals(2, c.getCities().get(1).getId());
        assertEquals("Bar City", c.getCities().get(1).getName());
        assertEquals(200, c.getCities().get(1).getPopulation());
    }

    @Test
    @DisplayName("/get (POST) should return Error object if the country which requested was not supported")
    public void testErr0001() throws Exception {
        Mockito.when(restXmlService.getCountryByRequestCountry(any())).thenThrow(new Exception("Foo Exception"));

        String resultBody = this.mockMvc.perform(post(
                "/api/v1/get")
                .contentType(MediaType.APPLICATION_XML)
                .content("<Country countryName=\"Japan\"><Cities><City><Name>Tokyo</Name></City></Cities></Country>")
        ).andDo(print())
        .andExpect(status().isInternalServerError())
        .andExpect(r -> assertTrue(r.getResolvedException() instanceof Exception))
        .andReturn().getResponse().getContentAsString();

        //System.out.println(resultBody);
        ErrorResponse errResponse = mapper.readValue(resultBody, ErrorResponse.class);
        assertEquals("Error", errResponse.getCode());
        assertEquals("Foo Exception", errResponse.getMessage());
    }
}
