# ldap-reload-properties
This project is a sample project to realize real time reloading properties without any down time.

# Testing
Jar file of this program should be first.

```
$ ./mvnw install
// This command will create a jar file under the target directory
```

Run the applications like below.

```
$ # First terminal
$ java -jar ./target/ldapsample-0.0.1-SNAPSHOT.jar --server.port=8080 \
    --spring.properties.refreshDelay=2000 \
    --spring.config.location=file://${PWD}/etc/8080/application.properties

$ # Secound terminal
$ java -jar ./target/ldapsample-0.0.1-SNAPSHOT.jar --server.port=8081 \
    --spring.properties.refreshDelay=2000 \
    --spring.config.location=file://${PWD}/etc/8081/application.properties

$ # Third terminal
$ java -jar ./target/ldapsample-0.0.1-SNAPSHOT.jar --server.port=8082 \
    --spring.properties.refreshDelay=2000 \
    --spring.config.location=file://${PWD}/etc/8082/application.properties
```

To request cintinuously, run the command from another terminal like below.

```
$ while sleep 0.1; do echo -n "$(date) "; curl http://localhost:8080//api/v1/getLdapData 2> /dev/null | head -c 30; echo "......"; done
```

# Reference
* [Module ngx_http_upstream_module](http://nginx.org/en/docs/http/ngx_http_upstream_module.html)
* [Nginx upstream active passive](https://stackoverflow.com/questions/30059711/nginx-upstream-active-passive)
