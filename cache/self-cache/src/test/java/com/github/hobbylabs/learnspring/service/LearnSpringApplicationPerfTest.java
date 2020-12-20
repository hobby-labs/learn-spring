package com.github.hobbylabs.learnspring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

@ExtendWith({ MockitoExtension.class, SpringExtension.class })
@SpringBootTest
@ActiveProfiles("test-perf")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class LearnSpringApplicationPerfTest {

    // This test requires to be running mysql server on the local machine.
    // Run "docker-compose up" then run this test cases.

    @Autowired
    private LearnSpringService learnSpringService;

    @Test
    @DisplayName(value="validateCustomers() should be faster then validateCustomersWithList()")
    public void test0001() {
        List<String> customers = LearnSpringServiceTest.createList();

        long durationOfPerfTestMillis = 60000L;
        long validationCount = 0L;
        long startTime = 0L;

        startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() < (startTime + durationOfPerfTestMillis)) {
            learnSpringService.validateCustomersWithList(customers);
            ++validationCount;
        }
        System.out.println("Perf result of validateCustomersWithList() -> Duration: " + durationOfPerfTestMillis + " ms, validationCount: " + validationCount);

        startTime = System.currentTimeMillis();
        validationCount = 0L;
        while (System.currentTimeMillis() < (startTime + durationOfPerfTestMillis)) {
            learnSpringService.validateCustomers(customers);
            ++validationCount;
        }
        System.out.println("Perf result of validateCustomers() -> Duration: " + durationOfPerfTestMillis + " ms, validationCount: " + validationCount);

        learnSpringService.validateCustomersWithList(customers);
    }

    @Test
    @DisplayName(value="validateCustomers() should be faster then validateCustomersWithList() in multi threaded environment")
    public void test0003() {
        List<String> customers = LearnSpringServiceTest.createList();
        long duration = 60000L;
        int numOfThread = 6;
        long count = 0L;

        System.out.println("Start perfValidatorWithList() ...");
        count = perfValidatorWithList(learnSpringService, customers, System.currentTimeMillis(), duration, numOfThread);
        System.out.println(
                "Result of perfValidatorWithList() -> Duration: "
                + duration + ", numOfThreads: " + numOfThread + ", count: " + count);

        System.out.println("Start perfValidatorWithSet() ...");
        count = perfValidatorWithSet(learnSpringService, customers, System.currentTimeMillis(), duration, numOfThread);
        System.out.println(
                "Result of perfValidatorWithSet() -> Duration: "
                + duration + ", numOfThreads: " + numOfThread + ", count: " + count);
    }

    public long perfValidatorWithList(
            LearnSpringService service, List<String> customers, long startTime, long duration, int numOfThreads) {
        return perfValidator(
                service, customers, duration,
                IntStream.range(0, numOfThreads).mapToObj(
                        i -> new ValidatorWithList(service, customers, startTime, duration)).toArray(Validator[]::new)
        );
    }

    public long perfValidatorWithSet(
            LearnSpringService service, List<String> customers, long startTime, long duration, int numOfThreads) {
        return perfValidator(
                service, customers, duration,
                IntStream.range(0, numOfThreads).mapToObj(
                        i -> new ValidatorWithSet(service, customers, startTime, duration)).toArray(Validator[]::new)
        );
    }

    public long perfValidator(
            LearnSpringService service, List<String> customers, long duration, Validator[] validators) {
        long result = 0L;

        for (int i = 0; i < validators.length; i++) {
            validators[i].start();
        }
        for (int i = 0; i < validators.length; i++) {
            try { validators[i].join(); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        for (int i = 0; i < validators.length; i++) {
            result += validators[i].getCount();
        }

        return result;
    }

    static abstract class Validator extends Thread {
        protected long count;

        protected LearnSpringService service;
        protected List<String> customers;
        protected long startTime;
        protected long duration;

        public Validator(LearnSpringService service, List<String> customers, long startTime, long duration) {
            this.service = service;
            this.customers = customers;
            this.startTime = startTime;
            this.duration = duration;
        }

        public long getCount() {
            return count;
        }
    }

    static class ValidatorWithSet extends Validator {
        public ValidatorWithSet(LearnSpringService service, List<String> customers, long startTime, long duration) {
            super(service, customers, startTime, duration);
        }

        public void run() {
            while (System.currentTimeMillis() < (startTime + duration)) {
                service.validateCustomers(customers);
                ++count;
            }
        }
    }

    static class ValidatorWithList extends Validator {
        public ValidatorWithList(LearnSpringService service, List<String> customers, long startTime, long duration) {
            super(service, customers, startTime, duration);
        }

        public void run() {
            while (System.currentTimeMillis() < (startTime + duration)) {
                service.validateCustomersWithList(customers);
                ++count;
            }
        }
    }
}
