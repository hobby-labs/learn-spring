package com.github.hobbylabs.rest.restxml.service;

import com.github.hobbylabs.rest.restxml.model.City;
import com.github.hobbylabs.rest.restxml.model.Country;
import com.github.hobbylabs.rest.restxml.model.RequestCountry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RestXmlService {

    @Value("${request.fetchSizeLimit}")
    private int fetchSizeLimit;

    private static final Map<String, City> dataCities = new HashMap<>();

    public RestXmlService() {
        // Dummy data
        City c = new City(); c.setId(1); c.setName("Tokyo"); c.setPopulation(13000000); dataCities.put("Tokyo", c);
        c = new City(); c.setId(2); c.setName("Osaka"); c.setPopulation(8000000); dataCities.put("Osaka", c);
        c = new City(); c.setId(3); c.setName("Hokkaido"); c.setPopulation(5000000); dataCities.put("Hokkaido", c);
        c = new City(); c.setId(4); c.setName("Fukuoka"); c.setPopulation(1500000); dataCities.put("Fukuoka", c);
        c = new City(); c.setId(5); c.setName("Kyoto"); c.setPopulation(1400000); dataCities.put("Kyoto", c);
        c = new City(); c.setId(6); c.setName("Miyagi"); c.setPopulation(2300000); dataCities.put("Miyagi", c);
        c = new City(); c.setId(7); c.setName("Aichi"); c.setPopulation(7500000); dataCities.put("Aichi", c);
    }

    public Country getCountry() {
        return getCountry(fetchSizeLimit);
    }

    private Country getCountry(int maxFetchSize) {
        List<City> cities = new ArrayList();
        int counter = 0;

        for (Map.Entry<String, City> e : dataCities.entrySet()) {
            cities.add(e.getValue());
            if(++counter >= maxFetchSize) break;
        }

        Country country = new Country();
        country.setId(1);
        country.setCities(cities);

        return country;
    }

    private Country getCountryByRequestCities(List<String> requestCities) throws Exception {
        List<City> cities = new ArrayList<>();

        for (int i = 0; i < requestCities.size(); ++i) {
            City c = dataCities.get(requestCities.get(i));
            if (c == null) {
                throw new Exception("Name of the city " + requestCities.get(i) + " is not found in the data");
            }
            cities.add(c);
        }

        Country country = new Country();
        country.setId(1);
        country.setCities(cities);

        return country;
    }

    public Country getCountryByRequestCountry(RequestCountry requestCountry) throws Exception {
        String countryName = requestCountry.getCountryName();
        if (!"Japan".equals(requestCountry.getCountryName())) {
            throw new Exception("Un supported country exception was thrown. This application only supports country \"Japan\"");
        }

        int maxFetchSize = (
                requestCountry.getMaxFetchSize() == null ? fetchSizeLimit : requestCountry.getMaxFetchSize());
        List<String> requestCities = requestCountry.getRequestCities();

        if (requestCities == null) {
            return getCountry(maxFetchSize);
        }

        if (requestCities.size() > maxFetchSize) {
            throw new Exception("Number of cities are exceeded in maxFetchSize(" + maxFetchSize + ")");
        }

        return getCountryByRequestCities(requestCities);
    }
}
