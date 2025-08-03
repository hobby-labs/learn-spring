package com.github.hobbylabs.ldapsample.data.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestQueryRoot {
    @JsonProperty("instruction")
    private Query query;
}
