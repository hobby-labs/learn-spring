package com.github.hobbylabs.rest.restxml.service;

import com.github.hobbylabs.rest.restxml.model.City;
import com.github.hobbylabs.rest.restxml.model.Country;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestXmlService {
    public Country getCountry() {
        List<City> cities = new ArrayList<City>();
        return new Country(1, cities);
    }
}
