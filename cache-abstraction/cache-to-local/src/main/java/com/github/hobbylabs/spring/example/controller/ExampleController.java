package com.github.hobbylabs.spring.example.controller;

import com.github.hobbylabs.spring.example.data.Product;
import com.github.hobbylabs.spring.example.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ExampleController {

    @Autowired
    private ExampleService service;

    @RequestMapping(value = {"/getProduct/{name}"}, method = RequestMethod.GET)
    public ResponseEntity<Product> getProduct(@PathVariable String name) {
        Product product = service.getProductByName(name);
        ResponseEntity<Product> response = new ResponseEntity<>(product, HttpStatus.OK);

        return response;
    }


}
