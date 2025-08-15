# spring_boot_batch_demo

```
curl https://start.spring.io/starter.zip \
  -d dependencies=web,batch \
  -d type=maven-project \
  -d language=java \
  -d groupId=com.github.TsutomuNakamura.spring_boot_batch_demo \
  -d artifactId=spring_boot_batch_demo \
  -d name=spring_boot_batch_demo \
  -d packageName=com.github.TsutomuNakamura.spring_boot_batch_demo \
  -o batch-demo.zip
```

# Run the Application
```bash
$ ./mvnw clean compile spring-boot:run
......
2025-07-27T23:25:23.404+09:00  INFO 321459 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2025-07-27T23:25:23.466+09:00  INFO 321459 --- [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection conn0: url=jdbc:h2:mem:1ad83639-f5fa-43ca-82fd-a5fcceb595a4 user=SA
2025-07-27T23:25:23.467+09:00  INFO 321459 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
......
2025-07-27T23:25:23.697+09:00  INFO 321459 --- [           main] c.g.t.s.PersonItemProcessor              : Converting (Person[firstName=Jill, lastName=Doe]) into (Person[firstName=JILL, lastName=DOE])
2025-07-27T23:25:23.698+09:00  INFO 321459 --- [           main] c.g.t.s.PersonItemProcessor              : Converting (Person[firstName=Joe, lastName=Doe]) into (Person[firstName=JOE, lastName=DOE])
2025-07-27T23:25:23.698+09:00  INFO 321459 --- [           main] c.g.t.s.PersonItemProcessor              : Converting (Person[firstName=Justin, lastName=Doe]) into (Person[firstName=JUSTIN, lastName=DOE])
2025-07-27T23:25:23.701+09:00  INFO 321459 --- [           main] c.g.t.s.PersonItemProcessor              : Converting (Person[firstName=Jane, lastName=Doe]) into (Person[firstName=JANE, lastName=DOE])
2025-07-27T23:25:23.701+09:00  INFO 321459 --- [           main] c.g.t.s.PersonItemProcessor              : Converting (Person[firstName=John, lastName=Doe]) into (Person[firstName=JOHN, lastName=DOE])
......
2025-07-27T23:25:23.709+09:00  INFO 321459 --- [           main] .g.t.s.JobCompletionNotificationListener : !!! JOB FINISHED! Time to verify the results
2025-07-27T23:25:23.711+09:00  INFO 321459 --- [           main] .g.t.s.JobCompletionNotificationListener : Found <Person[firstName=JILL, lastName=DOE]> in the database.
2025-07-27T23:25:23.711+09:00  INFO 321459 --- [           main] .g.t.s.JobCompletionNotificationListener : Found <Person[firstName=JOE, lastName=DOE]> in the database.
2025-07-27T23:25:23.711+09:00  INFO 321459 --- [           main] .g.t.s.JobCompletionNotificationListener : Found <Person[firstName=JUSTIN, lastName=DOE]> in the database.
2025-07-27T23:25:23.711+09:00  INFO 321459 --- [           main] .g.t.s.JobCompletionNotificationListener : Found <Person[firstName=JANE, lastName=DOE]> in the database.
2025-07-27T23:25:23.711+09:00  INFO 321459 --- [           main] .g.t.s.JobCompletionNotificationListener : Found <Person[firstName=JOHN, lastName=DOE]> in the database.
2025-07-27T23:25:23.711+09:00  INFO 321459 --- [           main] .g.t.s.JobCompletionNotificationListener : Batch job completed successfully. Exiting application.
2025-07-27T23:25:23.712+09:00  INFO 321459 --- [           main] o.s.b.c.l.s.TaskExecutorJobLauncher      : Job: [SimpleJob: [name=importUserJob]] completed with the following parameters: [{}] and the following status: [COMPLETED] in 31ms
```

# References
- [Creating a Batch Service](https://spring.io/guides/gs/batch-processing)
