package com.github.hobbylabs.learnspring.data;

import lombok.Data;
import java.util.List;

@Data
public class Stores {
    /** Stores ID */
    private int id;
    /** Stores */
    private List<Store> stores;
}
