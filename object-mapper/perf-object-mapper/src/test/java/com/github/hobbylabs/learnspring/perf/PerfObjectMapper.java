package com.github.hobbylabs.learnspring.perf;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hobbylabs.learnspring.data.Product;
import com.github.hobbylabs.learnspring.data.Store;
import com.github.hobbylabs.learnspring.data.Stores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PerfObjectMapper {
    @Test
    @DisplayName(value = "Compare the performance of writeValueAsString between its data size")
    public void test0001() throws JsonProcessingException {
        perfWriteValueAsString(1, 1);
        perfWriteValueAsString(1, 1);
        perfWriteValueAsString(1, 10);
        perfWriteValueAsString(126, 100);
        perfWriteValueAsStringWithCreateObjectMapperEachTime(126, 100);
    }

    public void perfWriteValueAsString(int storeCount, int productsCountPerStore) throws JsonProcessingException {
        Stores stores = createStores(storeCount, productsCountPerStore);
        ObjectMapper mapper = new ObjectMapper();
        String resultJson = null;

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            resultJson = mapper.writeValueAsString(stores);
        }

        System.out.println("Time consuming result: storeCount="
                + storeCount + ", productsCountPerStore=" + productsCountPerStore
                + ", Size of the JSON=" + String.format("%,d", resultJson.getBytes().length)
                + " bytes -> " + (System.currentTimeMillis() - start) + " ms");
    }

    public void perfWriteValueAsStringWithCreateObjectMapperEachTime(int storeCount, int productsCountPerStore) throws JsonProcessingException {
        Stores stores = createStores(storeCount, productsCountPerStore);
        String resultJson = null;

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            ObjectMapper mapper = new ObjectMapper();
            resultJson = mapper.writeValueAsString(stores);
        }

        System.out.println("Time consuming result (with creating ObjectMapper each time): storeCount="
                + storeCount + ", productsCountPerStore=" + productsCountPerStore
                + ", Size of the JSON=" + String.format("%,d", resultJson.getBytes().length)
                + " bytes -> " + (System.currentTimeMillis() - start) + " ms");
    }

    /**
     * Create Stores
     * @param storeCount Num of stores
     * @return Instance of Stores
     */
    public Stores createStores(int storeCount, int productsCountPerStore) {
        Stores stores           = new Stores();
        List<Store> storesList  = new ArrayList<>();

        stores.setId(1);

        // Create stores
        for (int i = 0; i < storeCount; i++) {
            storesList.add(Store.createStore(i, "Stores " + i, createProducts(productsCountPerStore)));
        }
        stores.setStores(storesList);

        return stores;
    }

    /**
     * Create Products list
     * @param productCount Num of Products
     * @return Instance of Products list
     */
    public List<Product> createProducts(int productCount) {
        List<Product> productsList = new ArrayList<>();
        Product product = null;

        for (int i = 0; i < productCount; i++) {
            productsList.add(Product.createInstance(
                    "Product " + i, i + 1, "This is a memo of Product " + 1));
        }

        return productsList;
    }
}
