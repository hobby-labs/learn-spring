package com.github.hobbylabs.ldapsample.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.pool2.factory.PoolConfig;
import org.springframework.ldap.pool2.factory.PooledContextSource;
import org.springframework.ldap.pool2.validation.DefaultDirContextValidator;
import org.springframework.ldap.transaction.compensating.manager.TransactionAwareContextSourceProxy;

@Configuration
public class LdapSampleConfiguration {

    @Value("${ldap.url}")
    private String ldapUrl;

    @Value("${ldap.bindDn}")
    private String bindDn;

    @Value("${ldap.password}")
    private String ldapPassword;

    @Value("${ldap.pool.enabled}")
    private Boolean ldapPoolEnabled;

    @Value("${ldap.pool.testOnBorrow}")
    private Boolean ldapPoolTestOnBorrow;

    @Value("${ldap.pool.maxTotalPerKey}")
    private Integer maxTotalPerKey;

    @Value("${ldap.pool.maxIdlePerKey}")
    private Integer maxIdlePerKey;

    @Value("${ldap.pool.timeBetweenEvictionRunsMillis}")
    private Long timeBetweenEvictionRunsMillis;

    @Value("${ldap.pool.minIdlePerKey}")
    private Integer minIdlePerKey;

    @Bean
    public ContextSource contextSource() {
        LdapContextSource ldapContextSource = new LdapContextSource();
        ldapContextSource.setUrl(ldapUrl);
        ldapContextSource.setUserDn(bindDn);
        ldapContextSource.setPassword(ldapPassword);
        ldapContextSource.afterPropertiesSet();

        if (ldapPoolEnabled != null && ldapPoolEnabled == true) {
            return createPoolContextSource(ldapContextSource);
        }

        return ldapContextSource;
    }

    /**
     * Create ContextSource that is enabled pool2 config.
     * @param ldapContextSource LdapContextSource
     * @return ContextSource that has pooling configuration.
     */
    public ContextSource createPoolContextSource(LdapContextSource ldapContextSource) {
        PoolConfig poolConfig = new PoolConfig();
        poolConfig.setTestOnBorrow(ldapPoolTestOnBorrow);
        poolConfig.setMaxTotalPerKey(maxTotalPerKey);
        poolConfig.setMaxIdlePerKey(maxIdlePerKey);
        poolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        poolConfig.setMinIdlePerKey(minIdlePerKey);

        PooledContextSource pcs = new PooledContextSource(poolConfig);
        pcs.setContextSource(ldapContextSource);
        pcs.setDirContextValidator(new DefaultDirContextValidator());

        TransactionAwareContextSourceProxy contextSourceProxy = new TransactionAwareContextSourceProxy(pcs);

        return contextSourceProxy;
    }

    @Bean
    public LdapTemplate ldapTemplate() {
        return new LdapTemplate(contextSource());
    }
}
