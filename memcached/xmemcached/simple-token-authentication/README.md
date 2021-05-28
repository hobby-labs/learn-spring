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

# References
* sixhours-team/memcached-spring-boot
  https://github.com/sixhours-team/memcached-spring-boot

* killme2008/xmemcached
  https://github.com/killme2008/xmemcached
