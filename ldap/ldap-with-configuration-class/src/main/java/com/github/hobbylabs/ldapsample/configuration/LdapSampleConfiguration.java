package com.github.hobbylabs.ldapsample.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.pool.factory.PoolingContextSource;

@Configuration
public class LdapSampleConfiguration {

    @Value("${ldap.url}")
    private String ldapUrl;

    @Value("${ldap.bindDn}")
    private String bindDn;

    @Value("${ldap.password}")
    private String ldapPassword;

    @Value("${ldap.pooled}")
    private Boolean ldapPooled;

    @Bean
    public LdapContextSource contextSource() {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl(ldapUrl);
        contextSource.setUserDn(bindDn);
        contextSource.setPassword(ldapPassword);
        contextSource.setPooled(ldapPooled);  // This enables single connection pooling

        return contextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate() {
        return new LdapTemplate(contextSource());
    }
}
