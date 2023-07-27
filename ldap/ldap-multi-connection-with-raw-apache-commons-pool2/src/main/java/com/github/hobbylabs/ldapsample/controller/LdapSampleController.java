package com.github.hobbylabs.ldapsample.controller;

import com.github.hobbylabs.ldapsample.data.People;
import com.github.hobbylabs.ldapsample.data.request.Query;
import com.github.hobbylabs.ldapsample.data.request.RequestQueryRoot;
import com.github.hobbylabs.ldapsample.data.request.Search;
import lombok.AllArgsConstructor;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.directory.ldap.client.template.LdapConnectionTemplate;
import org.apache.directory.ldap.client.template.exception.PasswordException;
import org.springframework.web.bind.annotation.*;

import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;

import com.github.hobbylabs.ldapsample.service.LdapSampleService;

import java.util.List;

import org.apache.directory.ldap.client.api.LdapConnectionConfig;
import org.apache.directory.ldap.client.api.DefaultPoolableLdapConnectionFactory;
import org.apache.directory.ldap.client.api.LdapConnectionPool;
import org.apache.directory.ldap.client.template.PasswordWarning;

import org.apache.directory.api.ldap.model.message.SearchScope;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class LdapSampleController {

    private Object ldapConnectionPool;

    private GenericKeyedObjectPoolConfig poolConfig;

    public LdapSampleController() {
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

        String status = "";

        LdapConnectionConfig config = new LdapConnectionConfig();
        config.setLdapHost("localhost");
        config.setLdapPort(389);
        config.setName("cn=Manager,dc=mysite,dc=example,dc=com");
        config.setCredentials("secret");
        final DefaultPoolableLdapConnectionFactory factory = new DefaultPoolableLdapConnectionFactory(config);
        final LdapConnectionPool pool = new LdapConnectionPool(factory);
        pool.setTestOnBorrow(true);

        final LdapConnectionTemplate ldapConnectionTemplate = new LdapConnectionTemplate(pool);

        // public PasswordWarning authenticate(org.apache.directory.api.ldap.model.name.Dn userDn, char[] password) throws PasswordException
        try {
            final PasswordWarning warning = ldapConnectionTemplate.authenticate(ldapConnectionTemplate.newDn("cn=Manager,dc=mysite,dc=example,dc=com"), "secret".toCharArray());
            status = "User credentials authenticated";
            if (warning != null) {
                status = status + " \n Warning!!" +warning.toString();
            }
        } catch (Exception e) {
            status = e.toString();
            e.printStackTrace();
        }

        System.out.println("Status of authenticating a user: " + status);
    }


//    @GetMapping(value = {"/getLdapData"})
//    public List<People> getLdapData() {
//        return ldapSampleService.getLdapData();
//    }
//
//    @PostMapping(value = {"/getLdapData"})
//    public People getLdapDate(@RequestBody RequestQueryRoot requestedQueryRoot) throws IllegalAccessException {
//        People people = ldapSampleService.getLdapDataByQuery(requestedQueryRoot);
//        return people;
//    }
}
