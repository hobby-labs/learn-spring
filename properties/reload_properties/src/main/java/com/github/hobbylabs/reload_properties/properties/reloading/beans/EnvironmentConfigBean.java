package com.github.hobbylabs.reload_properties.properties.reloading.beans;

import com.github.hobbylabs.reload_properties.properties.reloading.configs.ReloadablePropertySourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:application.properties", factory = ReloadablePropertySourceFactory.class)
public class EnvironmentConfigBean {

    private Environment environment;

    public EnvironmentConfigBean(@Autowired Environment environment) {
        this.environment = environment;
    }

    public String getFoo() {
        return environment.getProperty("test.message");
    }
}