package com.github.hobbylabs.experiment.comparisonbeanandbuilderpattern.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

/**
 * This is a bean that deserializable with builder pattern.
 */
@JsonDeserialize(builder = LargeUserBuilderDeserializable.LargeUserBuilderDeserializableBuilder.class)
@Value
@Builder
public class LargeUserBuilderDeserializable {
    private String firstName;
    private String lastName;
    private String fullName;
    private Integer age;
    private String country;
    private String address;
    private String mailAddress;
    private String phoneNumber;
    private Integer height;
    private Integer weight;

    @JsonPOJOBuilder(withPrefix = "")
    public static class LargeUserBuilderDeserializableBuilder {

    }
}
