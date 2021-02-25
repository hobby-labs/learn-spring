package com.github.hobbylabs.learnspring.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

@ExtendWith({ MockitoExtension.class, SpringExtension.class })
@SpringBootTest
public class TestLearnspringService {

    @Autowired
    @InjectMocks
    private LearnspringService learnSpringService;

    @Test
    public void testFoo() throws IOException, InterruptedException {
        learnSpringService.exec();
    }
}
