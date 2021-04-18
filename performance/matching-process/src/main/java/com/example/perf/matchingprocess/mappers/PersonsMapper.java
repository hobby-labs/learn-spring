package com.example.perf.matchingprocess.mappers;

import com.example.perf.matchingprocess.domains.Person;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PersonsMapper {
    List<Person> selectAll();
}
