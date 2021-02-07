package com.github.hobbylabs.spring.example.service;

import com.github.hobbylabs.spring.example.data.Product;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class ExampleService {

    private final Map<String, Product> database = new HashMap<>();

    @Cacheable("product")
    public Product getProductByName(String name) {
        System.out.println("getProductByName(): Getting the product from the name " + name);
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return database.get(name);
    }

    @PostConstruct
    public void init() {
        database.put("Apple", new Product("Apple", 100));
        database.put("Orange", new Product("Orange", 80));
    }
}
