package com.github.hobbylabs.ldapsample.properties.reloading.beans;

import com.github.hobbylabs.ldapsample.properties.reloading.configs.ReloadablePropertySourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "file:application.properties", factory = ReloadablePropertySourceFactory.class)
public class EnvironmentConfigBean {

    private Environment environment;

    public EnvironmentConfigBean(@Autowired Environment environment) {
        this.environment = environment;
    }

    public String getUrl() {
        return environment.getProperty("sample.ldap.url");
    }
    public String getBindDn() {
        return environment.getProperty("sample.ldap.bindDn");
    }
    public String getPassword() {
        return environment.getProperty("sample.ldap.password");
    }
}