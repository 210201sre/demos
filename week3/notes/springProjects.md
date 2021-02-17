# Spring Projects
- Spring Projects are built on top of Spring Modules and provide solutions to other issues faced by Enterprise Applications
- There are a variety of Spring Projects

## Spring Boot
- Takes an Opinionated view of building Spring Applications in order to get an app up and running as quickly as possible
    - Follows a principle of "Convention over Configuration"
    - We can follow certain conventions, such as variable naming with camel-case (which we will likely do anyways) and use that instead of writing a lot of xml configuration
- Using Spring Boot makes it very easy to create stand-alone, production-grade Spring-based Applications that you can "just run"

### Benefits/Features
- Embedded Tomcat Server
    - We use `jar` packaging, when we launch it, it will start a TOmcat Server and host the web application at a configured port (default 8080, which is the default for Tomcat)
- Many provided `starter` dependencies
    - These are multiple dependencies that are packaged together, to make configuring our `pom.xml` much easier
- Necessary Pre-configuration will be found in an `application.properties` file
    - Uses properties file syntax (similar to `log4j.properties`)
    - No xml configuration at all
- Some useful dependencies
    - Spring Boot Actuator
        - Provides endpoints for helpful metrics and project information
    - Spring Boot Development Tools (DevTools)
        - Provides developer support, such as hot-reloading the application when files are changed
- Provides some wrapper annotations
    - `@SpringBootApplication` annotation
        - `@Configuration`
            - Allows us to perform further Java Configuration if we need to configure any Spring Beans manually
        - `@EnableAutoConfiguration`
            - Enable Spring Boot's defaults
        - `@ComponentScan`
            - Enables Spring-based stereotype annotations
    - In our main method
        - `SpringApplication.run(Driver.class, args);`

## Spring Data JPA
- Spring Data is a Spring Boot Project which further abstracts away our persistence layer (even past Hibernate and other ORMs)
- Builds on top of Hibernate
- Provides repository support for the Java Persistence API (JPA)
- Eases development of applications that need access to JPA data sources

### Important interfaces
- CrudRepository
    - PagingAndSortingRepository
        - JpaRepository
- These interfaces use Generics to identify between classes and their Primary Key type

```java
public interface UserDAO extends JpaRepository<User, Integer> {

    public Optional<User> findByUsername(String username);
}
```

Spring Data will dynamically construct Repositories based on:
- The Entity
    - The class name
    - The field names
    - The methods
    - The JPA annotations
- The type of the Primary Key
- Then we will use Dependency Injection to inject the implementing repository into 
    any field that uses Autowiring for our interface.

#### Spring Data Rest
- Another project built on top of Spring Data JPA that will construct Repositories, Services, AND Controllers to provide an entire RESTful API based on entities declared with a particular annotation
- This RESTful API even includes Paging and Sorting functionality as well as HATEOS