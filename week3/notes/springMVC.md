# Spring MVC
- Deals with the MVC Design Pattern
    - Model: Data being passed, rendered, and manipulated
    - View: What will be displayed (html/css)
    - Controller: Handles logic & routing

The entire MVC architecture is built on top of Java's Servlet API.
Servlets are a Java API to handle HTTP requests directly. Applications that use this API, are generally deployed onto external Web Servers, and are packaged as `war` files.

Spring MVC follows a Design Pattern called "FrontController" where there is only a single Servlet class that handles all of the HTTP routing.

- SpringMVC will create a single Servlet class called "DispatcherServlet"

## Important Components
- DispatcherServlet
    - Only Servlet that we have
    - Provided to us
    - Follows the FrontController Design Pattern
- HandlerMapping (interface)
    - Handle routing of requests from url prefixes to specific Controller classes
    - Our Controller classes will use annotations to configure the specific routes for the HandlerMapping
    - `@Controller` (Stereotype Annotation)
    - `@RequestMapping(parameters)`
        - `@GetMapping`
        - `@PostMapping`
        - `@PutMapping`
        - etc
- ViewResolver (interface)
    - Handle the routing of static resources if the server has any
    - ex implementation
        - `InternalResourceViewResolver`

```java
@Controller
public class TestController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "test";
    }
}
```

The example above is still using the ViewResolver. It will take the "test" string that was returned and use it to locate some html file, such as "/static/test.html".

If we wanted to bypass the ViewResolver, we leverage the `@ResponseBody` annotation. This will skip the ViewResolver, and instead cause the method to return the contents as the body of the response. Generally, this will leverage a library like Jackson Databind to convert the contents to JSON.

```java
@Controller
public class TestController {

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "test";
    }
}
```

This method will now just return JSON contents of the string "test".

To go along with this, Spring provides some helpful classes, such as `ResponseEntity`, which leverages Generics. This class allows us to specify specific response status codes, and other configurations.

```java
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("test");
        // Response Status Code 200, with the body containing the JSON string of "test"
    }
}
```