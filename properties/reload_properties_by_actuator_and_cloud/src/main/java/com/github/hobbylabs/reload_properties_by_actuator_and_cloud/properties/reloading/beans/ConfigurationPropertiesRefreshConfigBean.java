package com.github.hobbylabs.reload_properties_by_actuator_and_cloud.properties.reloading.beans;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application.test")
@RefreshScope
public class ConfigurationPropertiesRefreshConfigBean {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}