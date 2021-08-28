# Reload properties by actuator and cloud

## To run the application from the CLI

```
$ java -jar ./target/reload_properties_by_actuator_and_cloud-0.0.1-SNAPSHOT.jar \
    --spring.config.location=file://${PWD}/target/classes/application.properties
```

## To run the application from the IntelliJ
You can run the application from the IntelliJ by right-clicking ReloadPropertiesApplication from the Project pain then click `Run` on the context menu.

## Testing
Run continuous request from the command line like below and you can see a current value of `test.message` on your log terminal.
```
$ while sleep 1; do curl http://localhost:8080/api/v1/getReloadProperties; done
```

You can confirm reloading the properties by modifying a value of `test.message` in `application.properties` located in `target/classes` directory and request the endpoint `/actuator/refresh`.

* ./target/classes/application.properties
```
# You can change any values as you like
test.message=foo bar baz
```

```
$ curl -X POST http://localhost:8080/actuator/refresh
```

# Reference
[Reloading Properties Files in Spring](https://www.baeldung.com/spring-reloading-properties)
[Spring Properties File Outside jar](https://www.baeldung.com/spring-properties-file-outside-jar)
