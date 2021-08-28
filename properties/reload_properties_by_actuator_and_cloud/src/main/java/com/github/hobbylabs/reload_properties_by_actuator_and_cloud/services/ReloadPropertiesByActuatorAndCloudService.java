package com.github.hobbylabs.reload_properties_by_actuator_and_cloud.services;

import com.github.hobbylabs.reload_properties_by_actuator_and_cloud.properties.reloading.beans.ConfigurationPropertiesRefreshConfigBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReloadPropertiesByActuatorAndCloudService {

    @Autowired
    private ConfigurationPropertiesRefreshConfigBean configurationPropertiesRefreshConfigBean;

    public void getReloadProperties() {
        log.info("test.message=\"" + configurationPropertiesRefreshConfigBean.getMessage() + "\"");
    }
}
