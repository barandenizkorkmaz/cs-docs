# Chapter 3: Inheritance

## 3.1. Classes, Superclasses, and Subclasses

- `Is-a` relationship is the hallmark of inheritance.
- The existing class is called the superclass, base class, or parent class. The new class is called the subclass, derived class, or child class.
- **Subclasses** have more functionality than their superclasses.

---
**NOTE**: Java Language Specification
The Java language specification states: “Members of a class that are declared private are not inherited by subclasses of that class.” This has confused the readers over the years. The specification uses the word “inherits” narrowly. It considers the private fields `non-inherited` because the Manager class cannot access them directly. Thus, every Manager object has three fields from the superclass, but the Manager class does not “inherit” them.

---
- You cannot extend a record, and a record cannot extend another class.
- If subclass methods want to access those private fields, they have to do what every other method does—use the public interface.
	- Use the special keyword `super` for this purpose.
	- `super` is a special keyword that directs the compiler to invoke the superclass method.
		- Some people think of super as being analogous to the this reference. However, that analogy is not quite accurate: `super` is not a reference to an object.
- A subclass can add fields, and it can add methods or override the methods of the superclass. However, inheritance can never take away any fields or methods.
- Since a subclass constructor cannot access the private fields of the parent class, it must initialize them through a `constructor`. The constructor is invoked with the special `super` syntax. The call using super must be the **first** statement in the constructor for the subclass.
	- When a subclass object is constructed without explicit invocation of a super-class constructor, the superclass must have a no-argument constructor.That constructor is invoked prior to the subclass construction.
	- The constructor calls can only occur as the first statement in another constructor.
		- The constructor parameters are either passed to another constructor of the same class (`this`) or a constructor of the superclass (`super`).
- When e refers to an Employee object, the call e.getSalary() calls the getSalary method of the Employee class. However, when e refers to a Manager object, then the getSalary method of the Manager class is called instead. The virtual machine knows about the actual type of the object to which e refers, and therefore can invoke the correct method.
- The fact that an object variable (such as the variable e) can refer to multiple actual types is called `polymorphism`. Automatically selecting the appropriate method at runtime is called `dynamic binding`. 
- In C++, you need to declare a member function as virtual if you want dynamic binding. **In Java, dynamic binding is the default behavior**; if you do not want a method to be virtual, you tag it as `final`.
- Inheritance need not stop at deriving one layer of classes. The collection of all classes extending a common superclass is called an **inheritance hierarchy**.
- In C++, a class can have multiple superclasses. **Java does not support multiple inheritance.**
- A simple rule can help you decide whether or not inheritance is the right design for your data. The`is–a` rule states that every object of the subclass is an object of the superclass. For example, every manager is an employee. Naturally, the opposite is not true—not every employee is a manager.
- Another way of formulating the “is–a” rule is the substitution principle. That principle states that you can use a subclass object whenever the program expects a superclass object.
	- In the Java programming language, object variables are `polymorphic`: A variable of type Employee can refer to an object of type Employee or to an object of any subclass of the Employee class (such as Manager, Executive, Secretary, and so on).
	- However, you cannot assign a superclass reference to a subclass variable. The reason is clear: Not all employees are managers.

---

**Understanding Method Calls**

It is important to understand exactly how a method call is applied to an object. Let’s say we call x.f(args), and the implicit parameter x is declared to be an object of class C. Here is what happens:
1. The compiler looks at the declared type of the object and the method name. Note that there may be multiple methods, all with the same name, f, but with different parameter types. For example, there may be a method f(int) and a method f(String). The compiler enumerates all methods called f in the class C and all accessible methods called f in the superclasses of C. (Private methods of the superclass are not accessible.) **Now the compiler knows all possible candidates for the method to be called.**
2. Next, the compiler determines the types of the arguments supplied in the method call. If among all the methods called f there is a unique method whose parameter types are a best match for the supplied arguments, that method is chosen to be called. This process is called **overloading resolution**. For example, in a call x.f("Hello"), the compiler picks f(String) and not f(int). The situation can get complex because of type conversions (int to double, Manager to Employee, and so on). If the compiler cannot find any method with matching parameter types or if multiple methods all match after applying conversions, the compiler reports an error. **Now the compiler knows the name and parameter types of the method that needs to be called.**
3. If the method is `private`, `static`, `final`, or a `constructor`, then the compiler knows exactly which method to call. This is called **static binding**. Otherwise, the method to be called depends on **the actual type of the implicit parameter**, and **dynamic binding** must be used at runtime. In our example, the compiler would generate an instruction to call f(String) with dynamic binding.
	-  Subclasses do not have access to `private` methods, thus cannot be `overridden`.
		- Recall: The Java language specification states: “Members of a class that are declared private are not inherited by subclasses of that class.”
