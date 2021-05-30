package com.github.hobbylabs.experiment.comparisonbeanandbuilderpattern.data;

import lombok.Data;

import java.util.List;

@Data
public class LargeUserImmutableBean2 {
    private final String firstName;
    private final List<String> hobbies;
}
