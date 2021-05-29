package com.github.hobbylabs.experiment.comparisonbeanandbuilderpattern;

import com.github.hobbylabs.experiment.comparisonbeanandbuilderpattern.data.LargeUserBean;
import com.github.hobbylabs.experiment.comparisonbeanandbuilderpattern.data.LargeUserBuilder;
import com.github.hobbylabs.experiment.comparisonbeanandbuilderpattern.data.LargeUserCustomBean;
import com.github.hobbylabs.experiment.comparisonbeanandbuilderpattern.data.LargeUserImmutableBean;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.Instant;

public class PerformanceTest {

    private static DecimalFormat decimalFormatter = new DecimalFormat("###,###");

    @Test
    public void test0001() throws Exception {
        long count = 100_000_000L;
        for (int i = 0; i < 10; ++i) {
            generateLargeUserBean(count);
            generateLargeBuilder(count);
            generateLargeImmutableBean(count);
            generateLargeUserCustomBean(count);
            System.out.println("----");
        }
    }

    public void generateLargeBuilder(long count) throws Exception {
        Instant start = Instant.now();
        LargeUserBuilder largeUserBuilder = null;
        for (long i = 0; i < count; ++i) {
            largeUserBuilder = LargeUserBuilder.builder()
                    .firstName("Taro")
                    .lastName("Suzuki")
                    .fullName("Taro Suzuki")
                    .age(20)
                    .country("JP")
                    .address("Foo Bar Street")
                    .mailAddress("taro-suzuki@example.com")
                    .phoneNumber("000-000-0000")
                    .height(170)
                    .weight(65).build();
            if (largeUserBuilder == null) throw new Exception("Failed to generate LargeUserBuilder");
        }
        Instant end = Instant.now();
        System.out.println(
                "generateLargeBuilder() " + decimalFormatter.format(Duration.between(start, end).toMillis()) + " ms");
    }

    public void generateLargeImmutableBean(long count) throws Exception {
        Instant start = Instant.now();
        LargeUserImmutableBean largeUserImmutableBean = null;
        for (long i = 0; i < count; ++i) {
            largeUserImmutableBean = new LargeUserImmutableBean(
                    "Taro", "Suzuki", "Taro Suzuki",
                    20, "JP", "Foo Bar Street",
                    "taro-suzuki@example.com", "000-000-0000", 170,
                    65
            );
            if (largeUserImmutableBean == null) throw new Exception("Failed to generate LargeUserImmutableBean");
        }
        Instant end = Instant.now();
        System.out.println(
                "generateLargeImmutableBean() " + decimalFormatter.format(Duration.between(start, end).toMillis()) + " ms");
    }

    public void generateLargeUserBean(long count) throws Exception {
        Instant start = Instant.now();
        LargeUserBean largeUserbean = null;
        for (long i = 0; i < count; ++i) {
            largeUserbean = new LargeUserBean();
            largeUserbean.setFirstName("Taro");
            largeUserbean.setLastName("Suzuki");
            largeUserbean.setFullName("Taro Suzuki");
            largeUserbean.setAge(20);
            largeUserbean.setCountry("JP");
            largeUserbean.setAddress("Foo Bar Street");
            largeUserbean.setMailAddress("taro-suzuki@example.com");
            largeUserbean.setPhoneNumber("000-000-0000");
            largeUserbean.setHeight(170);
            largeUserbean.setWeight(65);
            if (largeUserbean == null) throw new Exception("Failed to generate LargeUserBean");
        }
        Instant end = Instant.now();
        System.out.println(
                "generateLargeUserBean() " + decimalFormatter.format(Duration.between(start, end).toMillis()) + " ms");
    }

    public void generateLargeUserCustomBean(long count) throws Exception {
        Instant start = Instant.now();
        LargeUserCustomBean largeUserCustomBean = null;
        for (long i = 0; i < count; ++i) {
            largeUserCustomBean = largeUserCustomBean.init(new LargeUserCustomBean(),
                                            "Taro", "Suzuki", "Taro Suzuki",
                                            20, "JP", "Foo Bar Street",
                                            "taro-suzuki@example.com", "000-000-0000", 170,
                                            65
                                        );
            if (largeUserCustomBean == null) throw new Exception("Failed to generate LargeUserCustomBean01");
        }
        Instant end = Instant.now();
        System.out.println(
                "generateLargeUserCustomBean01() " + decimalFormatter.format(Duration.between(start, end).toMillis()) + " ms");
    }

}