4. When the program runs and uses dynamic binding to call a method, the virtual machine must call the version of the method that is appropriate for the actual type of the object to which x refers. Let’s say the actual type is D, a subclass of C. If the class D defines a method f(String), that method is called. If not, D’s superclass is searched for a method f(String), and so on.

---

- When you override a method, the subclass method **must** be at least as **visible** as the superclass method. In particular, if the superclass method is public, the subclass method must also be declared public.

---
**The `final` Keyword**
 Classes that cannot be extended are called final classes.
	- You can also make a specific method in a class final. If you do this, then no subclass can override that method.
		- All methods in a final class are automatically final.
	- Fields can also be declared as `final`. A final field cannot be changed after the object has been constructed. **However, if a class is declared final, only the methods, not the fields, are automatically final.**
	- **Enumerations** and **records** are always `final`—you cannot extend them.

---

---
**Casting**
The process of forcing a conversion from one type to another is called `casting`.
- There is only one reason why you would want to make a cast—to use an object in its full capacity after its actual type has been temporarily forgotten.
- You can cast only within an inheritance hierarchy.
- Use `instanceof` to check before casting from a superclass to a subclass.
	- This check returns a boolean value.
- The getSalary method will work correctly on both objects of both classes. The dynamic binding that makes **polymorphism** work locates the correct method automatically.
- The only reason to make the cast is to use a method that is unique to a subclass, therefore the compiler can know about the method definitions.
- Pattern Matching for `instanceof`

```java
if (staff[i] instanceof Manager boss){
	boss.setBonus(5000);
}
```
- The variable-declaring `instanceof` form is called `pattern-matching`. It is similar to type patterns in `switch`.

```java
String description = switch (e){
	case Executive exec -> "An executive with a fancy title of " + exec.getTitle();
	case Manager m -> "A manager with a bonus of " + m.getBonus();
	default -> "A lowly employee with a salary of " + e.getSalary();
}
```
---

---
**Protected Access**
- A subclass cannot access the `private` fields of its superclass.
- A `protected` field is accessible by any class in the same package.
	- Now consider an Administrator subclass in a different package. The methods of the Administrator class can peek inside the hireDay field of Administrator objects only, not of other Employee objects. This restriction is made so that you can’t abuse the protected mechanism by forming subclasses just to gain access to the protected fields.

Here is a summary of the four access control modifiers in Java:

1. `private`: Accessible in the class only.
2. `public`: Accessible by the world.
3. `protected`: Accessible in the package and all subclasses.
4. `default`: Accessible in the package—the (unfortunate) default. No modifiers are needed.

---

## 3.2. `Object`: The Cosmic Superclass

- The ultimate superclass `Object` is taken for granted if no superclass is explicitly mentioned.
- You can use a variable of type Object to refer to objects of any type:
```java
Object obj = new Employee("Harry", 35000);
```
- A variable of type `Object` is only useful as a generic holder for arbitrary values. To do anything specific with the value, you need to have some knowledge about the original type and apply a cast:
```java
Employee e = (Employee) obj;
```

- **The `equals` Method**
	- The equals method in the `Object` class tests whether one object is considered equal to another.
	- When you define the equals method for a subclass, **first** call equals on the `super-class`. If that test doesn’t pass, then the objects can’t be equal. If the superclass fields are equal, you are ready to compare the instance fields of the subclass.

Here is a recipe for writing the perfect `equals` method:
1. Name the explicit parameter `otherObject`—later, you will need to cast it to another variable that you should call other.
2. Test whether `this` happens to be identical to `otherObject` (same address location):
```java
if (this == otherObject) return true;
```
3. Test whether `otherObject` is `null` and return false if it is. This test is required.
```java
if (otherObject == null) return false;
```
4. Compare the classes of `this` and `otherObject`. If the semantics of `equals` can change in subclasses, use the `getClass` test.
```java
if (getClass() != otherObject.getClass()) return false;
ClassName other = (ClassName) otherObject;
```
If the same semantics holds for all subclasses, you can use an `instanceof` test:
```java
if (!(otherObject instanceof ClassName other)) return false;
```
5. Now compare the fields, as required by your notion of equality. Use `==` for **primitive** type fields, `Objects.equals` for **object** fields. Return true if all fields match, false otherwise. If you redefine equals in a **subclass**, include a call to `super.equals(other)`.
```java
return field1 == other.field1 && Objects.equals(field2, other.field2) && . . .;
```

