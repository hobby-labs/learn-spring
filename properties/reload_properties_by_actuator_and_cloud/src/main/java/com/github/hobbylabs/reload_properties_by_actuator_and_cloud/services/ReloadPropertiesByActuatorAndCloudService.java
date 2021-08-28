package com.github.hobbylabs.reload_properties_by_actuator_and_cloud.services;

import com.github.hobbylabs.reload_properties_by_actuator_and_cloud.properties.reloading.beans.ConfigurationPropertiesRefreshConfigBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RefreshScope
public class ReloadPropertiesByActuatorAndCloudService {

    private String message;

    public ReloadPropertiesByActuatorAndCloudService(@Value("${application.test.message}") String message) {
        this.message = message;
    }

    public void getReloadProperties() {
        log.info("application.test.message=\"" + message + "\"");
    }
}
