package com.github.hobbylabs.experiment.comparisonbeanandbuilderpattern.data;

import lombok.Data;

@Data
public class LargeUserCustomBean {
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

    public static LargeUserCustomBean init(
            LargeUserCustomBean instance,
            String firstName,    String lastName,     String fullName,
            Integer age,         String country,      String address,
            String mailAddress,  String phoneNumber,  Integer height,
            Integer weight
    ) {
        instance.firstName = firstName;
        instance.lastName = lastName;
        instance.fullName = fullName;
        instance.age = age;
        instance.country = country;
        instance.address = address;
        instance.mailAddress = mailAddress;
        instance.phoneNumber = phoneNumber;
        instance.height = height;
        instance.weight = weight;
        return instance;
    }
}
