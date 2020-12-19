package com.github.hobbylabs.learnspring.service;

import com.github.hobbylabs.learnspring.mapper.CustomerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LearnSpringService {

    private static Logger logger = LoggerFactory.getLogger(LearnSpringService.class);

//    @Autowired
//    private CustomerMapper customerMapper;

    private final CustomerMapper customerMapper;

//    @Value("${com.github.hobbylabs.learnspring.service.LearnSpringService.cacheAgeInMillis:2000}")
//    private long cacheAgeInMillis;
    private final long cacheAgeInMillis;

    private long mapperCreatedDateInMillis = 0L;

    private Set<String> cacheCustomerNameSet;

    @Autowired
    public LearnSpringService(
            CustomerMapper customerMapper,
            @Value("${com.github.hobbylabs.learnspring.service.LearnSpringService.cacheAgeInMillis:2000}") long cacheAgeInMillis) {
        // Add @Autowired to the constructor to be testable.
        // https://tedvinke.wordpress.com/2014/02/13/mockito-why-you-should-not-use-injectmocks-annotation-to-autowire-fields/
        this.customerMapper = customerMapper;
        this.cacheAgeInMillis = cacheAgeInMillis;
    }

    public void validateCustomers(List<String> customers) {
        Set<String> customerNameSet = getCustomerMapper();
    }

    public Set<String> getCustomerMapper() {
        long currentTimeMillis = System.currentTimeMillis();

        if(currentTimeMillis > (mapperCreatedDateInMillis + cacheAgeInMillis)) {
            // Lazy synchronization.
            // Change cache cacheCustomerNameSet if cache age were expired.
            mapperCreatedDateInMillis = currentTimeMillis;
            ////List<String> customerDtoList = customerMapper.selectNameAll();
            ////cacheCustomerNameSet = new HashSet<>(customerDtoList);
            cacheCustomerNameSet = new HashSet<>(new ArrayList<>());
        }

        System.out.println("cacheAgeInMillis: " + cacheAgeInMillis + ", Hash of cacheCustomerNameSet: " + cacheCustomerNameSet.hashCode());

        return cacheCustomerNameSet;
    }
}
