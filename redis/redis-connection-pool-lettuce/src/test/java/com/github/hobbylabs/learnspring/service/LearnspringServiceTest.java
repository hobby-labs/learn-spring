package com.github.hobbylabs.learnspring.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class LearnspringServiceTest {
    @Autowired
    private LearnspringService service;

    @Test
    public void testService() {
        assertThat(true);
    }
}
