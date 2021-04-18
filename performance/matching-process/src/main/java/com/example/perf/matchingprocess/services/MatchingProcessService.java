package com.example.perf.matchingprocess.services;

import com.example.perf.matchingprocess.dao.PersonsDAO;
import com.example.perf.matchingprocess.domains.Person;
import com.example.perf.matchingprocess.mappers.PersonsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class MatchingProcessService {

    @Autowired
    private PersonsDAO personsDAO;

    public void selectAll() {
        List<Person> persons = personsDAO.selectAll();
        System.out.println(persons.size());
        for(Person p : persons) {
            System.out.println(p.getName());
        }
    }

    public void mappingNamesByList(List<String> sourceList) {
        List<String> namesInDB = personsDAO.selectAllNameAsList();
        for(String name : sourceList) {
            namesInDB.contains(name);
        }
    }
}
