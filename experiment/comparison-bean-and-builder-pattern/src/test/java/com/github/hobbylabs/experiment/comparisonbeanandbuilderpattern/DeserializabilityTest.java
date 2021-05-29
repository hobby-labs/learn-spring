package com.github.hobbylabs.experiment.comparisonbeanandbuilderpattern;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.github.hobbylabs.experiment.comparisonbeanandbuilderpattern.data.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.platform.commons.function.Try.success;

public class DeserializabilityTest {
    private static final String sampleData = "{"
                + "\"firstName\": \"Taro\","
                + "\"lastName\": \"Suzuki\","
                + "\"fullName\": \"Taro Suzuki\","
                + "\"age\": \"20\","
                + "\"country\": \"JP\","
                + "\"address\": \"Foo Bar Street\","
                + "\"mailAddress\": \"taro-suzuki@example.com\","
                + "\"phoneNumber\": \"000-000-0000\","
                + "\"height\": 170,"
                + "\"weight\": 65"
            + "}";

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Bean should deserialize JSON string")
    public void test0001() throws JsonProcessingException {
        LargeUserBean bean = mapper.readValue(sampleData, LargeUserBean.class);

        assertEquals("Taro", bean.getFirstName());
        assertEquals("Suzuki", bean.getLastName());
        assertEquals("Taro Suzuki", bean.getFullName());
        assertEquals(20, bean.getAge());
        assertEquals("JP", bean.getCountry());
        assertEquals("Foo Bar Street", bean.getAddress());
        assertEquals("taro-suzuki@example.com", bean.getMailAddress());
        assertEquals("000-000-0000", bean.getPhoneNumber());
        assertEquals(170, bean.getHeight());
        assertEquals(65, bean.getWeight());
    }

    @Test
    @DisplayName("LargeUserBuilder can not be deserialize JSON string")
    public void test0002() throws JsonProcessingException {
        try {
            LargeUserBuilder bean = mapper.readValue(sampleData, LargeUserBuilder.class);
        } catch (InvalidDefinitionException e) {
            success("LargeUserBuilder can not deserialize JSON string");
        }
    }

    @Test
    @DisplayName("LargeUserImmutableBean can not be deserialize JSON string")
    public void test0003() throws JsonProcessingException {
        try {
            LargeUserImmutableBean bean = mapper.readValue(sampleData, LargeUserImmutableBean.class);
        } catch (InvalidDefinitionException e) {
            success("LargeUserImmutableBean can not deserialize JSON string");
        }
    }

    @Test
    @DisplayName("LargeUserCustomBean should deserialize JSON string")
    public void test0004() throws JsonProcessingException {
        LargeUserCustomBean bean = mapper.readValue(sampleData, LargeUserCustomBean.class);

        assertEquals("Taro", bean.getFirstName());
        assertEquals("Suzuki", bean.getLastName());
        assertEquals("Taro Suzuki", bean.getFullName());
        assertEquals(20, bean.getAge());
        assertEquals("JP", bean.getCountry());
        assertEquals("Foo Bar Street", bean.getAddress());
        assertEquals("taro-suzuki@example.com", bean.getMailAddress());
        assertEquals("000-000-0000", bean.getPhoneNumber());
        assertEquals(170, bean.getHeight());
        assertEquals(65, bean.getWeight());
    }

    @Test
    @DisplayName("LargeUserBuilderDeserializable should deserialize JSON string")
    public void test0005() throws JsonProcessingException {
        LargeUserBuilderDeserializable bean = mapper.readValue(sampleData, LargeUserBuilderDeserializable.class);

        assertEquals("Taro", bean.getFirstName());
        assertEquals("Suzuki", bean.getLastName());
        assertEquals("Taro Suzuki", bean.getFullName());
        assertEquals(20, bean.getAge());
        assertEquals("JP", bean.getCountry());
        assertEquals("Foo Bar Street", bean.getAddress());
        assertEquals("taro-suzuki@example.com", bean.getMailAddress());
        assertEquals("000-000-0000", bean.getPhoneNumber());
        assertEquals(170, bean.getHeight());
        assertEquals(65, bean.getWeight());
    }
}
