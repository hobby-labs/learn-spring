package com.github.hobbylabs.ldapsample;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class LdapSampleApplication {

	@Bean
	@ConditionalOnProperty(name = "spring.config.location", matchIfMissing = false)
	public PropertiesConfiguration propertiesConfiguration(
			@Value("${spring.config.location}") String path,
			@Value("${spring.properties.refreshDelay}") long refreshDelay) throws Exception {
		String filePath = path.substring("file:".length());
		PropertiesConfiguration configuration = new PropertiesConfiguration(new File(filePath).getCanonicalPath());
		FileChangedReloadingStrategy fileChangedReloadingStrategy = new FileChangedReloadingStrategy();
		fileChangedReloadingStrategy.setRefreshDelay(refreshDelay);
		configuration.setReloadingStrategy(fileChangedReloadingStrategy);
		return configuration;
	}

	public static void main(String[] args) {
		SpringApplication.run(LdapSampleApplication.class, args);
	}

}
