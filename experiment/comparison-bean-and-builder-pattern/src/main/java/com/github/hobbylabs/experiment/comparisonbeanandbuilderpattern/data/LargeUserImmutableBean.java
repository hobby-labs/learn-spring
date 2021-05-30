package com.github.hobbylabs.experiment.comparisonbeanandbuilderpattern.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
public class LargeUserImmutableBean {
    private final String firstName;
    private final String lastName;
    private final String fullName;
    private final Integer age;
    private final String country;
    private final String address;
    private final String mailAddress;
    private final String phoneNumber;
    private final Integer height;
    private final Integer weight;
}
