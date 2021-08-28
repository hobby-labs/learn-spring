package com.github.hobbylabs.reload_properties.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ReloadPropertiesService {

    private static Logger logger = LoggerFactory.getLogger(ReloadPropertiesService.class);

    @Value("${test.foo}")
    private String testFoo;

    public void getReloadProperties() {
        logger.info("test.foo=\"" + testFoo + "\"");
    }
}
