# restxml
This is a sample project for demonstrating to implement REST API by using xml.
This sample application is a system that searches cities in a country.

# Operation
Running Docker containers are required to demonstrate this project.  
Then you can launch this Spring Boot application and execute command like below.

* To obtain all cities
```console
# curl -X POST -H 'Content-Type: application/xml' \
    http://localhost:8080/api/v1/get -d '<Country countryName="Japan" />'

<Country id="1"><Cities><City id="6"><Name>Miyagi</Name><Population>2300000</Population></City><City id="1"><Name>Tokyo</Name><Population>13000000</Population></City><City id="5"><Name>Kyoto</Name><Population>1400000</Population></City></Cities></Country>
```

* To obtain only specified cities
```console
# curl -X POST -H 'Content-Type: application/xml' \
    http://localhost:8080/api/v1/get \
    -d '<Country countryName="Japan"><Cities><City><Name>Tokyo</Name></City></Cities></Country>'

<Country id="1"><Cities><City id="1"><Name>Tokyo</Name><Population>13000000</Population></City></Cities></Country>
```

# Testing
You can find some sample test cases in test codes.  
Feel free to try them on your computer.
Some of test cases requires running docker containers that launched by docker-compose.yml in this project.

# Description
## Dependencies

* pom.xml
```xml
        <!-- ... -->
        <!-- For XML REST -->
        <dependency>
            <groupId>com.fasterxml.jackson.dataformat</groupId>
            <artifactId>jackson-dataformat-xml</artifactId>
        </dependency>
        <dependency>
            <groupId>javax.xml.bind</groupId>
            <artifactId>jaxb-api</artifactId>
            <version>2.3.1</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.assertj</groupId>
            <artifactId>assertj-core</artifactId>
        </dependency>
        <!-- ... -->
```

## To accept request formatted XML
A class annotated `@Data` represents data that comming from client as request body.
You can also use `@JacksonXmlRootElement`, `@JacksonXmlProperty` etc if you want to specify the name of elements and attributes.

* RequestCountry.java
```java
// ...

@Data
@JacksonXmlRootElement(localName = "Country")
public class RequestCountry {

    @NotNull
    @JacksonXmlProperty(isAttribute = true, localName = "countryName")
    private String countryName;

    @JacksonXmlElementWrapper(localName = "Cities")
    @JacksonXmlProperty(localName = "City")
    private List<RequestCity> requestCities;

    @Size(min=1, max=32)
    @JacksonXmlProperty(localName = "Description")
    private String description;

    @Min(1)
    @Max(3)
    @JacksonXmlProperty(localName = "MaxFetchSize")
    private Integer maxFetchSize;
}
```

`MediaType.APPLICATION_XML_VALUE` at the endpoint method in a controller to accept data as XML body.
```java
    // ......
    @PostMapping(value = {"/get"}, produces=MediaType.APPLICATION_XML_VALUE)
    public Country getCountry(@Valid @RequestBody RequestCountry requestCountry) throws Exception {
        return restXmlService.getCountryByRequestCountry(requestCountry);
    }
    // ......
```

## To response data formatted XML
Returning the data class that annotated `@JacksonXml...` you already seen like `RequestCountry.java` enables response data as XML.
Or if you specify a header `Content-Type: application/xml` to the response then Spring Boot will return the content as XML.

* (In some controller class)
```java
    // ......
    @RequestMapping(......)
    public ResponseEntity<SomeDataClass> doStuff() {
        // ......
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/xml");

        new Response
        return new ResponseEntity<>(someDataClass, headers, HttpStatus.OK);
    }
    // ......
```

## To response data as XML when some Exceptions occurred
To be able to response data as XML even if some error occured in a program, you can add any exception handlers that respond any XML data.

```java
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // ...

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        /*
         * This method will handle ...
         *     BindException: This exception is thrown when fatal binding errors occur.
         *     MethodArgumentNotValidException: This exception is thrown when argument annotated with @Valid failed validation.
         */
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/xml");

        return new ResponseEntity<>(someXmlData, someHeaders, someHttpStatus);
    }

    // ...
```

You can see examples of handling errors in `GlobalExceptionHandler.java`.

## To response data as XML even when 404 error has occurred
Spring Boot does not throw an Exception when the use requested an endpoint that does not existed.
This means that response with status code 404 will return the content that was not formatted as XML even if you add exception handling.
To response data as XML, we need to add some parameters to be able to throw an Exception from Spring Boot when 404 error was triggered then handling it.  

Parameters below can be set in application.properties to be able to throw an Exception when 404 error was triggered.

* application.properties
```
spring.mvc.throw-exception-if-no-handler-found=true
spring.resources.add-mappings=false
```

Then you can override the method of `ResponseEntityExceptionHandler` in your exception handler class.

* GlobalExceptionHandler
```
// ......
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    // ......
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        // ......

        return new ResponseEntity<Object>(errorResponse, errHeaders, HttpStatus.NOT_FOUND);
    }
    // ......
}
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
  
