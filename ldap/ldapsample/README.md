# ldapsample

# Test request

* Get all people in LDAP
```console
$ curl http://localhost:8080//api/v1/getLdapData
```

* Get specified people in LDAP
```
$ curl http://localhost:8080//api/v1/getLdapData -X POST \
    -H 'Content-Type: application/json' \
    -d '{"instruction":{"search":{"cn":"taro","mail":"taro-suzuki@mysite.example.com"}}}'
```

# Check established connections
You can check established connections typing the commands below in the container.
```
# apt update
# apt install iproute2
# while true; do date;ss -t -a | grep -P 'LISTEN|ESTAB'; sleep 1; done
```

# State of the connection pool
Connection pool of this project only create one connection.

# Reference
* Spring LDAP (Official document)  
https://spring.io/projects/spring-ldap#overview  

* Spring LDAP Overview  
https://www.baeldung.com/spring-ldap  

* LDAP template search by multiple attributes  
https://stackoverflow.com/questions/32776541/ldap-template-search-by-multiple-attributes  

* Connection pooling  
https://stackoverflow.com/questions/46659738/how-to-verify-spring-ldap-connection-pooling-configuration  

* Pooling with LdapRepository  
https://github.com/spring-projects/spring-ldap/issues/472
