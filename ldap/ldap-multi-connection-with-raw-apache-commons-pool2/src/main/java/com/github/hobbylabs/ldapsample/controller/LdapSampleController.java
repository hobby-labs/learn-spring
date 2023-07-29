package com.github.hobbylabs.ldapsample.controller;

import com.github.hobbylabs.ldapsample.data.People;
import com.github.hobbylabs.ldapsample.data.request.RequestQueryRoot;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.github.hobbylabs.ldapsample.service.LdapSampleService;

import java.util.List;

@RestController
//@AllArgsConstructor
@RequestMapping("/api/v1")
public class LdapSampleController {

    private final LdapSampleService ldapSampleService;

    public LdapSampleController(LdapSampleService ldapSampleService) {
        this.ldapSampleService = ldapSampleService;

//        // https://www.programcreek.com/java-api-examples/?api=org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig
//
//        GenericKeyedObjectPoolConfig objectPoolConfig = new GenericKeyedObjectPoolConfig();
//        DirContextPooledObjectFactory dirContextPooledObjectFactory = new DirContextPooledObjectFactory();  // https://www.programcreek.com/java-api-examples/?code=spring-projects%2Fspring-ldap%2Fspring-ldap-master%2Fcore%2Fsrc%2Fmain%2Fjava%2Forg%2Fspringframework%2Fldap%2Fpool2%2Ffactory%2FPooledContextSource.java#
//        // objectPoolConfig.setMaxTotalPerKey(<int>);
//        //objectPoolConfig.setMaxTotal(<int>);
//
//        //objectPoolConfig.setMaxIdlePerKey(<int>);
//        //objectPoolConfig.setMinIdlePerKey(<int>);
//
//        //objectPoolConfig.setTestWhileIdle(<int>);
//        //objectPoolConfig.setTestOnReturn(<boolean>);
//        //objectPoolConfig.setTestOnCreate(<boolean>);
//        //objectPoolConfig.setTestOnBorrow(<boolean>);
//
//        //objectPoolConfig.setTimeBetweenEvictionRunsMillis(<long>);
//        //objectPoolConfig.setEvictionPolicyClassName(<String>);
//        //objectPoolConfig.setMinEvictableIdleTimeMillis(<long>);
//        //objectPoolConfig.setNumTestsPerEvictionRun(<int>);
//        //objectPoolConfig.setSoftMinEvictableIdleTimeMillis(<long>);
//
//        //objectPoolConfig.setJmxEnabled(<boolean>);
//        //objectPoolConfig.setJmxNameBase(<String>);
//        //objectPoolConfig.setJmxNamePrefix(<String>);
//
//        //objectPoolConfig.setMaxWaitMillis(<long>);
//
//        //objectPoolConfig.setFairness(<boolean>);
//        //objectPoolConfig.setBlockWhenExhausted(<boolean>);
//        //objectPoolConfig.setLifo(<boolean>);
//
//        protected final GenericKeyedObjectPool<Object,Object> keyedObjectPool;
//
//        keyedObjectPool = new GenericKeyedObjectPool<Object, Object>(dirContextPooledObjectFactory, objectPoolConfig);
    }

    @PostMapping(value = {"/getLdapData"})
    public List<People> getLdapData(@RequestBody RequestQueryRoot requestedQueryRoot) {
        List<People> peopleList = ldapSampleService.getLdapDataByQuery(requestedQueryRoot);
        return peopleList;
    }

//    @PostMapping(value = {"/getLdapData"})
//    public People getLdapDate(@RequestBody RequestQueryRoot requestedQueryRoot) throws IllegalAccessException {
//        People people = ldapSampleService.getLdapDataByQuery(requestedQueryRoot);
//        return people;
//    }
}
