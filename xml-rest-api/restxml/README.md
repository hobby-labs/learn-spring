# restxml
This is a sample project for demonstrating to implement REST API by using xml.
This sample application is a system that searches cities in a country.

# Operation
Run docker containers first to demonstrate this project.  
Then you can launch this Spring Boot application and execute command like below.

* To obtain all cities
```
# curl -X POST -H 'Content-Type: application/xml' \
    http://localhost:8080/api/v1/get \
    -d '<Country countryName="Japan"><Cities><City><Name>Tokyo</Name></City></Cities></Country>'

curl -X POST -H 'Content-Type: application/xml' \
    http://localhost:8080/api/v1/gett \
    -d '<Country countryName="Japan"><Cities><City><Name>Tokyo</Name></City></Cities></Country>'

```


# Reference
* Spring Boot REST XML  
https://zetcode.com/springboot/restxml/

* Spring Boot Rest XML example – Web service with XML Response  
https://bezkoder.com/spring-boot-rest-xml/

* XML Serialization and Deserialization with Jackson  
https://www.baeldung.com/jackson-xml-serialization-and-deserialization

* Validation  
https://spring.io/guides/gs/validating-form-input/

* Spring Boot validation: get max size from property file  
https://stackoverflow.com/a/66744942

* JUnit  
https://spring.io/guides/gs/testing-web/

* Can't make Jackson and Lombok work together  
https://stackoverflow.com/questions/39381474/cant-make-jackson-and-lombok-work-together

* Custom Error Message Handling for REST API
https://www.baeldung.com/global-error-handler-in-a-spring-rest-api  

* Spring Boot - Handling NoHandlerFoundException
https://stackoverflow.com/a/51050702  
  