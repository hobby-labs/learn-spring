package com.example.perf.matchingprocess.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({ MockitoExtension.class, SpringExtension.class })
@SpringBootTest
class MatchingProcessApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void perfMappingProcess() {
		assertTrue(true);
	}
}
