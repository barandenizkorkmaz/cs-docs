# Chapter 4: Interfaces, Lambda Expressions, and Inner Classes

## 4.1. Interfaces

- An interface is not a class but a set of requirements for the classes that want to conform to the interface.
- All methods of an interface are automatically `public`.
	- However, when implementing the interface, you must declare the method as `public`, as the default method definition makes it package accessible. 
	- If `public` keyword not provided, the compiler then complains that you're trying to supply a more restrictive access privilege.
- **Interfaces can never have instance fields.**
- Interfaces can define constants, e.g. `static final` variables.
	- Fields that are defined in interfaces are always `public static final`.
	```java
	public interface Powered extends Moveable {
		double milesPerGallon();
		double SPEED_LIMIT = 95;
	}
```
- It is possible to have `static` and `private` methods as well as `default` methods 
- Interfaces might be tedious when inheritance comes into play.
	- Since Manager extends Employee, it implements `Comparable<Employee>` and not `Comparable<Manager>`. If Manager chooses to override compareTo, it must be prepared to compare managers to employees.
		```java
class Manager extends Employee {

	public int compareTo(Employee other) {
	
		Manager otherManager = (Manager) other; // NO
	
		. . .
	
	}
	
	. . .

}
```
	- This violates the `antisymmetry` rule: If x is an Employee and y is a Manager, then the call x.compareTo(y) doesn’t throw an exception—it simply compares x and y as employees. But the reverse, y.compareTo(x), throws a `ClassCastException`.
	- There are two possible remedies:
		- If subclasses have different notions of comparison, then you should outlaw comparison of objects that belong to different classes.
		```java
		if (getClass() != other.getClass()) throw new ClassCastException();
```
		- If there is a common algorithm for comparing subclass objects, simply provide a single `compareTo` method in the superclass and declare it as `final`.
- Interfaces cannot be instantiated.
	- However, even though you can’t construct interface objects, you can still declare interface variables (same as **abstract classes**).
	- An interface variable must refer to an object of a class that implements the interface.
- Next, just as you use `instanceof` to check whether an object is of a specific class, you can use `instanceof` to check whether an object implements an interface.
- Just as you can build hierarchies of classes, **you can extend interfaces.**
- While each class can have **only one superclass**, classes **can implement multiple interfaces**.
- **Records and enumeration classes cannot extend other classes (since they implicitly extend the Record and Enum class). However, they can implement interfaces.**
- Interfaces can be `sealed`.
	- As with sealed classes, the direct subtypes (which can be classes or interfaces) must be declared in a permits clause or be located in the same source file.
- **Interfaces vs Abstract Classes**
	- You may wonder why the designers of the Java programming language bothered with introducing the concept of interfaces.
	- There is, unfortunately, a major problem with using an abstract base class to express a generic property. A class can only extend a single class. 
	- But each class can implement as many interfaces as it likes.
	- Other programming languages, in particular C++, allow a class to have more than one superclass. This feature is called **multiple inheritance**.
	- **Java does not support multiple inheritance.**
- **Static and Private Methods**
	- As of Java 8, you are allowed to add static methods to interfaces.
	- Up to now, it has been common to place static methods in companion classes, e.g. `Paths` and `Collections` classes. However, when you implement your own interfaces, there is no longer a reason to provide a separate companion class for utility methods.
	- As of Java 9, methods in an interface can be `private`. A private method can be `static` or an `instance method`. **Since private methods can only be used in the methods of the interface itself, their use is limited to being helper methods for the other methods of the interface.**
- **Default Methods**
	- You can supply a default implementation for any interface method.
	```java
	public interface Comparable<T>
	{

		default int compareTo(T other) { return 0; } // by default, all elements are the same
	
	}
```
	- **Resolving Default Method Conflicts**
		- What happens if the exact same method is defined as a default method in one interface and then again as a method of a superclass or another interface?
		- Rules
			1.  Superclasses win. If a superclass provides a concrete method, default methods with the same name and parameter types are simply ignored.
			2. Interfaces clash. If an interface provides a default method, and another interface contains a method with the same name and parameter types (default or not), then you must resolve the conflict by overriding that method.
- **Interfaces and Callbacks**
	- A common pattern in programming is the callback pattern. In this pattern, you specify the action that should occur whenever a particular event happens.
	- Interfaces allow implementing callbacks by enforcing what a class should do.
	- In many programming languages, you supply the name of a function that the timer should call periodically. However, the classes in the Java standard library take an object- oriented approach. You pass an object of some class. The timer then calls one of the methods on that object. Passing an object is more flexible than passing a function because the object can carry additional information.

---
### Object Cloning and `Cloneable` Interface




---