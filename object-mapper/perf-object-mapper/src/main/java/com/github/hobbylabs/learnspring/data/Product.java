package com.github.hobbylabs.learnspring.data;

import lombok.Data;

@Data
public class Product {
    /** Product name */
    private String name;
    /** Quantity of the product */
    private int quantity;
    /** Memo */
    private String memo;

    /**
     * Create Product instance and return it.
     * @param name Name of the Product
     * @param quantity Quantity of the product
     * @param memo Memo
     * @return Instance of the Product
     */
    public static Product createInstance(String name, int quantity, String memo) {
        Product product = new Product();
        product.setName(name);
        product.setQuantity(quantity);
        product.setMemo(memo);

        return product;
    }
}
