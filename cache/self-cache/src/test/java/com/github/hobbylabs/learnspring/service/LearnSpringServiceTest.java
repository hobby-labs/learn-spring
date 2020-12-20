package com.github.hobbylabs.learnspring.service;

import com.github.hobbylabs.learnspring.mapper.CustomerMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;

@ExtendWith({ MockitoExtension.class, SpringExtension.class })
//@SpringBootTest(properties = { "com.github.hobbylabs.learnspring.service.LearnSpringService.cacheAgeInMillis=1499" })  // This will set a specific property on the fly
@SpringBootTest
@ActiveProfiles("test")  // This loads application-test.yml in the directory /test/java/resources.
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class LearnSpringServiceTest {

    @Autowired
    @InjectMocks
    private LearnSpringService learnSpringService;

    @Mock
    private CustomerMapper customerMapper;

    @Test
    @DisplayName(value="getCustomerSetCache() should returns Set<String> that was filled by names")
    public void test0001() {
        Mockito.when(customerMapper.selectNameAll()).thenReturn(createList());

        Set<String> customerSet = learnSpringService.getCustomerSetCache();
        assertThat(verifyCustomerSet(customerSet));
        Mockito.verify(customerMapper, times(1)).selectNameAll();
    }

    @Test
    @DisplayName(value="getCustomerSetCache() should returns same instance of Set<String> if the time of cache was NOT expired")
    public void test0002() {
        Mockito.when(customerMapper.selectNameAll()).thenReturn(createList());

        int firstHash = learnSpringService.getCustomerSetCache().hashCode();
        int secondHash = learnSpringService.getCustomerSetCache().hashCode();

        assertThat(firstHash == secondHash);
        Mockito.verify(customerMapper, times(1)).selectNameAll();
    }

    @Test
    @DisplayName(value="getCustomerSetCache() should returns different instance of Set<String> if the time of cache was expired")
    public void test0003() {
        Mockito.when(customerMapper.selectNameAll()).thenReturn(createList());

        int firstHash = learnSpringService.getCustomerSetCache().hashCode();
        try { Thread.sleep(1500L); } catch (Exception e) { e.printStackTrace(); }
        int secondHash = learnSpringService.getCustomerSetCache().hashCode();

        assertThat(firstHash != secondHash);
        Mockito.verify(customerMapper, times(2)).selectNameAll();
    }

    /**
     * Verify the Set interface whether it was configured properly or not.
     * @param set Set that was returnd Service class.
     * @return result of the verification.
     */
    public static boolean verifyCustomerSet(Set<String> customerSet) {
        for(int i = 0; i < 200; i++) {
            if (!customerSet.contains("Name " + i)) {
                System.err.println("Could not be found \"Name " + i + "\" in the Set");
                return false;
            }
        }

        return true;
    }
    public static boolean verifyCustomerList(List<String> customerList) {
        for(int i = 0; i < 200; i++) {
            if (!customerList.contains("Name " + i)) {
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
    public static List<String> createList() {
        List<String> result = new ArrayList<>();

        //for(int i = 0; i < 200; i++) {
        for(int i = 0; i < 1000; i++) {
            result.add("Name " + i);
        }

        return result;
    }
}
