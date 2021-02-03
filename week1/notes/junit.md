# JUnit

Unit Testing Framework for Java. As mentioned before, unit tests are small scale tests to confirm that the behavior of the application is as expected. They are generally independent from each other, and only test a single unit, or even a single characteristic of a single unit at a time.

JUnit has several versions that are still in use. Version 3 is primarily used by legacy systems. Version 4 is the most popular version.

At some point, a different test framework called TestNG (Test Next Generation) was released that was considered more powerful than JUnit 4, due to more customizable configuration options. In response JUnit released version 5, called JUnit Jupiter. However, Junit 5 is not backwards compatible with JUnit 4 (pretty similar, but certain annotations are renamed, and package locations have changed). As such, many companies did not fully rewrite their tests to transition to JUnit 5, so JUnit 4 is still widely used.

## Annotations

- `@Test`
    - Defines a single Unit Test Case
    - Has a property called `expected` that allows you to define an Exception that should be thrown as a result of this test case
        - Alternative to `assertThrows`
    - Has a property called `timeout` that defines a limit on how long the test case should last
        - If the timeout is exceeded, the test case will fail
        - This allows some low level performance tests
- `@Before` & `@After`
    - Define configuration methods that will be invoked before and after each test case
- `@BeforeClass` & `@AfterClass`
    - Define global configuration methods that will be invoked once, before and after all test cases are executed
- `@Ignore`
    - Skip a specific test case

## Asserts

JUnit has many static methods that are on the `Assert` class. These are generally statically imported so that we do not refer to them through `Assert.assertTrue`, and we instead just use `assertTrue`.
These methods allow the developer to make assertions about what the result of a method should be. If the assertion fails, then the test case will fail.

- `assertTrue` & `assertFalse`
- `assertEquals` & `assertNotEquals`
- `assertThrows`
- `assertSame` & `assertNotSame`
- etc

## Test Driven Development

A paradigm or approach to software development, where the developer writes the unit tests for a particular feature _before_ writing the implementation. The process proceeds by writing tests and then creating implementations to make the tests pass.

This has many benefits in terms of the quality of a codebase. It can even help with the architectural design of an application. You are forced to design your application in a way that is easily testable.

There are some downsides of course, which is that TDD requires a lot more effort on the developer at the beginning. But generally speaking the pros will outweigh the cons.