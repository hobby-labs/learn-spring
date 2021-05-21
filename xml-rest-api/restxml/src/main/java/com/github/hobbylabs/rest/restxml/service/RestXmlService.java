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
        cities.add(new City(1, "Tokyo", 13000000));
        cities.add(new City(2, "Osaka", 8000000));
        cities.add(new City(3, "Sapporo", 1900000));
        return new Country(1, cities);
    }
}
