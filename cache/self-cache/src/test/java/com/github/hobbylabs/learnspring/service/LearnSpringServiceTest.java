package com.github.hobbylabs.learnspring.service;

import com.github.hobbylabs.learnspring.mapper.CustomerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

//@ExtendWith(SpringExtension.class)
////@MybatisTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})

@ExtendWith(MockitoExtension.class)
public class LearnSpringServiceTest {

    @InjectMocks
    private LearnSpringService learnSpringService;
    @Mock
    private CustomerMapper mapper;

    @Test
    public void testGetCustomerMapperShouldReturnSetOfNames() {
        Mockito.when(mapper.selectNameAll()).thenReturn(createList());

        Set<String> set = learnSpringService.getCustomerMapper();

        // assertions
        assertEquals(200, set.size());
        for(int i = 0; i < 200; i++) { assertThat(set.contains("Name " + i)); }
        Mockito.verify(mapper, times(1)).selectNameAll();
    }

    @Test
    public void testGetCustomerMapperShouldReturnSetOfCacheIfTheTimeOfCacheWasValid() {
        Mockito.when(mapper.selectNameAll()).thenReturn(createList());

        Set<String> set = learnSpringService.getCustomerMapper();
        int firstHash = set.hashCode();
        int secondHash = set.hashCode();

        assertThat(firstHash == secondHash);
    }

    private List<String> createList() {
        List<String> result = new ArrayList<>();

        for(int i = 0; i < 200; i++) {
            result.add("Name " + i);
        }

        return result;
    }
}
