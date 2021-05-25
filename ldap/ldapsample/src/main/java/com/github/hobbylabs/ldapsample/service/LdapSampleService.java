package com.github.hobbylabs.ldapsample.service;

import com.github.hobbylabs.ldapsample.domain.LdapData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.WhitespaceWildcardsFilter;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LdapSampleService {

    private final LdapTemplate ldapTemplate;

    public LdapSampleService(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    public LdapData getLdapData() {

        WhitespaceWildcardsFilter filter = new WhitespaceWildcardsFilter("cn", "foo");
        System.out.println(filter.encode());

        List<String> results = ldapTemplate.search(
                "dc=mysite,dc=example,dc=com",
                "",
                (AttributesMapper<String>) attrs -> (String) attrs.get("cn").get());

        for (String s : results) {
            System.out.println(s);
        }

        return null;
    }
}
