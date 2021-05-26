
https://spring.io/projects/spring-ldap#overview

* Spring LDAP Overview
https://www.baeldung.com/spring-ldap


https://stackoverflow.com/questions/32776541/ldap-template-search-by-multiple-attributes



https://stackoverflow.com/questions/7694992/counting-method-invocations-in-unit-tests

* Connection pooling
https://stackoverflow.com/questions/46659738/how-to-verify-spring-ldap-connection-pooling-configuration
  
* Connection pooling 2
https://docs.spring.io/spring-ldap/docs/1.3.2.RELEASE/reference/html/pooling.html
https://docs.spring.io/spring-ldap/docs/2.3.0.BUILD-SNAPSHOT/reference/html/pooling.html


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

# Check established connections
You can check established connections typing the commands below in the container.
```
# apt update
# apt install iproute2
# while true; do date;ss -t -a | grep -P 'LISTEN|ESTAB'; sleep 1; done
```

