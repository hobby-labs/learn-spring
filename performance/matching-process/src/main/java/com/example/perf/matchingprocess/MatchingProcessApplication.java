package com.example.perf.matchingprocess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class MatchingProcessApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatchingProcessApplication.class, args);
	}

}
