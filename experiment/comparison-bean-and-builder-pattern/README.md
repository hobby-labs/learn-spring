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

## Immutable Bean
Immutable bean is to be able to implement giving `@Data` annotation and make all field attribute `private final`.

```java
@Data
public class LargeUserImmutableBean {
    private final String firstName;
    private final String lastName;
    private final String fullName;
    private final Integer age;
    private final String country;
    private final String address;
    private final String mailAddress;
    private final String phoneNumber;
    private final Integer height;
    private final Integer weight;
}
```

You can use it by passing values through its constructor.

```java
    LargeUserImmutableBean largeUserImmutableBean =
            new LargeUserImmutableBean("Taro", "Suzuki", ...);
```

This instance never changed it field attributes because its attributes are all `final` (and not any setters).

## Custom bean
My custom bean that has init method to set all attributes at once.
This class considered to be able to deserialize it with ObjectMapper easily.  
// Caution: It is my own strategy not widely used among other programmers.

```java
@Data
public class LargeUserCustomBean {
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

    public static LargeUserCustomBean init(
            LargeUserCustomBean instance,
            String firstName,    String lastName,     String fullName,
            Integer age,         String country,      String address,
            String mailAddress,  String phoneNumber,  Integer height,
            Integer weight
    ) {
        instance.firstName = firstName;
        instance.lastName = lastName;
        instance.fullName = fullName;
        instance.age = age;
        instance.country = country;
        instance.address = address;
        instance.mailAddress = mailAddress;
        instance.phoneNumber = phoneNumber;
        instance.height = height;
        instance.weight = weight;
        return instance;
    }
}
```

You can use it by passing values through init method.

```java
    LargeUserCustomBean largeUserCustomBean = largeUserCustomBean.init(new LargeUserCustomBean(),
                                                    "Taro", "Suzuki", "Taro Suzuki", ......
                                                );
```

# Performance
I measured performance of them.
The scenario is that to create instance and set all attributes 100,000,000 times.
Then repeat this instruction 10 times.
Results are like below.

```
                     r1     r2     r3     r4     r5     r6     r7     r8     r9
Bean:             273ms  268ms  299ms  298ms  299ms  290ms  298ms  298ms  298ms
Builder pattern: 3738ms 3806ms 4302ms 4306ms 4351ms 4332ms 4350ms 4332ms 4427ms
Immutable Bean:  2331ms 2590ms 2648ms 2647ms 2719ms 2652ms 2655ms 2645ms 2641ms
Custom bean:     2294ms 2656ms 2690ms 2664ms 2779ms 2646ms 2667ms 2650ms 2722ms
// I omitted each first result because there are some deviations of results due to lacking caches and JVM's optimization etc.
```

Bean breaks the best record.
But you may choose any type of way along with your strategies.
For example, I recommend you to use builder pattern if you want to implement immutability and keep your data safe in your program.
Performance is not the only reason why we choose one.

# Reference
https://stackoverflow.com/questions/39381474/cant-make-jackson-and-lombok-work-together
