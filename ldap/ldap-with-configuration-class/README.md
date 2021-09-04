# ldap-with-configuration-class
This is a sample project for demonstrating an application that implements LDAP connections by using configuration class.

# Operation
Running Docker containers are required to launch LDAP server to demonstrate this project.

* Get all people in LDAP
```console
$ curl http://localhost:8080//api/v1/getLdapData
```

* Get specified people in LDAP
```console
$ curl http://localhost:8080//api/v1/getLdapData -X POST \
    -H 'Content-Type: application/json' \
    -d '{"instruction":{"search":{"cn":"taro","mail":"taro-suzuki@mysite.example.com"}}}'
```

# Description
## Dependencies

* pom.xml
```xml
        <dependency>
            <groupId>org.springframework.ldap</groupId>
            <artifactId>spring-ldap-core</artifactId>
            <version>2.3.2.RELEASE</version>
        </dependency>
```

You can create a bean class to configure LdapTemplate.
Some notices to create it is that use PoolConfig that is packaged in `org.springframework.ldap.pool2` not in `org.springframework.ldap.pool`.

* LdapSampleConfiguration
```java
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

    // ...

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
```

Another example of creating configuration class for LDAP is in the below.
* [LDAP-316: support for apache commons-pool2 #351](https://github.com/spring-projects/spring-ldap/issues/351#issuecomment-586551591)

Configuration attributes will be found in the reference of Apache Commons Pool 2.
* [Class PoolConfig](https://docs.spring.io/spring-ldap/docs/current/apidocs/org/springframework/ldap/pool2/factory/PoolConfig.html)

After creating a configuration class, its parameters can be declared in `application.properties` like below.

* application.properties
```properties
ldap.url=ldap://localhost:389
ldap.bindDn=cn=Manager,dc=mysite,dc=example,dc=com
ldap.password=secret

# Pool 2 config. If ldap.pool.enabled=false, all configs under it do not affected to the LDAP client.
ldap.pool.enabled=true
ldap.pool.testOnBorrow=true
ldap.pool.maxTotalPerKey=8
ldap.pool.maxIdlePerKey=8
ldap.pool.timeBetweenEvictionRunsMillis=60000
ldap.pool.minIdlePerKey=4
```

# To check established connections
You can check established connections typing the commands below in the container.
```console
# apt update
# apt install iproute2
# while true; do date;ss -t -a | grep -P 'LISTEN|ESTAB'; sleep 1; done
LISTEN     0       1024           0.0.0.0:389           0.0.0.0:*
LISTEN     0       4096        127.0.0.11:40439         0.0.0.0:*
ESTAB      0       0           172.24.0.2:389        172.24.0.1:37938
LISTEN     0       1024              [::]:389              [::]:*
```

# State of the connection pool
Connection pool of this project only create one connection.

# Reference
* [Spring LDAP (Official document)](https://spring.io/projects/spring-ldap#overview)
* [Spring LDAP Overview](https://www.baeldung.com/spring-ldap)
* [LDAP template search by multiple attributes](https://stackoverflow.com/questions/32776541/ldap-template-search-by-multiple-attributes)
* [Connection pooling](https://stackoverflow.com/questions/46659738/how-to-verify-spring-ldap-connection-pooling-configuration)
* [Re-run Spring Boot Configuration Annotation Processor to update generated metadata](https://stackoverflow.com/questions/33483697/re-run-spring-boot-configuration-annotation-processor-to-update-generated-metada)
