
https://spring.io/projects/spring-ldap#overview

* Spring LDAP Overview
https://www.baeldung.com/spring-ldap


https://stackoverflow.com/questions/32776541/ldap-template-search-by-multiple-attributes



https://stackoverflow.com/questions/7694992/counting-method-invocations-in-unit-tests

* Connection pooling
https://stackoverflow.com/questions/46659738/how-to-verify-spring-ldap-connection-pooling-configuration

```
# GET
curl http://localhost:8080//api/v1/getLdapData

# POST
curl http://localhost:8080//api/v1/getLdapData -X POST -H 'Content-Type: application/json' -d '{"instruction":{"search":{"cn":"taro","mail":"taro-suzuki@mysite.example.com"}}}'
```

```
ldapsearch -x -LLL -w secret -D "cn=Manager,dc=mysite,dc="
```

https://stackoverflow.com/questions/12362212/what-is-the-best-way-to-know-if-all-the-variables-in-a-class-are-null/12362245#12362245
