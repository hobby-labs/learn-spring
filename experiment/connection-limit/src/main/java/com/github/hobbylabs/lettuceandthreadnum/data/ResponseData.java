package com.github.hobbylabs.lettuceandthreadnum.data;

import lombok.Data;

@Data
public class ResponseData {
    private final int activeThreadNum;
    private final String redisData;
    private final String message;
}
