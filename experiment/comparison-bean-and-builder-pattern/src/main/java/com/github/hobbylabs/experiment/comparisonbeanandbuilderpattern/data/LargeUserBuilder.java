package com.github.hobbylabs.experiment.comparisonbeanandbuilderpattern.data;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LargeUserBuilder {
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
}
