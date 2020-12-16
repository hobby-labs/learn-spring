package com.github.hobbylabs.learnspring.service;

import com.github.hobbylabs.learnspring.mapper.CustomerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LearnSpringService {

    private static Logger logger = LoggerFactory.getLogger(LearnSpringService.class);

    @Autowired
    private CustomerMapper mapper;

    private Set<String> cacheCustomerNameSet;

    private long cacheAgeInMillis = 10000L;

    private long mapperCreatedDateInMillis = 0L;

    public void validateCustomers(List<String> customers) {
        Set<String> customerNameSet = getCustomerMapper();
    }

    public Set<String> getCustomerMapper() {
        long currentTimeMillis = System.currentTimeMillis();

        if(currentTimeMillis > (mapperCreatedDateInMillis + cacheAgeInMillis)) {
            // Lazy synchronization.
            // Change cache cacheCustomerNameSet if cache age were expired.
            mapperCreatedDateInMillis = currentTimeMillis;
            List<String> customerDtoList = mapper.selectNameAll();
            cacheCustomerNameSet = new HashSet<>(customerDtoList);
        }

        return cacheCustomerNameSet;
    }
}
