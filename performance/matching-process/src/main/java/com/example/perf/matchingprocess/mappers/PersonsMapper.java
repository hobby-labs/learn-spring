package com.example.perf.matchingprocess.mappers;

import com.example.perf.matchingprocess.domains.Person;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface PersonsMapper {
    /**
     * Get all Persons in Persons table as Person in List.
     * @return All Persons as List
     */
    List<Person> selectAll();

    /**
     * Get all names in Persons table as Strings in List.
     * @return All names as List
     */
    List<String> selectAllNameAsList();

    /**
     * Get all names in Persons table as String in Set
     * @return All names as Set
     */
    Set<String> selectAllNameAsSet();
}
