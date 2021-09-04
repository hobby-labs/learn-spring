package com.github.hobbylabs.ldapsample.service;

import com.github.hobbylabs.ldapsample.data.People;
import com.github.hobbylabs.ldapsample.data.LdapData;
import com.github.hobbylabs.ldapsample.data.request.Query;
import com.github.hobbylabs.ldapsample.data.request.RequestQueryRoot;
import com.github.hobbylabs.ldapsample.data.request.Search;
import com.github.hobbylabs.ldapsample.properties.reloading.beans.EnvironmentConfigBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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


@Slf4j
@Service
public class LdapSampleService {

    private final LdapTemplate ldapTemplate;

    private EnvironmentConfigBean environmentConfigBean;

    @Value("${sample.ldap.url}")
    private String ldapUrl;
    @Value("${sample.ldap.bindDn}")
    private String ldapBindDn;
    @Value("${sample.ldap.password}")
    private String ldapPassword;

    public LdapSampleService(LdapTemplate ldapTemplate, EnvironmentConfigBean environmentConfigBean) {
        this.ldapTemplate = ldapTemplate;
        this.environmentConfigBean = environmentConfigBean;
    }

    public List<People> getLdapData() {
        log.info("bean.ldap.url=\"" + environmentConfigBean.getUrl()
                + "\",bean.ldap.bindDn=\"" + environmentConfigBean.getBindDn()
                + "\",bean.ldap.password=\"" + environmentConfigBean.getPassword()
                + "\",value.ldap.url=\"" + ldapUrl
                + "\",value.ldap.bindDn=\"" + ldapBindDn
                + "\",value.ldap.password=\"" + ldapPassword + "\"");

        List<People> result = ldapTemplate.search(
                "ou=People,dc=mysite,dc=example,dc=com",
                "(cn=*)",
                new PeopleAttributeMapper());

        return result;
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
