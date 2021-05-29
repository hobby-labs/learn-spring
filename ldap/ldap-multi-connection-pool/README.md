# ldap-multi-connection-pool
This is a sample project for demonstrating an application that implements LDAP connections.
You can see some examples to read, connection pooling etc in this project.



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

