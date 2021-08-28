package com.github.hobbylabs.reload_properties.services;

import com.github.hobbylabs.reload_properties.properties.reloading.beans.EnvironmentConfigBean;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReloadPropertiesService {
    @Value("${test.message}")
    private String testMessage;

    @Autowired
    private EnvironmentConfigBean environmentConfigBean;

    public void getReloadProperties() {
        log.info("(test.message from EnvironmentConfigBean)=\""
                + environmentConfigBean.getFoo() + "\", (test.message from @Value)=\"" + testMessage + "\"");
    }
}
