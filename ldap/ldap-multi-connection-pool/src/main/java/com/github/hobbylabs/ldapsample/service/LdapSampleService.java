package com.github.hobbylabs.ldapsample.service;

import com.github.hobbylabs.ldapsample.data.People;
import com.github.hobbylabs.ldapsample.data.LdapData;
import com.github.hobbylabs.ldapsample.data.request.Query;
import com.github.hobbylabs.ldapsample.data.request.RequestQueryRoot;
import com.github.hobbylabs.ldapsample.data.request.Search;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AbstractFilter;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.BinaryLogicalFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import java.util.ArrayList;
import java.util.List;


@Service
public class LdapSampleService {

    private final LdapTemplate ldapTemplate;

    public LdapSampleService(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    public List<People> getLdapData() {
        List<People> peopleList = new ArrayList<>();

        List<People> results = ldapTemplate.search(
                "ou=People,dc=mysite,dc=example,dc=com",
                "(cn=*)",
                new PeopleAttributeMapper());

        for (People p : results) {
            peopleList.add(p);
        }

        return peopleList;
    }

    public People getLdapDataByQuery(RequestQueryRoot requestedQueryRoot) throws IllegalAccessException {
        Query query = requestedQueryRoot.getQuery();
        Search search = query.getSearch();

        // This instruction assumes that the both cn and mail are NOT null.
        AndFilter andFilter = new AndFilter();
        andFilter.and(new EqualsFilter("cn", search.getCn()));
        andFilter.and(new EqualsFilter("mail", search.getMail()));
        System.out.println(andFilter.encode());

        // This query assumes that the users are all unique.
        List<People> results = ldapTemplate.search(
                "ou=People,dc=mysite,dc=example,dc=com",
                andFilter.encode(),
                new PeopleAttributeMapper());
        if(results.size() == 0) {
            return new People();  // empty
        }
        People people = results.get(0);

        return people;
    }

    private class PeopleAttributeMapper implements AttributesMapper<People> {
        public People mapFromAttributes(Attributes attrs) throws NamingException {
            People people = new People();
            people.setFullName((String)attrs.get("cn").get());
            people.setMail((String)attrs.get("mail").get());
            people.setUidNumber(Integer.parseInt((String)attrs.get("uidNumber").get()));
            people.setGidNumber(Integer.parseInt((String)attrs.get("gidNumber").get()));
            people.setHomeDirectory((String)attrs.get("homeDirectory").get());

            return people;
        }
    }
}
