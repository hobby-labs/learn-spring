package com.github.hobbylabs.learnspring.service;

import com.github.hobbylabs.learnspring.mapper.CustomerMapper;
import org.junit.jupiter.api.DisplayName;
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

//@SpringBootTest(properties = { "com.github.hobbylabs.learnspring.service.LearnSpringService.cacheAgeInMillis=3000" })

@ExtendWith(MockitoExtension.class)
@SpringBootTest(properties = { "com.github.hobbylabs.learnspring.service.LearnSpringService.cacheAgeInMillis=3000" })
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class LearnSpringServiceTest {

    @InjectMocks
    private LearnSpringService learnSpringService;
    @Mock
    private CustomerMapper customerMapper;

    @Test
    @DisplayName(value="getCustomerMapper() should returns Set<String> that was filled by names")
    public void testGetCustomerMapperShouldReturnsSetStringThatWasFilledByNames() {
        Mockito.when(customerMapper.selectNameAll()).thenReturn(createList());

        Set<String> set = learnSpringService.getCustomerMapper();

        // assertions
        assertThat(verifySet(set));
        Mockito.verify(customerMapper, times(1)).selectNameAll();
    }

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
