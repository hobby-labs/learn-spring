package com.github.hobbylabs.learnspring.service;

import com.github.hobbylabs.learnspring.mapper.CustomerMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

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

// @SpringBootTest(properties = { "com.github.hobbylabs.learnspring.service.LearnSpringService.cacheAgeInMillis=3000" })

@ExtendWith(MockitoExtension.class)
@SpringBootTest
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class LearnSpringServiceTest {

    @InjectMocks
    private LearnSpringService learnSpringService;
    @Mock
    private CustomerMapper customerMapper;

    @Test
    public void testGetCustomerMapperShouldReturnSetOfNames() {
        Mockito.when(customerMapper.selectNameAll()).thenReturn(createList());

        Set<String> set = learnSpringService.getCustomerMapper();

        // assertions
        assertEquals(200, set.size());
        for(int i = 0; i < 200; i++) { assertThat(set.contains("Name " + i)); }
        Mockito.verify(customerMapper, times(1)).selectNameAll();
        assertThat(true);
    }

//    @Test
//    public void testGetCustomerMapperShouldReturnSetOfCacheIfTheTimeOfCacheWasValid() {
//        Mockito.when(mapper.selectNameAll()).thenReturn(createList());
//
//        List<String> data = createList();
//        Set<String> set = learnSpringService.getCustomerMapper();
//
//        int firstHash = learnSpringService.getCustomerMapper().hashCode();
//        int secondHash = learnSpringService.getCustomerMapper().hashCode();
//
//        assertThat(firstHash == secondHash);
//        Mockito.verify(mapper, times(1)).selectNameAll();
//    }
//
//    @Test
//    public void testPerfoamance() {
//        Mockito.when(mapper.selectNameAll()).thenReturn(createList());
//
//        List<String> data = createList();
//        Set<String> set = learnSpringService.getCustomerMapper();
//
//        int firstHash = set.hashCode();
//        for(int i = 0; i < 1000; i++) {
//            set = learnSpringService.getCustomerMapper();
//            for(String s: data) {
//                set.contains(s);
//            }
//        }
//
//        int secondHash = set.hashCode();
//
//        assertThat(firstHash == secondHash);
//        Mockito.verify(mapper, times(1)).selectNameAll();
//    }
//
//    @Test
//    public void testGetCustomerMapperShouldReturnNewSetIfTheTimeOfCacheWasNotValid() {
//        Mockito.when(mapper.selectNameAll()).thenReturn(createList());
//
//        Set<String> set = learnSpringService.getCustomerMapper();
//        int firstHash = set.hashCode();
//
//    }
//

    /**
     * Create dummy list for testing.
     * @return List of the names
     */
    private List<String> createList() {
        List<String> result = new ArrayList<>();

        for(int i = 0; i < 200; i++) {
            result.add("Name " + i);
        }

        return result;
    }
}
