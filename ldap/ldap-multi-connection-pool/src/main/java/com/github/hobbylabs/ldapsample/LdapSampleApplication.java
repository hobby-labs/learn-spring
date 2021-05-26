package com.github.hobbylabs.ldapsample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class LdapSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(LdapSampleApplication.class, args);
	}

}
