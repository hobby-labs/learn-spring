package com.github.hobbylabs.rest.restxml.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.hobbylabs.rest.restxml.model.City;
import com.github.hobbylabs.rest.restxml.model.Country;
import com.github.hobbylabs.rest.restxml.service.RestXmlService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestXmlController.class)
public class RestXmlControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestXmlService restXmlService;

    private XmlMapper mapper = new XmlMapper();

    @Test
    @DisplayName("App should return Country object if requesting to /get with method GET behind WebMvc")
    public void test0001() throws Exception {
        List<City> cities = new ArrayList<>();
        Country country = new Country();
        country.setId(1);

        Mockito.when(restXmlService.getCountry()).thenReturn(country);
        this.mockMvc.perform(get("/api/v1/get")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("<Country id=\"1\"/>"));
    }
}
