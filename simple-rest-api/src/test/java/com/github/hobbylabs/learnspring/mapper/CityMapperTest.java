package com.github.hobbylabs.learnspring.mapper;

import com.github.hobbylabs.learnspring.domain.City;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
public class CityMapperTest {

    @Autowired
    private CityMapper cityMapper;

    @Test
    public void findByStateTest() {
        City city = cityMapper.findByState("CA");
        assertThat(city.getName()).isEqualTo("San Francisco");
        assertThat(city.getState()).isEqualTo("CA");
        assertThat(city.getCountry()).isEqualTo("US");
    }

}