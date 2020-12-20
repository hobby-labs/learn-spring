package com.github.hobbylabs.learnspring.service;

import com.github.hobbylabs.learnspring.mapper.CustomerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

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

//@SpringBootTest(properties = { "com.github.hobbylabs.learnspring.service.LearnSpringService.cacheAgeInMillis=3000" })

@ExtendWith({ MockitoExtension.class, SpringExtension.class })
//@TestPropertySource(locations = { "classpath:application.yml" })
//@TestPropertySource(locations = { "classpath:application.yml" })
//@ActiveProfiles("test")
//@SpringBootTest(properties = { "com.github.hobbylabs.learnspring.service.LearnSpringService.cacheAgeInMillis=2000" })
//@ContextConfiguration(classes = LearnSpringService.class)
@TestPropertySource(properties="com.github.hobbylabs.learnspring.service.LearnSpringService.cacheAgeInMillis=2000")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class LearnSpringServiceTest {

//    @Autowired
//    private LearnSpringService learnSpringService;
//    @Mock
//    private CustomerMapper customerMapper;

    @Mock
    private CustomerMapper customerMapper;

    private LearnSpringService learnSpringService;

    @BeforeEach
    public void setUp() {
        this.learnSpringService = new LearnSpringService(customerMapper, 2000L);
    }

    @Test
    @DisplayName(value="getCustomerMapper() should returns Set<String> that was filled by names")
    public void testGetCustomerMapperShouldReturnsSetStringThatWasFilledByNames() {
        Set<String> mapper = learnSpringService.getCustomerMapper();
    }

//    @Test
//    @DisplayName(value="getCustomerMapper() should returns Set<String> that was filled by names")
//    public void testGetCustomerMapperShouldReturnsSetStringThatWasFilledByNames() {
////        Mockito.when(customerMapper.selectNameAll()).thenReturn(createList());
//
//        Set<String> set = learnSpringService.getCustomerMapper();
//
//        // assertions
//        assertThat(verifySet(set));
////        Mockito.verify(customerMapper, times(1)).selectNameAll();
//    }

    @Test
    @DisplayName(value="getCustomerMapper() should returns same instance of Set<String> if the time of cache was NOT expired")
    public void testGetCustomerMapperShouldReturnsSameInstanceOfSetStringIfTheTimeOfCacheWasNotExpired() {
//        Mockito.when(customerMapper.selectNameAll()).thenReturn(createList());

        List<String> data = createList();
        Set<String> set = learnSpringService.getCustomerMapper();

        int firstHash = learnSpringService.getCustomerMapper().hashCode();
        int secondHash = learnSpringService.getCustomerMapper().hashCode();

        assertThat(firstHash == secondHash);
//        Mockito.verify(customerMapper, times(1)).selectNameAll();
    }

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
     * Verify the Set interface whether it was configured properly or not.
     * @param set Set that was returnd Service class.
     * @return result of the verification.
     */
    public boolean verifySet(Set<String> set) {
        if (set.size() != 200) {
            System.err.println("Set was not the appropriate size that was expected. (Expected size = " + 200 + ")");
            return false;
        }

        for(int i = 0; i < 200; i++) {
            if (!set.contains("Name " + i)) {
                System.err.println("Could not be found \"Name " + i + "\" in the Set");
                return false;
            }
        }

        return true;
    }

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
