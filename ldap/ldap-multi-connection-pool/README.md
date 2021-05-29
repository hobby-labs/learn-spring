# ldap-multi-connection-pool
This is a sample project for demonstrating an application that implements LDAP connections.
You can see some examples to read, connection pooling etc in this project.

# Operation
Running Docker containers are required to launch LDAP server to demonstrate this project.  
Then you can launch this Spring Boot application and execute command like below.

* Get all peoples information in LDAP
```console
$ curl http://localhost:8080//api/v1/getLdapData

[{"cn":"taro","mail":"taro-suzuki@mysite.example.com","uidNumber":1000,"gidNumber":1000,"homeDirectory":"/home/taro-suzuki"},{"cn":"hanako","mail":"hanako-suzuki@mysite.example.com","uidNumber":1001,"gidNumber":1002,"homeDirectory":"/home/hanako-suzuki"}]
```

* Get specified people infomation in LDAP

```
$ curl http://localhost:8080//api/v1/getLdapData -X POST \
    -H 'Content-Type: application/json' \
    -d '{"instruction":{"search":{"cn":"taro","mail":"taro-suzuki@mysite.example.com"}}}'

{"cn":"taro","mail":"taro-suzuki@mysite.example.com","uidNumber":1000,"gidNumber":1000,"homeDirectory":"/home/taro-suzuki"}
```

# Description
## Dependencies

* pom.xml
```xml
        <!-- ... -->
        <!-- For LDAP -->
        <dependency>
            <groupId>org.springframework.ldap</groupId>
            <artifactId>spring-ldap-core</artifactId>
            <version>2.3.2.RELEASE</version>
        </dependency>
        <!-- For connection pool of LDAP -->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-pool2</artifactId>
        </dependency>
        <!-- ... -->
```

It assumes to use Apache Commons Pool 2 to create multi connection pools between the application and LDAP that aim to improve performance.

## To read configuration from applicationContext.xml
You can create `applicationContext.xml` under the `resources` directory and its contents are like below.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:ldap="http://www.springframework.org/schema/ldap"
       xsi:schemaLocation="http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/ldap https://www.springframework.org/schema/ldap/spring-ldap.xsd">

    <context:property-placeholder location="classpath:/ldap.properties" />

    <ldap:context-source id="contextSource"
                         password="${sample.ldap.password}"
                         url="${sample.ldap.url}"
                         username="${sample.ldap.userDn}"
                         >
        <ldap:pooling2 min-idle-per-key="2" eviction-run-interval-millis="60000" />
    </ldap:context-source>

    <ldap:ldap-template id="ldapTemplate" context-source-ref="contextSource"/>
</beans>
```

`ldap:pooling2` element under `ldap:context-source` element enables the application to create connection pool.
You can find other options in the URL below.  

* [8.4 Pool2 Configuration(Spring LDAP Reference)](https://docs.spring.io/spring-ldap/docs/2.3.0.BUILD-SNAPSHOT/reference/html/pooling.html#pool2-configuration)

And you can also see examples of applicationContext.xml in the [spring-projects/spring-ldap] GitHub repository.

* [applicationContext.xml under samples in spring-projects/spring-ldap](https://github.com/spring-projects/spring-ldap/blob/d5212a0/samples/plain/src/main/resources/applicationContext.xml)

To read some parameters like `${sample.ldap.password}`, `${sample.ldap.url}`, `ldap.properties` file that specified `context:property-placeholder` can be prepared under the resources directory.

* ldap.properties
```properties
sample.ldap.url=ldap://localhost:389
sample.ldap.bindDn=cn=Manager,dc=mysite,dc=example,dc=com
sample.ldap.password=secret
```

Finally `@ImportResource` annotation is required to read configuration from `applicationContext.xml`.

* (Your main class in Spring Boot)
```java
@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class LdapSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(LdapSampleApplication.class, args);
    }

}
```

## To use LdapTemplate in your source code
You can AutoWire LdapTemplate Like below.

```java
@Service
public class LdapSampleService {

    private final LdapTemplate ldapTemplate;

    public LdapSampleService(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;  // This will AutoWire LdapTemplate
    }

    // ......
}
```

Then you can querying LDAP like below.

```java
@Service
public class LdapSampleService {

    // ......
    public List<People> doStuff() {
        List<People> results = ldapTemplate.search(
                "ou=People,dc=mysite,dc=example,dc=com",
                andFilter.encode(),
                new PeopleAttributeMapper());

        for (People p : results) {
            System.out.println(p.getFullName());
            System.out.println(p.getMail());
        }

        return results;
    }
    // ......
}
```

`People` indicates a class that represents data of LDAP entry.

* People.java
```
// ......
@Data
public class People {
    @JsonProperty("cn")
    private String fullName;
    @JsonProperty("mail")
    private String mail;
    @JsonProperty("uidNumber")
    private Integer uidNumber;
    @JsonProperty("gidNumber")
    private Integer gidNumber;
    @JsonProperty("homeDirectory")
    private String homeDirectory;
}
```

Complex filtering is also enabled by quering like below.

```

@Service
public class LdapSampleService {

    // ......
    public List<People> doStuff() {

        AndFilter andFilter = new AndFilter();
        andFilter.and(new EqualsFilter("cn", "John"));
        andFilter.and(new EqualsFilter("mail", "john@example.com"));
        System.out.println(andFilter.encode());

        List<People> results = ldapTemplate.search(
                "ou=People,dc=mysite,dc=example,dc=com",
                andFilter.encode(),
                new PeopleAttributeMapper());

        // ......
    }
    // ......
}
```

# To check established connections
You can check established connections typing the commands below in the container.

```console
# apt update
# apt install iproute2
# while true; do date;ss -t -a | grep -P 'LISTEN|ESTAB'; sleep 1; done
...
LISTEN  0       1024             0.0.0.0:389            0.0.0.0:*
LISTEN  0       4096          127.0.0.11:34227          0.0.0.0:*
ESTAB   0       0             172.22.0.2:389         172.22.0.1:54746
ESTAB   0       0             172.22.0.2:389         172.22.0.1:54750
LISTEN  0       1024                [::]:389               [::]:*
```

# Reference
* Spring LDAP  
https://spring.io/projects/spring-ldap  

* Spring LDAP Overview  
https://www.baeldung.com/spring-ldap  

* LDAP template search by multiple attributes  
https://stackoverflow.com/questions/32776541/ldap-template-search-by-multiple-attributes  


* Counting method invocations in Unit tests  
https://stackoverflow.com/questions/7694992/counting-method-invocations-in-unit-tests  

* How to verify Spring LDAP connection pooling configuration?  
https://stackoverflow.com/questions/46659738/how-to-verify-spring-ldap-connection-pooling-configuration  

* Spring LDAP Reference - 8. Pooling Support  
https://docs.spring.io/spring-ldap/docs/2.3.0.BUILD-SNAPSHOT/reference/html/pooling.html  

