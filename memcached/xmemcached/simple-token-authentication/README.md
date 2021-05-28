# simple-token-authentication
This is a sample project for demonstrating to use memcached on Spring Boot.

# Operation
You can check the behavior of this application by sending request like below.
First you need to register a user to the application.

* Register a user
```
# curl -X POST -H 'Content-Type: application/json' \
    http://localhost:8080/api/v1/setToken -d '{"userName": "taro"}' | jq

{"userName":"taro","token":"45961408-33a1-4fe4-b7db-74a0567d559b"}
```

You can get an auth token after you registered a user.
Then you can use authenticate the user by using the auth token that created at previous command.

```
# curl -X POST -H 'Content-Type: application/json' \
    http://localhost:8080/api/v1/authToken -d '{"token": "45961408-33a1-4fe4-b7db-74a0567d559b"}'

{"code":0,"userName":"taro","message":"Succeeded in authenticate user."}
```

# Testing
You can find some sample test cases in test codes.  
Feel free to try them on your computer.
Some of test cases requires running docker containers that launched by docker-compose.yml in this project.

# Description
If you want to implement to access memcached in Spring Boot, you should add Xmemcached in your pom.xml.

```
		<dependency>
			<groupId>com.googlecode.xmemcached</groupId>
			<artifactId>xmemcached</artifactId>
			<version>2.4.7</version>
		</dependency>
```

After added a dependency, you should create configuration class to set up memcached client's configurations.
I recommend you to create it to be able to separate values to application.properties by using some annotations.

* MemcachedClientConfiguration.java
```
package com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.configuration;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class MemcachedClientConfiguration {

    @Value("${memcached.server.list.string}")
    private String serverListString;

    @Bean
    public MemcachedClient configureMemcachedClient() throws IOException {
        return new XMemcachedClientBuilder(AddrUtil.getAddresses(serverListString)).build();
    }
}
```

Then you can declare `memcached.server.list.string` variable in `application.properties`.

* application.properties
```
memcached.server.list.string=127.0.0.1:11211 127.0.0.1:11212 127.0.0.1:11213
```

To use memcached client in your program, declare MemcachedClient in a class and 

```
@Repository
@AllArgsConstructor
public class AuthTokenRepositoryImpl implements AuthTokenRepository {

    private final MemcachedClient memcachedClient;

    // ......

    public String someMethod(......) throws InterruptedException, TimeoutException, MemcachedException {
        return memcachedClient.get(token, 3000);
    }
    
    // ......
}
```

This code is as same like below.

```
@Repository
public class AuthTokenRepositoryImpl implements AuthTokenRepository {

    private final MemcachedClient memcachedClient;
    
    public AuthTokenRepositoryImpl(MemcachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
    }
    // ......

    public String someMethod(......) throws InterruptedException, TimeoutException, MemcachedException {
        return memcachedClient.get(token, 3000);
    }
    
    // ......
}
```

# References
* sixhours-team/memcached-spring-boot
  https://github.com/sixhours-team/memcached-spring-boot

* killme2008/xmemcached
  https://github.com/killme2008/xmemcached
