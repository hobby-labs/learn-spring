package com.github.hobbylabs.rest.restxml.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@JacksonXmlRootElement(localName = "Country")
public class RequestCountry {

    @NotNull
    @JacksonXmlProperty(isAttribute = true, localName = "countryName")
    private String countryName;

    @JacksonXmlElementWrapper(localName = "Cities")
    @JacksonXmlProperty(localName = "City")
    private List<RequestCity> requestCities;

    @Size(min=1, max=32)
    @JacksonXmlProperty(localName = "Description")
    private String description;

    @Min(1)
    @Max(3)
    @JacksonXmlProperty(localName = "MaxFetchSize")
    private Integer maxFetchSize;
}
