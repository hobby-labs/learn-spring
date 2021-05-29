package com.github.hobbylabs.experiment.comparisonbeanandbuilderpattern;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeserializabilityTest {
    private static final String sampleData = "{"
                + "{\"firstName\": \"Taro\"},"
                + "{\"lastName\": \"Suzuki\"},"
                + "{\"fullName\": \"Taro Suzuki\"},"
                + "{\"age\": \"20\"},"
                + "{\"country\": \"JP\"},"
                + "{\"address\": \"Foo Bar Street\"},"
                + "{\"mailAddress\": \"taro-suzuki@example.com\"},"
                + "{\"phoneNumber\": \"000-000-0000\"},"
                + "{\"height\": 170},"
                + "{\"weight\": 65}"
            + "}";

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Bean should deserialize JSON string")
    public void test0001() {
        
    }
}
