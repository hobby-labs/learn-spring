package com.github.hobbylabs.rest.restxml.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@JacksonXmlRootElement(localName = "Error")
public class ErrorResponse {
    @JacksonXmlProperty(localName = "Code")
    private HttpStatus code;

    @JacksonXmlProperty(localName = "Message")
    private String message;
}
