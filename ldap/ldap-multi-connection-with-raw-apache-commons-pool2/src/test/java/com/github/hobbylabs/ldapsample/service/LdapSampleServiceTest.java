package com.github.hobbylabs.ldapsample.service;

import com.github.hobbylabs.ldapsample.data.request.RequestQueryRoot;
import com.github.hobbylabs.ldapsample.data.request.Search;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class LdapSampleServiceTest {

    @Autowired
    private LdapSampleService ldapSampleService;
    @Test
    public void testPerformance() throws IOException, InterruptedException {
        List<Search> searchList = createDummySearchList();

        //https://stackify.com/heres-how-to-calculate-elapsed-time-in-java/

        for(int i = 0; i < 20; i++) {
            long start = System.nanoTime();
            for(int j = 0; j < 10000; j++) {
                ldapSampleService.getLdapDataBySearchObject(searchList.get(0));
            }
            long end = System.nanoTime();
            System.out.println("Round: " + i + ", Elapsed time: " + String.format("%,d", end - start) + " nano sec");
        }

    }

    public List<Search> createDummySearchList() {
        List<Search> searchList = new ArrayList<>();
        Search s = new Search();
        s.setCn("taro");
        s.setMail("taro-suzuki@mysite.example.com");
        searchList.add(s);
        s = new Search();
        s.setCn("hanako");
        s.setMail("hanako-suzuki@mysite.example.com");
        searchList.add(s);

        return searchList;
    }
}
