package com.github.hobbylabs.spring.example.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    /** Name of the product */
    private String name;
    /** Price of the product */
    private long price;
}
