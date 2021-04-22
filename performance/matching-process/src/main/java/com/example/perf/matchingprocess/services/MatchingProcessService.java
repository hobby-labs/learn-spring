package com.example.perf.matchingprocess.services;

import com.example.perf.matchingprocess.dao.PersonsDAO;
import com.example.perf.matchingprocess.domains.Person;
import com.example.perf.matchingprocess.mappers.PersonsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Set;

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

    public void matchingNamesByList(List<String> sourceList) throws Exception {
        List<String> namesInDB = personsDAO.selectAllNameAsList();
        for(String name : sourceList) {
            if(!namesInDB.contains(name)) {
                throw new Exception("A name\"" + name + "\" does not exist in DB.");
            }
        }
    }

    public void matchingNamesBySet(List<String> sourceList) throws Exception {
        Set<String> namesInDB = personsDAO.selectAllNameAsSet();
        for(String name : sourceList) {
            if(!namesInDB.contains(name)) {
                throw new Exception("A name\"" + name + "\" does not exist in DB.");
            }
        }
    }
}
