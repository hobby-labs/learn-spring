# comparison-bean-and-builder-pattern
This is a repository that compare the format and the performance.
To create bean and bean of builder pattern, I use lombok in this example.

# Format and performance

## Bean
Simple bean is to be able to implement giving `@Data` annotation in a class that contains any field attributes.

```java
@Data
public class LargeUserBean {
    private String firstName;
    private String lastName;
    private String fullName;
    private Integer age;
    private String country;
    private String address;
    private String mailAddress;
    private String phoneNumber;
    private Integer height;
    private Integer weight;
}
```

You can use it by setting each field through any setters after instantiate it.

```java
    LargeUserBean largeUserBean = new LargeUserBean();
    largeUserBean.setFirstName("Taro");
    largeUserBean.setLastName("Suzuki");
    ......
```

## Builder pattern
Bean with builder pattern is to be able to implement giving `@Value` and `@Builder` annotation in a class that contains any field attributes.
`@Value` annotation will make a bean immutable.

```java
@Value
@Builder
public class LargeUserBuilder {
    private String firstName;
    private String lastName;
    private String fullName;
    private Integer age;
    private String country;
    private String address;
    private String mailAddress;
    private String phoneNumber;
    private Integer height;
    private Integer weight;
}
```

You can use it by passing values through a builder and build it.

```java
    LargeUserBuilder largeUserBuilder = LargeUserBuilder.builder()
        .firstName("Taro")
        .lastName("Suzuki")
        ......
        .build();
```

https://stackoverflow.com/questions/39381474/cant-make-jackson-and-lombok-work-together

# 
```
openjdk version "11.0.11" 2021-04-20
OpenJDK Runtime Environment (build 11.0.11+9)
OpenJDK 64-Bit Server VM (build 11.0.11+9, mixed mode)
```


