# Spring Framework
- An open source Java framework that supports the development of enterprise applications
- Utilizes Dependency Injection as an implementation of Inversion of Control
    - Whenever you have a dependency, instead of instantiating it directly,
        you obtain an instance from the IoC (Inversion of Control) Container (or Spring Container)

Specifically, we can turn this code

```java
public class UserService {
    private IUserDAO userDAO = new UserDAO();
}
```

into this:

```java
public class UserService {
    private IUserDAO userDAO;
}
```

- "Spring" is an umbrella term. It refers to the overall framework itself, the modules that up the framework, as well as many projects that are built on top of the framework (and even constructs within the framework, e.g. "Spring Beans")

## Loose Coupling vs Tight Coupling
"Coupling" is a term that means that a class depends on relies on another class. We say that the two classes are "coupled".

Tight Coupling is where the two classes are tied together as specific implementations. Specific classes within their corresponding heirarchy.

Loose Coupling is where we specifically leverage Covariance, to an extreme sense. Generally, relying on Dependency Injection to decide the specific implementation to provide.

## Spring Beans
- Any object whose lifecycle is managed by Spring (The Spring Container / IoC Container)
    - It is insantiated, injected, and eventually allowed to be garbage collected through the control of Spring Framework
    - The developer is not performing any of the above actions
- Will not generally include classes in the `models` package


## Spring Modules
The Spring Framework is subdivided into different modules:

- Spring Beans
- Spring Core
- Spring Context

Spring Core + Spring Beans provide the essential DI features with the `BeanFactory` interface

Spring Context builds on top of Core & Beans to add additional functionality and access to the `ApplicationContext` interface (sub-interface of `BeanFactory`).

Additional Modules:
- Web Module
    - Particular importance for us
- AOP Module
    - Aspect Oriented Programming
    - Allows you to dynamically inject functionality before/after method invocation
- ORM Module
    - Object Relational Mapping (Mapper)
    - Further abstracted away with Spring Data
- JDBC Module
- Etc

## Spring Projects
Spring Projects are built on top of a set of modules from the framework
- Spring Boot
    - Large scale abstraction
    - Provide default configuration for any module that is included
    - Allows you to "get up and running" _very_ rapidly
- Spring Data
    - Abstract the Data Access layer
    - Further abstracts the idea of ORMs, and can provide dynamic implementations automatically
- Spring Security
    - Features in regards to Security
    - HTTPS functionality
    - Working with JWTs
    - CORS and CSRF configurations
    - Authorization & Authentication
        - Authorization is: "Do you have access to this?"
        - Authentication is: "Are you who you say you are?"

## Configuration

There multiple ways in which we can configure Spring Framework. Without Spring Boot, this is of particular importance.

### XML Config
The most legacy approach to Spring Configuration. We have a `applicationContext.xml` file (src > main > resources > `applicationContext.xml`)
    - Before ApplicationContext existed, we used to call the file `beans.xml`

```xml
<bean name="beanA" class="com.revature.services.BeanA"></bean>
```

### Annotation Configuration
1. Enable "Component Scanning" in `applicationContext.xml`
2. Annotate classes with "Stereotype Annotations"
    - `@Component`: general Spring-Managed class
    - `@Repository`: DAO
    - `@Controller`
    - `@Service`
    - Each of the above performs the _exact_ same function
    - The differences in name is just for other developers to understand what the purpose is

### Java Configuration
Introduced the `@Configuration` annotation that can be used along with `@Bean` annotations to register different beans in a centralized location without needing to write any XML.
Also uses `@ComponentScan` to enable to the usage of Stereotype Annotations.

## Spring Bean Lifecycle
0. Request Bean from ApplicationContext
    - Instantiation
    - Populate Properties
1. BeanNameAware's setBeanName method
2. BeanClassLoaderAware's setBeanClassLoader method
3. BeanFactoryAware's setBeanFactory method
    - Only applicable when using ApplicationContext
    1. ResourceLoaderAware's setResourceLoader method
    2. ApplicationEventPublisherAware's setApplicationEventPublisher method
    3. MessageSourceAware's setMessageSource method
    4. ApplicationContextAware's setApplicationContext method
    5. ServletContextAware's setServletContext method
        - Only applicable when running in a WebApplicationContext
4. EnvironmentAware's setEnvironment method
5. beanPostProcessor's postProcessBeforeInitialization method
6. InitializingBean's afterPropertiesSet method
7. Custom init Method
    - Init method in our own bean declaration (applicationContext.xml)
    - `@PostConstruct` annotation
8. BeanPostProcessor's postProcessAfterInitialization method

### On Shutdown
1. DestructionAwareBeanPostProcessor's postProcessBeforeDestruction method
2. DisposableBean's destroy method
3. Custom destroy method
    - Destroy method in our bean declaration (applicationContext.xml)
    - `@PreDestroy`

### Conceptual Spring Bean Lifecycle
- Setup
    - Object is insantiated
    - handle dependencies (assigns values to instance variables)
    - Custom initialization method
    - Spring Bean is ready
- Tear Down
    - Spring's destroy method
    - Custom destroy method

## Bean Scopes

These scopes determine how many instances of a particular bean we create. This depends on the context in which they are used (hence, "scope").

### Universal Scopes
- Singleton (default)
    - There is only 1 instance of the Bean at a time
- Prototype
    - There will be a new Bean instantiated each time it is called for

### Web-Aware Scopes
- Request
    - One instance per HTTP Request
- Session
    - One per HTTP Session
- New as of Spring 5
    - Application
        - One per ServletContext (Per Web Container)
        - Tomcat or Jetty
    - WebSocket
        - One instance per WebSocket
- Now deprecated as of Spring 5
    - Global Session: This was used for Portlets for Global sessions

## Autowiring
- Let Spring decide which instances to use
- The dependencies are wired "automagically"

```java
@Component
public class UserService

    @Autowired // Technically use Reflection
    // Which means won't truly invoke your setter method
    private IUserDAO userDAO;

    // Spring will look to perform type-based "Field Injection"
```

```java
@Component
public class UserService {
    private IUserDAO userDAO;

    @Autowired // Spring will use this setter method
    public void setUserDAO(IUserDAO dao) {
        this.userDAO = dao;
    }
    // This is called "Setter Injection"
}
```

```java
@Component
public class UserService {
    private IUserDAO userDAO;

    @Autowired // Spring will use this constructor
    public UserService(IUserDAO dao) {
        super();
        this.userDAO = dao;
    }
    // This is called "Constructor Injection"
}
```