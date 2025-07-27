# learnspringmodule
This is a repository that you can find examples how to create modules that will be used in other Spring Boot applications.

# Create jar file
```
$ cd learnspringmodule
$ mvn package
```

# Put the jar file to your another project
Put the jar file into the directory that belonging to the class path of your another Spring Boot project.
Then put an annotation in the main class like below to be able to find the jar file.

```
@SpringBootApplication(scanBasePackages = "com.github.hobbylabs.spring.learnspringmodule")
@SpringBootApplication
public class AnotherSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnotherSpringBootApplication.class, args);
    }
    // ......
}
```


# Publish jar package to some maven repository
To publish your jar file to some maven repository, you should modify pom.xml and execute some command.
Please let me skip the details because this category is beyond the scope of this repository.

# References
- [UnsatisfiedDependencyException: Error creating bean with name](https://stackoverflow.com/questions/41511511/unsatisfieddependencyexception-error-creating-bean-with-name)
- [Spring BootでNo qualifying bean of typeが出た時の原因と対応](https://qiita.com/NagaokaKenichi/items/058a7243bd2948de7553)
- [org.springframework.beans.factory.NoSuchBeanDefinitionException が出たときの対処](https://tutuz-tech.hatenablog.com/entry/2019/04/20/200530)
