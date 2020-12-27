package com.github.hobbylabs.learnspring.service;

import com.github.hobbylabs.learnspring.data.Product;
import com.github.hobbylabs.learnspring.data.Store;
import com.github.hobbylabs.learnspring.data.Stores;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class LearnSpringService {

    public Stores getAllStores() {
        Stores stores               = new Stores();
        Store store                 = null;
        List<Store> storesList      = new ArrayList<>();
        //Product product             = null;
        List<Product> productsList  = null;

        stores.setId(1);

        // Create store1 and its products
        productsList = new ArrayList<>();
        productsList.add(Product.createInstance("Orange", 6, "It's a delicious orange"));
        productsList.add(Product.createInstance("Grape", 10, "It's a delicious grape"));
        storesList.add(Store.createStore(1, "Taro's fruits shop", productsList));

        // Create store2 and its products
        productsList = new ArrayList<>();
        productsList.add(Product.createInstance("Radish", 10, "It's a delicious radish"));
        productsList.add(Product.createInstance("Green pepper", 8, "It's a delicious green pepper"));
        storesList.add(Store.createStore(2, "Hanako's vegetable shop", productsList));

        stores.setStores(storesList);

        return stores;
    }
}
