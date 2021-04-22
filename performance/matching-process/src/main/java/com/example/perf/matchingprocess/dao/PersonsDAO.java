package com.example.perf.matchingprocess.dao;

import com.example.perf.matchingprocess.domains.Person;
import com.example.perf.matchingprocess.mappers.PersonsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PersonsDAO implements PersonsMapper {
    @Autowired
    private PersonsMapper personsMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Person> selectAll() {
        return personsMapper.selectAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> selectAllNameAsList() {
        return personsMapper.selectAllNameAsList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Cacheable(cacheNames="persons-name-cache", sync=true)
    public Set<String> selectAllNameAsSet() {
        System.out.println("selectAllNameAsSet() has called");
        List<String> persons = personsMapper.selectAllNameAsList();
        return new HashSet<>(persons);
    }
}
