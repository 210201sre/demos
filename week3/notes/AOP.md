# Aspect Oriented Programming
Different paradigm of programming, where we deal with "Aspects", which are "cross-cutting concerns".

Generally, AOP is used alongside Object Oriented Programming. And these "cross-cutting concerns" are cutting across different packages or layers of our OOP design.

There are certain issues that we must account for in an application that are implemented across many different classes/objects. They are not centralized.
Ex:
- Logging
- Security

Instead of repeating our code in every single class/object, we create "Advice" which will be injected at corresponding "JoinPoints", described as "PointCuts".

An "Aspect" is one of these cross-cutting concerns, such as logging.
"Advice" is the code/functionality/logic that will be injected to address the concern.
"JoinPoints" are the locations that can be injected at. The potential targets for injection.
"PointCuts" are expressions (like Regular Expressions) that describe some subset of the targets, or JoinPoints.

Specifically, the JoinPoints are methods. We can inject functionality before, after, or around methods being invoked.

PointCuts will be some expression to define some collection of method signatures that will be target for some specific scenario of injection.

For example, we can target all of our Constructors for a specific class, and inject some logic before it runs, to log a statement that an object was being created.

Spring has a module that allows us to perform AOP. In general, there is also a dependency called AspectJ that is provided as well, to act as the underlying implementation. Similar to JPA vs Hibernate.


We won't necessarily need to discuss this in a lot of detail, but the idea is very powerful.

For example, we can inject instructions around our Controller methods to catch certain exceptions and send back custom error responses. As our global exception handlers.

To go along with this, Spring has a useful annotation called `@ControllerAdvice` which supports this. Alongside this annotation, there are classes we can extend such as `ResponseEntityExceptionHandler` that provide methods which will already be injected at corresponding locations.