package com.github.hobbylabs.ldapsample.data.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.Data;

@Data
public class Query {
    @JsonProperty("search")
    private Search search;
}
