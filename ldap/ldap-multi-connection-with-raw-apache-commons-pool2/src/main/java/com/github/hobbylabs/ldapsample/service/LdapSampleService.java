package com.github.hobbylabs.ldapsample.service;

import com.github.hobbylabs.ldapsample.data.People;
import com.github.hobbylabs.ldapsample.data.request.RequestQueryRoot;
import com.github.hobbylabs.ldapsample.data.request.Search;

import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.api.ldap.model.message.SearchScope;
import org.apache.directory.ldap.client.api.DefaultPoolableLdapConnectionFactory;
import org.apache.directory.ldap.client.api.LdapConnectionConfig;
import org.apache.directory.ldap.client.api.LdapConnectionPool;
import org.apache.directory.ldap.client.template.EntryMapper;
import org.apache.directory.ldap.client.template.PasswordWarning;
import org.springframework.stereotype.Service;

import org.apache.directory.ldap.client.template.LdapConnectionTemplate;

import com.github.hobbylabs.ldapsample.service.LdapSampleService;

import java.util.ArrayList;
import java.util.List;

@Service
public class LdapSampleService {

    final LdapConnectionTemplate ldapConnectionTemplate;

    private static final EntryMapper<People> peopleEntryMapper =
            new EntryMapper<People>() {
                @Override
                public People map( Entry entry ) throws LdapException {
                    People p = new People();
                    p.setFullName(entry.get("cn").getString());
                    p.setMail(entry.get("mail").getString());
                    p.setUidNumber(Integer.parseInt(entry.get("uidNumber").getString()));
                    p.setGidNumber(Integer.parseInt(entry.get("gidNumber").getString()));
                    p.setHomeDirectory(entry.get("homeDirectory").getString());
                    return p;
                }
            };


    public LdapSampleService() {
        String status = "";

        LdapConnectionConfig config = new LdapConnectionConfig();
        config.setLdapHost("localhost");
        config.setLdapPort(389);
        config.setName("cn=Manager,dc=mysite,dc=example,dc=com");
        config.setCredentials("secret");
        final DefaultPoolableLdapConnectionFactory factory = new DefaultPoolableLdapConnectionFactory(config);
        final LdapConnectionPool pool = new LdapConnectionPool(factory);
        pool.setTestOnBorrow(true);
        pool.setMinIdle(256);
        pool.setMaxIdle(256);
        pool.setBlockWhenExhausted(true);
        pool.setMaxTotal(256);

        ldapConnectionTemplate = new LdapConnectionTemplate(pool);

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


    public List<People> getLdapDataByQuery(RequestQueryRoot requestedQueryRoot) {
        return getLdapDataBySearchObject(requestedQueryRoot.getQuery().getSearch());
    }
    public List<People> getLdapDataBySearchObject(Search search) {
        List<People> peopleList  = ldapConnectionTemplate.search(
                ldapConnectionTemplate.newDn("ou=People,dc=mysite,dc=example,dc=com"),
                "(&(cn=" + search.getCn() + ")(mail=" + search.getMail() + "))",
                SearchScope.SUBTREE,
                peopleEntryMapper);

        return peopleList;
    }
}
