package com.github.hobbylabs.learnspring.data;

import java.util.List;
import lombok.Data;

@Data
public class Store {
    /** Store ID */
    private int id;
    /** Store name */
    private String name;
    /** Products stoked by the store */
    private List<Product> products;

    /**
     * Create Store instance and return it.
     * @param id Id of the Store
     * @param name Name of the Store
     * @param products Products of the Store
     * @return Instance of Store
     */
    public static Store createStore(int id, String name, List<Product> products) {
        Store store = new Store();
        store.setId(id);
        store.setName(name);
        store.setProducts(products);

        return store;
    }
}
