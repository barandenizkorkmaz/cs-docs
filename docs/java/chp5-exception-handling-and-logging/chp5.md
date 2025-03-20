# Chapter 5: Exception Handling, Assertions, and Logging

- In the Java programming language, an exception object is always an instance of a class derived from `Throwable`.
	- The `Error` hierarchy describes internal errors and resource exhaustion situations inside the Java runtime system.
	- The `Exception` hierarchy also splits into two branches: exceptions that derive from `RuntimeException` and those that do not. The general rule is this: A `RuntimeException` happens because you made a programming error. Any other exception occurs because a bad thing, such as an I/O error, happened to your otherwise good program.
	- The Java Language Specification calls any exception that derives from the class `Error` or the class `RuntimeException` an **unchecked exception**. 
	- All other exceptions are called checked exceptions. The compiler checks that you provide exception handlers for all checked exceptions.


![[exception-hierarchy.png]]

- As with Java methods that are part of the supplied classes, you declare that your method may throw an exception with an exception specification in the method header.
- In summary, a method must declare all the checked exceptions that it might throw. Unchecked exceptions are either beyond your control (`Error`) or result from conditions that you should not have allowed in the first place (`RuntimeException`). If your method fails to faithfully declare all checked exceptions, the compiler will issue an error message.
- Once a method throws an exception, it does not return to its caller. This means you do not have to worry about cooking up a default return value or an error code.