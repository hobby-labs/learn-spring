package com.example.perf.matchingprocess.dao;

import com.example.perf.matchingprocess.domains.Person;
import com.example.perf.matchingprocess.mappers.PersonsMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Set<String> selectAllNameAsSet() {
        List<String> persons = personsMapper.selectAllNameAsList();
        return new HashSet<>(persons);
    }
}
