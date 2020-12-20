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
    public void test0002() {
        int numOfThread = 4;

        ValidatorWithList[] validatorWithListsThreads   = new ValidatorWithList[numOfThread];
        Validator[] validatorThreads                    = new Validator[numOfThread];

        long resultCountValidatorWithLists              = 0L;
        long resultCountValidator                       = 0L;

        List<String> customers = LearnSpringServiceTest.createList();
        long durationOfPerfTestMillis = 60000L;
        long startTime = 0L;

        // Perf with ValidatorWithList
        System.out.println("Perf with ValidatorWithList");
        startTime = System.currentTimeMillis();
        for (int i = 0; i < validatorWithListsThreads.length; i++) {
            validatorWithListsThreads[i] = new ValidatorWithList(learnSpringService, customers, startTime, durationOfPerfTestMillis);
        }
        for (int i = 0; i < validatorWithListsThreads.length; i++) {
            validatorWithListsThreads[i].start();
        }
        for (int i = 0; i < validatorWithListsThreads.length; i++) {
            try { validatorWithListsThreads[i].join(); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        for (int i = 0; i < validatorWithListsThreads.length; i++) {
            resultCountValidatorWithLists += validatorWithListsThreads[i].getCount();
        }
        System.out.println("Perf result of validateCustomersWithList() -> Threads: " + numOfThread
                + ", Duration: " + durationOfPerfTestMillis + " ms, validationCount: " + resultCountValidatorWithLists);

        // Perf with Validator
        System.out.println("Perf with Validator");
        startTime = System.currentTimeMillis();
        for (int i = 0; i < validatorThreads.length; i++) {
            validatorThreads[i] = new Validator(learnSpringService, customers, startTime, durationOfPerfTestMillis);
        }
        for (int i = 0; i < validatorThreads.length; i++) {
            validatorThreads[i].start();
        }
        for (int i = 0; i < validatorThreads.length; i++) {
            try { validatorThreads[i].join(); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        for (int i = 0; i < validatorThreads.length; i++) {
            resultCountValidator += validatorThreads[i].getCount();
        }
        System.out.println("Perf result of validateCustomers() -> Threads: " + numOfThread
                + ", Duration: " + durationOfPerfTestMillis + " ms, validationCount: " + resultCountValidator);
    }


    static class Validator extends Thread {

        private long count;

        private LearnSpringService service;
        private List<String> customers;
        private long startTime;
        private long duration;

        public Validator(LearnSpringService service, List<String> customers, long startTime, long duration) {
            this.service = service;
            this.customers = customers;
            this.startTime = startTime;
            this.duration = duration;
        }

        public void run() {
            while (System.currentTimeMillis() < (startTime + duration)) {

                service.validateCustomers(customers);
                ++count;
            }
        }

        public long getCount() {
            return count;
        }
    }

    static class ValidatorWithList extends Thread {

        private long count;

        private LearnSpringService service;
        private List<String> customers;
        private long startTime;
        private long duration;

        public ValidatorWithList(LearnSpringService service, List<String> customers, long startTime, long duration) {
            this.service = service;
            this.customers = customers;
            this.startTime = startTime;
            this.duration = duration;
        }

        public void run() {
            while (System.currentTimeMillis() < (startTime + duration)) {
                service.validateCustomersWithList(customers);
                ++count;
            }
        }

        public long getCount() {
            return count;
        }
    }
}