---
**TIP**:

If you have fields of array type, you can use the static `Arrays.equals` method to check that the corresponding array elements are equal. Use the `Arrays.deepEquals` method for multidimensional arrays.

---

---

**TIP**: @Override

```java
public class Employee
{

public boolean equals(Employee other)
{
	return other != null
		&& getClass() == other.getClass()
		&& Objects.equals(name, other.name)
		&& salary == other.salary
		&& Objects.equals(hireDay, other.hireDay);
}

. . .

}
```

This method declares the explicit parameter type as **Employee**. As a result, it **does not override the equals method of the Object class** but defines a completely unrelated method.

You can protect yourself against this type of error by tagging methods that are intended to override superclass methods with @Override:

```java
@Override
public boolean equals(Object other)
```

If you made a mistake and are defining a new method, the compiler reports an error.

---

- **The `hashCode` Method**
	- A hash code is an integer that is derived from an object. Hash codes should be scrambled—if x and y are two distinct objects, there should be a high probability that x.hashCode() and y.hashCode() are different.
	- The hashCode method is defined in the Object class. Therefore, every object has a default hash code. That hash code is derived from the object’s memory address.
	- A `record` type automatically provides a `hashCode` method that derives a hash code from the hash codes of the field values.
- **The `toString Method
	- Another important method in `Object` is the `toString` method that returns a string representing the value of this object.
	- If x is any object and you call `System.out.println(x);` then the `println` method simply calls `x.toString()` and prints the resulting string.

---
**CAUTION**
Annoyingly, arrays inherit the toString method from Object, with the added twist that the array type is printed in an archaic format. For example,

```java
int[] luckyNumbers = { 2, 3, 5, 7, 11, 13 };
String s = "" + luckyNumbers;
```

yields the string `[I@1a46e30`. (The prefix `[I` denotes an array of integers.) The remedy is to call the static `Arrays.toString` method instead. The code

```java
String s = Arrays.toString(luckyNumbers);
```

yields the string `[2, 3, 5, 7, 11, 13]`. 
To correctly print multidimensional arrays (that is, arrays of arrays), use `Arrays.deepToString`.

---

#### java.lang.Object

| Modifier and Type  | Method                        | Description |
|--------------------|-----------------------------|------------|
| `Class`           | `getClass()`                 | Returns a class object that contains information about the object. Java has a runtime representation for classes that is encapsulated in the `Class` class. |
| `boolean`         | `equals(Object otherObject)`  | Compares two objects for equality; returns `true` if the objects point to the same area of memory, and `false` otherwise. You should override this method in your own classes. |
| `String`          | `toString()`                 | Returns a string that represents the value of this object. You should override this method in your own classes. |

#### java.lang.Class

| Modifier and Type  | Method           | Description |
|--------------------|-----------------|-------------|
| `String`          | `getName()`      | Returns the name of this class. |
| `Class`          | `getSuperclass()` | Returns the superclass of this class as a `Class` object. |

## 3.3. Generic Array Lists

ArrayList is a generic class with a type parameter. To specify the type of the element objects that the array list holds, you append a class name enclosed in angle brackets, such as `ArrayList<Employee>`.

```java
// ArrayList Initialization
ArrayList<Employee> staff = new ArrayList<Employee>();

var staff = new ArrayList<Employee>();

ArrayList<Employee> staff = new ArrayList<>();
```

- Before Java 5, there were no generic classes. Instead, there was a single ArrayList class, a one-size-fits-all collection holding elements of type Object. You can still use ArrayList without a <. . .> suffix. It is considered a “raw” type, with the type parameter erased.
- When there were no generic classes, the get method of the raw ArrayList class had no choice but to return an Object. Consequently, callers of get had to cast the returned value to the desired type
```java
Employee e = (Employee) staff.get(i);
```

## 3.4. Object Wrappers and Autoboxing

- Occasionally, you need to convert a primitive type like int to an object.
- All primitive types have class counterparts.
- These kinds of classes are usually called `wrappers`.
- The wrapper classes have obvious names: `Integer`, `Long`, `Float`, `Double`, `Short`, `Byte`, `Character`, and `Boolean`. 
	- (The first six inherit from the common superclass `Number`.)
- The wrapper classes are **immutable** — you cannot change a wrapped value after the wrapper has been constructed. 
- They are also `final`, so **you cannot subclass them**.

---
**Autoboxing**

```java
list.add(3);
```

is automatically translated to

```java
list.add(Integer.valueOf(3));
```

This conversion is called **autoboxing**.

Conversely, when you assign an Integer object to an int value, it is automatically **unboxed**. That is, the compiler translates

```java
int n = list.get(i);
```

into

```java
int n = list.get(i).intValue();
```

The `==` operator, applied to wrapper objects, only tests whether the objects have identical memory locations. The following comparison would therefore probably fail:

```java
Integer a = 1000;
Integer b = 1000;
if (a == b) . . .
```

The remedy is to call the `equals` method when comparing wrapper objects.

Don’t use the wrapper class constructors. They are deprecated and scheduled for removal. For example, use `Integer.valueOf(1000)`, **never** `new Integer(1000)`. Or, simply rely on **autoboxing**: `Integer a = 1000`.

To convert a string to an integer, use the following statement:

```java
int x = Integer.parseInt(s);
```

---

---
#### java.lang.Integer

| Modifier and Type  | Method                            | Description |
|--------------------|----------------------------------|-------------|
| `int`             | `intValue()`                     | Returns the value of this `Integer` object as an `int` (overrides the `intValue` method in the `Number` class). |
| `static String`   | `toString(int i)`                | Returns a new `String` object representing the number `i` in base 10. |
| `static String`   | `toString(int i, int radix)`     | Returns a representation of the number `i` in the base specified by the `radix` parameter. |
| `static int`      | `parseInt(String s)`             |  |
| `static int`      | `parseInt(String s, int radix)`  | Returns the integer whose digits are contained in the string `s`. The string must represent an integer in base 10 (for the first method) or in the base given by the `radix` parameter (for the second method). |
| `static Integer`  | `valueOf(String s)`              |  |
| `static Integer`  | `valueOf(String s, int radix)`   | Returns a new `Integer` object initialized to the integer whose digits are contained in the string `s`. The string must represent an integer in base 10 (for the first method) or in the base given by the `radix` parameter (for the second method). |

---

## 3.5. Methods with a Variable Number of Parameters

It is possible to provide methods that can be called with a variable number of parameters. (These are sometimes called “varargs” methods.)

The printf method is defined like this:

```java
public class PrintStream

{

	public PrintStream printf(String fmt, Object... args)
	{
		return format(fmt, args);
	}

}
```

Here, the ellipsis ... is part of the Java code. It denotes that the method can receive an arbitrary number of objects. The printf method actually receives two parameters: the format string and an `Object[]` array that holds all other parameters. In other words, for the implementor of printf, the Object... parameter type is exactly the same as `Object[]`. The compiler needs to transform each call to printf, bundling the parameters into an array and **autoboxing** as necessary:

```java
System.out.printf("%d %s", new Object[] { Integer.valueOf(n), "widgets" } );
```

## 3.6. Abstract Classes

- As you move up the inheritance hierarchy, classes become more general and probably more abstract. At some point, the ancestor class becomes so general that you think of it more as a basis for other classes than as a class with specific instances you want to use.
- If you use the abstract keyword, you do not need to implement the method at all. 
- For added clarity, a class with one or more abstract methods must itself be declared `abstract`.

```java
public abstract class Person 
{

	. . .
	
	public abstract String getDescription();

}
```

- In addition to abstract methods, **abstract classes can have fields and concrete methods.** For example, the Person class stores the name of the person and has a concrete method that returns it.

```java
public abstract class Person

{

	private String name;
	
	public Person(String name){
		this.name = name;
	}
	
	public abstract String getDescription();
	
	public String getName(){
		return name;
	}

}
```

- You should always move common fields and methods (whether abstract or not) to the superclass (whether abstract or not).
- **Abstract methods act as placeholders for methods that are implemented in the subclasses.** When you extend an abstract class, you have two choices. You can leave some or all of the abstract methods **undefined**; then, **you must tag the subclass as abstract as well**. Or, you can define all methods, and the subclass is no longer abstract.
	- An abstract class can extend another abstract class.
- A class can even be declared as abstract though it has no abstract methods.
- Abstract classes **cannot** be instantiated.
- Note that you can still create object variables of an abstract class, but such a variable must refer to an object of a non-abstract subclass.

```java
Person p = new Student("Vince Vu", "Economics");
```

- Isn’t this a call to an undefined method? Keep in mind that the variable p never refers to a Person object because it is impossible to construct an object of the abstract Person class. The variable p always refers to an object of a concrete subclass such as Employee or Student. For these objects, the getDescription method is defined.

```java
var people = new Person[2];

people[0] = new Employee(. . .);people[1] = new Student(. . .);

for (Person p : people)
	System.out.println(p.getName() + ", " + p.getDescription());
```

Abstract methods are an important concept in the Java programming language. You will encounter them most commonly inside interfaces.

## 3.7. Enumeration Classes

