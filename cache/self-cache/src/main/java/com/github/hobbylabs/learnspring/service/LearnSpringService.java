package com.github.hobbylabs.learnspring.service;

import com.github.hobbylabs.learnspring.mapper.CustomerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LearnSpringService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LearnSpringService.class);

    /** Mapper of a customer DB table. */
    private final CustomerMapper customerMapper;

    /** Cache age of the customer name set. New customer name set will be created if this age is expired. */
    private final long cacheAgeInMillis;

    /** Created date of the instance of customer name set*/
    private long mapperCreatedDateInMillis = 0L;

    /** customer name set by customers table in the DB */
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

    /**
     * Valudate customers.
     * This method checks all customers exists in the customer mapper.
     * @param customers List of the customers.
     */
    public void validateCustomers(List<String> customers) {
        Set<String> customerNameSet = getCustomerMapperCache();
    }

    /**
     * Get customer mapper.
     * It will returns the cached mapper if cacheAgeInMillis is not expired.
     * @return Cached customer mapper if enabled. If not, returns new customer mapper.
     */
    public Set<String> getCustomerMapperCache() {
        long currentTimeMillis = System.currentTimeMillis();

        if(currentTimeMillis > (mapperCreatedDateInMillis + cacheAgeInMillis)) {
            // Lazy synchronization. Update cacheCustomerNameSet if cache age were expired.
            // If we want to be more restrict, use volatile to cacheCustomerNameSet.
            mapperCreatedDateInMillis = currentTimeMillis;
            List<String> customerDtoList = customerMapper.selectNameAll();
            cacheCustomerNameSet = new HashSet<>(customerDtoList);
        }

        LOGGER.info("cacheAgeInMillis: " + cacheAgeInMillis + ", Hash of cacheCustomerNameSet: " + cacheCustomerNameSet.hashCode());

        return cacheCustomerNameSet;
    }
}
