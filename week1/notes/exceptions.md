# Exception Handling (In Java)

Handling issues that arise during the runtime of a Java application. In Java there are 2 categories of these issues: Errors and Exceptions. Errors are issues at runtime that are not able to be "reasonably recovered from". Exceptions are the opposite, they are issues at runtime tht can be reasonably recovered from.

## Exception Heirarchy

- Throwable
    - Error
        - NoClassDefFoundError
        - OutOfMemoryError
    - *Exception*
        - *RuntimeException*
            - ArithmeticException (division by zero)
            - ArrayIndexOutOfBoundsException
        - IOException
            - FileNotFoundException
        - ClassNotFoundException

## Checked vs Unchecked Exceptions

Exceptions are categorized into Checked and Unchecked exceptions. Checked exceptions are checked at compile-time to see if they are *possible* to occur. While these checked exceptions still would only occur at runtime, the `throws` keyword indicates if these checked exceptions *could* be thrown during runtime. These are potential issues that the developer must be mindful of, and therefore, the compiler forces that these checked exceptions *must* be handled.

Any Exception that inherits from the Exception Class, but does NOT inherit from RuntimeException is considered to be a Checked Exception.

Unchecked Exceptions are any Exception that inherits from RuntimeException (or is RuntimeException itself). These exceptions are still issues that can arise, same as Checked exceptions. Except that the compiler will not care if they can occur or not. We can still handle unchecked exceptions in the same way, it is just not enforced.

The boundary between is sometimes vague, but for the most part, it is based on the potential root cause of these issues. In general, Checked exceptions, are caused by some external factor, that is outside the control of the developer. Whereas RuntimeExceptions are often within the control of the developer.

## Handling Exceptions that occur

- `throw` keyword
    - Designate that an issue is occurring right now
- `throws` keyword
    - Is placed after the method signature and before the curly braces for the method
    - Associated primarily with Checked Exceptions
    - Indicates that an Exception *could* be thrown by a particular method call
        - It also indicates that the handling of this Checked Exception is being handled
            elsewhere
        - Most of the time, it makes sense to delegate the handling elsewhere, as the current method
            may not have sufficient information to decide in what way the exception should be handled
- `try` & `catch` keywords
    - `try` indicates that we will attempt some sequence of instructions that might result in an Exception
    - After a `try` block, we can create a `catch` block that will provide the instructions to "handle" the exceptions that occured.
    - There are no minimum requirements/instructions needed to resolve or handle an exception. Simply by having the `catch` block, the exception will be "resolved"
        - However, this is not always very helpful
        - It is to up to the developer to decide what instructions will be helpful to successfully and properly resolve the exception
        - It largely depends on the situation/context
    - When an exception occurs inside a try block, the flow of execution will stop, and skip forward to the corresponding catch block
    - You CAN catch and resolve `Errors`, but it is a very poor decision. Generally, Errors are extreme enough that your program will not be able to properly function if an Error occurs. It is generally preferable to allow the program to simply terminate instead.

- You can chain multiple `catch` blocks in sequence
    - However, the order of the specific exceptions that are caught is important
    - You must catch exceptions from most specific to more general
        - Otherwise, you will have unreachable code
    - Unrelated exceptions can be caught in any order

- `finally` keyword
    - A block that will execute no matter what
    - This is helpful in some scenarios, such as closing any open streams despite an exception occuring
    - You can have a `try-finally` block, without having any `catch` blocks
    - A `try` block *must* be followed by a `finally` block *or* at least 1 `catch` block
        - Without either a `finally` block or a `catch` block, the `try` block will have a compiler issue
    - If there are `catch` blocks, the `finally` block must be at the end

## Custom Exceptions

We can create our own classes that inherit from some class in the Exception heirarchy. When we do this, we create a custom exception. This exception will operate just as any other exception, and will be classified as checked or unchecked according to its inheritance tree.

You can also create custom errors. They operate in the same way.

## History

In the past, Checked Exceptions were much more commonly used than Uncheked Exceptions. This is because developers were able to force users of their libraries to handle potentially bad scenarios. However, this over time lead to very complicated exception handling logic.

By switching over to unchecked exceptions in many scenarios, developers were able to clean up the heavily obfuscated/complicated exception logic.

Nowadays, most exceptions will be unchecked exceptions, as they don't clutter application logic as much.