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

To understand what cloning means, recall what happens when you make a copy of a variable holding an object reference. The original and the copy are references to the same object. This means a change to either variable also affects the other.

The clone method is a protected method of Object, which means that your code cannot simply call it. Only the Employee class can clone Employee objects. There is a reason for this restriction. Think about the way in which the Object class can implement clone. It knows nothing about the object at all, so it can make only a field-by-field copy. If all instance fields in the object are numbers or other basic types, copying the fields is just fine. But if the object contains references to subobjects, then copying the field gives you another reference to the same subobject, so the original and the cloned objects still share some information.

As you can see, the default cloning operation is “shallow”—it doesn’t clone objects that are referenced inside other objects.

Does it matter if the copy is shallow? It depends. If the subobject shared between the original and the shallow clone is immutable, then the sharing is safe.

Quite frequently, however, subobjects are mutable, and you must redefine the clone method to make a deep copy that clones the subobjects as well.

For every class, you need to decide whether:
1. The default clone method is good enough;
2. The default clone method can be patched up by calling clone on the mutable subobjects; or
3. clone should not be attempted.

The third option is actually the default. To choose either the first or the second option, a class must
1. Implement the Cloneable interface; and
2. Redefine the clone method with the public access modifier.

**The clone method is declared protected in the Object class, so that your code can’t simply call anObject.clone(). But aren’t protected methods accessible from any subclass, and isn’t every class a subclass of Object? Fortunately, the rules for protected access are more subtle. A subclass can call a protected clone method only to clone its own objects. You must redefine clone to be public to allow objects to be cloned by any method.**

A tagging interface has no methods; its only purpose is to allow the use of instanceof in a type inquiry.

Even if the default (shallow copy) implementation of clone is adequate, you still need to implement the Cloneable interface, redefine clone to be public, and call super.clone(). **The clone method that you just saw adds no functionality to the shallow copy provided by Object.clone. It merely makes the method public.**

```java
class Employee implements Cloneable

{

// public access, change return type

public Employee clone() throws CloneNotSupportedException {

	return (Employee) super.clone();

}

	. . .

}
```

Here is an example of a clone method that creates a deep copy:

```java
class Employee implements Cloneable

{

	public Employee clone() throws CloneNotSupportedException {
		
		// call Object.clone()
		
		Employee cloned = (Employee) super.clone();
		
		// clone mutable fields
		
		cloned.hireDay = (Date) hireDay.clone();
		
		return cloned;
	
	}

}
```

The clone method of the Object class threatens to throw a **CloneNotSupportedException**—it does that whenever clone is invoked on an object whose class does not implement the `Cloneable` interface.

You have to be careful about cloning of subclasses. For example, once you have defined the clone method for the Employee class, anyone can use it to clone Manager objects. Can the Employee clone method do the job? It depends on the fields of the Manager class. In our case, there is no problem because the bonus field has primitive type. But Manager might have acquired fields that require a deep copy or are not cloneable. There is no guarantee that the implementor of the subclass has fixed clone to do the right thing. **For that reason, the clone method is declared as protected in the Object class.** But you don’t have that luxury if you want the users of your classes to invoke clone.

---