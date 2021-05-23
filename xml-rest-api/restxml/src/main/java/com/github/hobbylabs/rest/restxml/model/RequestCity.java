package com.github.hobbylabs.rest.restxml.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement
public class RequestCity {
    @JacksonXmlProperty(localName = "Name")
    private String name;
}
