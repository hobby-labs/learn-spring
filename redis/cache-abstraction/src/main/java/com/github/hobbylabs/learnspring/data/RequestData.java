package com.github.hobbylabs.learnspring.data;

import lombok.Data;

import java.util.List;

@Data
public class RequestData {
    private List<String> products;
}
