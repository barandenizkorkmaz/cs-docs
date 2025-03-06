# Chapter 2: Objects and Classes

## 2.1. Introduction to Object-Oriented Programming


4 Pillars of OOP:
1. Abstraction: The process of hiding implementation details and exposing only the functionality to the user. In abstraction, we deal with ideas and not events. This means the user will only know “what it does” rather than “how it does”.
2. Encapsulation: In software systems, **encapsulation** refers to the bundling of data with the mechanisms or methods that operate on the data. It may also refer to the limiting of direct access to some of that data, such as an object's components. Essentially, encapsulation prevents external code from being concerned with the internal workings of an object.
3. Inheritance: The process of one class inheriting properties and methods from another class in Java. Inheritance is used when we have ***is-a*** relationship between objects.  Inheritance in Java is implemented using ***extends*** keyword.
4. Polymorphism: Polymorphism in Java is **a core concept of object-oriented programming (OOP) that allows objects to be treated as instances of their parent class**. It facilitates flexibility and the ability to define methods in multiple forms. Polymorphism is primarily achieved through method overriding and method overloading.

There are two types of polymorphism as listed below:

1. Static or Compile-time Polymorphism
2. Dynamic or Run-time Polymorphism

***Static or Compile-time Polymorphism*** occurs when the compiler is able to determine the actual function, it’s called ***compile-time** polymorphism. Compile-time polymorphism can be achieved by ***method overloading*** in Java. When different functions in a class have the same name but different signatures, it’s called **method overloading**. A method signature contains the name and method arguments. So, overloaded methods have different arguments. The arguments might differ in the numbers or the type of arguments.
***Dynamic or Run-time Polymorphism*** occurs when the compiler is not able to determine at compile-time which method (superclass or subclass) will be called. This decision is made at run-time. Run-time polymorphism is achieved through **method overriding**, which happens when a method in a subclass has the same name, return type, and parameters as a method in its superclass. When the superclass method is overridden in the subclass, it is called method overriding.
### 2.1.1. Classes

A class is a blueprint for objects. In Java, all classes extend the cosmic superclass called `Object.
### 2.1.2. Objects

An object is an instance of the class, which has consists of a `state` and `behavior`.

### 2.1.3. Identifying Classes

In a traditional procedural program, you start the process at the top, with the `main` function. When designing an OOP system, there is no `top` and newcomers to OOP often wonder where to begin. A simple rule of thumb in identifying classes is to look for `nouns` in the problem analysis. Methods, on the other hand, correspond to `verbs`.

### 2.1.4. Relationships Between Classes

The most common relationships between classes are:
- Dependence (`uses-a`)
- Aggregation (`has-a`)
- Inheritance (`is-a`)

**NOTE:** UML notations are used for describing the relationships between classes.

## 2.2. Using Predefined Classes

### 2.2.1. Objects and Object Variables

There is an important difference between objects and object variables.

```java
Date startTime; // startTime doesn't refer to any object
```

The statement defines an object variable, `startTime`, that can refer to objects of type `Date`. It is important to realize that the variable `startTime` is not an object and, in fact, does not even refer to an object yet. You cannot use any `Date` methods on this variable at this time. The statement

```java
s = startTime.toString();
```

would cause a compile-time error.

You must first initialize the `startTime` variable. It is important to realize that **an object variable doesn't actually contain an object**. It only refers to an object.
In Java, the value of any object variable is **a reference to an object** that is stored elsewhere. The return value of the `new` operator is also a **reference**. You can explicitly set an object variable to `null` to indicate that it currently refers to no object.

---
**C++ NOTE**

Some people mistakenly believe that Java object variables behave like C++ references. But in C++ there are no null references and references cannot be assigned. You should think of Java object variables as analogous to `object pointers` in C++. 

Date rightNow; // Java

is really the same as

Date* rightNow; // C++

Of course, a `Date*` pointer isn't initialized until you initialize it with a call to `new`. The syntax is almost the same in C++ and Java.

Date* rightNow = new Date(); // C++

If you copy one variable to another, then both variables refer to the same date-- they are pointers to the same object. The equivalent of the Java null reference is the C++ NULL pointer.

All Java objects live on the `heap`. When an object contains another object variable, it contains just a pointer to yet another heap object.

---



### 2.2.2. The `Date` and `LocalDate` Class of the Java Library

An instance of the `Date` class has a state-- namely, a particular point in time. The standard Java library contains two separate classes: the `Date` class, which represents a point in time, and the `LocalDate` class, which expresses days in the familiar calendar notation.

```java
LocalDate.now();
LocalDate.of(1999, 12, 31);

LocalDate newYearsEve = LocalDate.of(1999, 12, 31);
int year = newYearsEve.getYear();
int month = newYearsEve. getMonthValue();
int day = newYearsEve.getDayOfMonth();

LocalDate aThousandDaysLater = newYearsEve.plusDays(1000);
```

| Modifier and Type               | Method                            | Description |
|---------------------------------|-----------------------------------|------------|
| `static LocalDate`              | `now()`                           | Constructs an object that represents the current date. |
| `static LocalDate`              | `of(int year, int month, int day)` | Constructs an object that represents the given date. |
| `int`                           | `getYear()`                       |  |
| `int`                           | `getMonthValue()`                 |  |
| `int`                           | `getDayOfMonth()`                 | Gets the year, month, and day of this date. |
| `DayOfWeek`                     | `getDayOfWeek()`                  | Gets the weekday of this date as an instance of the `DayOfWeek` class. Call `getValue()` on the `DayOfWeek` instance to get a weekday between 1 (Monday) and 7 (Sunday). |
| `LocalDate`                     | `plusDays(int n)`                 |  |
| `LocalDate`                     | `minusDays(int n)`                | Yields the date that is `n` days after or before this date. |

# 2.3. Defining Your Own Classes

---
**C++ NOTE**

Constructors work the same way in Java as they do in C++. Keep in mind, however, that all Java objects are constructed on the heap and that a constructor must be combined with new. It is a common error of C++ programmers to forget the new operator:

```cpp
Employee number007("James Bond", 100000, 1950, 1, 1); // C++, not Java!
```

That works in C++ but not in Java.

---


### 2.3.1. Declaring Local Variables with `var`

As of Java 10, you can declare local variables with the `var` keyword instead of specifying their type, provided their type can be inferred from the initial value. For example, instead of declaring

```java
Employee harry = new Employee("Harry Hacker", 50000, 1989, 10, 1);
```

you simply write

```java
var harry = new Employee("Harry Hacker", 50000, 1989, 10, 1);
```

This is nice since it avoids the repetition of the type name Employee. Note that the var keyword can only be used with **local variables inside methods**. You must always declare the types of parameters and fields.

### 2.3.2. Working with `null` References

The Objects class has a convenience method for this purpose:

```java
public Employee(String n, double s, int year, int month, int day){

	name = Objects.requireNonNullElse(n, "unknown");

	. . .

}

```

The “tough love” approach is to reject a null argument:

```java
public Employee(String n, double s, int year, int month, int day){

	name = Objects.requireNonNull(n, "The name cannot be null");

	. . .

}
```

### 2.3.3. Implicit and Explicit Parameters

Any class method has two kinds of parameters. The first type of parameter, called the `implicit parameter`, is the callee object itself that appears before the method name. The second kind of parameter, inside the parentheses after the method name, is an `explicit parameter`. (Some people call the implicit parameter the target or receiver of the method call.) 

The implicit parameter does not appear in the method declaration. In every method, the keyword `this` refers to `the implicit parameter`.  Some programmers prefer that style because it clearly distinguishes between instance fields and local variables.

---

**C++ NOTE:**

In C++, you generally define methods outside the class:

```cpp
void Employee::raiseSalary(double byPercent) // C++, not Java
{
	. . .
}
```

If you define a method inside a class, then it is, automatically, an inline method.

```cpp
class Employee{
	. . .
	int getName() { return name; } // inline in C++
}
```

---

**In Java, all methods are defined inside the class itself.** This does not make them inline. Finding opportunities for inline replacement is the job of the Java virtual machine. The just-in-time compiler watches for calls to methods that are short, commonly called, and not overridden, and optimizes them away.

### 2.3.4. Benefits of Encapsulation

Two kinds of class methods:
- Accessor methods
- Mutator methods

---
**CAUTION:**

Be careful not to write accessor methods that return references to mutable objects. In a previous edition of this book, I violated that rule in the `Employee` class in which the `getHireDay` method returned an object of class Date:

```java
class Employee{
	
	private Date hireDay;
	
	. . .
	
	public Date getHireDay(){
		return hireDay; // BAD
	}
	
	. . .
	
}
```

```java
class Employee{
	
	private Date hireDay;
	
	. . .
	
	public Date getHireDay(){
		return (Date) hireDay.clone(); // OK
	}
	
	. . .
	
}
```

As a rule of thumb, always use `clone` whenever you need to return a copy of a mutable field.

---

### 2.3.5. Final Instance Fields

You can define an instance field as `final`. **Such a field must be initialized when the object is constructed.** That is, you must guarantee that the field  value has been set after the end of every constructor. Afterwards, the field may not be modified again. 

The final modifier is particularly useful for fields whose type is primitive or an `immutable` class. A class is `immutable` if **none of its methods ever mutate its objects**. For example, the String class is immutable. For mutable classes, the final modifier can be confusing. For example, consider a field:

```java
private final StringBuilder evaluations;
```

that is initialized in the `Employee` constructor as

```java
evaluations = new StringBuilder();
```

The final keyword merely means that the object reference stored in the evaluations variable will **never again refer to a different StringBuilder object**. But the object can be mutated:

```java
public void giveGoldStar(){
	evaluations.append(LocalDate.now() + ": Gold star!\n");
}
```

## 2.4. Static Fields and Methods

### 2.4.1. Static Fields

If you define a field as static, then the field is not present in the objects of the class. There is only a single copy of each static field. You can think of static fields as belonging to the class, not to the individual objects.

---
**NOTE:**
In some object-oriented programming languages, static fields are called class fields. The term “static” is a meaningless holdover from C++.

---

### 2.4.2. Static Constants

Static variables are quite rare. However, static constants are more common.
```java
public class Math {

	public static final double PI = 3.14159265358979323846;
	
}
```
You can access this constant in your programs as Math.PI.

If the keyword `static` had been omitted, then PI would have been an instance field of the `Math` class. That is, you would need an object of this class to access PI, and every Math object would have its own copy of PI.

Another static constant that you have used many times is `System.out`. It is declared in the System class as follows:

```java
public class System {
	
	public static final PrintStream out = . . .;
	
}
```

As mentioned several times, it is never a good idea to have public fields, because everyone can modify them. However, public constants (that is, final fields) are fine. Since out has been declared as final, you cannot reassign another print stream to it:

```java
System.out = new PrintStream(. . .); // ERROR--out is final
```

### 2.4.3. Static Methods

Static methods are methods that do not operate on objects. You can think of static methods as methods that don’t have a this parameter. (In a nonstatic method, the this parameter refers to the implicit parameter of the method.)

A `static` method of the Employee class **cannot** access the id `instance field` because it does not operate on an object. However, a static method can access a static field.

Use static methods in two situations:
- When a method doesn’t need to access the object state because all needed parameters are supplied as explicit parameters (example: Math.pow).
- When a method only needs to access static fields of the class (example: Employee.advanceId).

---
**C++ NOTE**

Static fields and methods have the same functionality in Java and C++. However, the syntax is slightly different. In C++, you use the :: operator to access a static field or method outside its scope, such as Math::PI.

---

### 2.4.4. Factory Methods

Here is **another common use for static methods**. Classes such as `LocalDate` and `NumberFormat` use **static factory methods that construct objects**. You have already seen the factory methods `LocalDate.now` and `LocalDate.of`. 

Why doesn’t the `NumberFormat` class use a constructor instead? There are two reasons:
- You can’t give names to constructors. The constructor name is always the same as the class name. But we want two different names to get the currency instance and the percent instance.
- When you use a constructor, you can’t vary the type of the constructed object. But the factory methods actually return objects of the class DecimalFormat, a subclass that inherits from NumberFormat.

### 2.4.5. The `main` Method

Every class **can** have a `main` method. That is a handy trick for adding demonstration code to a class.

To see a demo of the Employee class, simply execute
```
java Employee
```

If the Employee class is a part of a larger application, you start the application with
```
java Application
```
and the main method of the Employee class is never executed.

| Modifier and Type | Method                                                      | Description                                                                                            |
| ----------------- | ----------------------------------------------------------- | ------------------------------------------------------------------------------------------------------ |
| `static <T> void` | `requireNonNull(T obj)`                                     |                                                                                                        |
| `static <T> void` | `requireNonNull(T obj, String message)`                     |                                                                                                        |
| `static <T> void` | `requireNonNull(T obj, Supplier<String> messageSupplier)`   | If `obj` is `null`, these methods throw a `NullPointerException` with no message or the given message. |
| `static <T> T`    | `requireNonNullElse(T obj, T defaultObj)`                   |                                                                                                        |
| `static <T> T`    | `requireNonNullElseGet(T obj, Supplier<T> defaultSupplier)` | Returns `obj` if it is not `null`, or the default object if `obj` is `null`.                           |

## 2.5. Method Parameters

Let us review the computer science terms that describe how parameters can be passed to a method (or a function) in a programming language. The term `call by value` means that the method gets just the value that the caller provides. In contrast, `call by reference` means that the method gets the location of the variable that the caller provides. Thus, a method can modify
the value stored in a variable passed by reference but not in one passed by value. 

These “call by . . .” terms are standard computer science terminology describing the behavior of method parameters in various programming languages, not just Java. (There is also a `call by name` that is mainly of  historical interest, being employed in the Algol programming language, one of the oldest high-level languages.)

The Java programming language **always** uses `call by value`. That means that **the method gets a copy of all parameter values**. In particular, the method cannot modify the contents of any parameter variables passed to it.

There are, however, two kinds of method parameters:
- Primitive types (numbers, boolean values)
- Object references

You have seen that it is impossible for a method to change the value of a primitive type parameter. The situation is different for object parameters.

As you have seen, it is easily possible—and in fact very common—to implement methods that change the state of an object parameter. The reason is simple. **The method gets a copy of the object reference, and both the original and the copy refer to the same object.**

Many programming languages (in particular, C++ and Pascal) have two mechanisms for parameter passing: `call by value` and `call by reference`. Some programmers (and unfortunately even some book authors) claim that Java uses call by reference for objects. That is false. As this is such a common misunderstanding, it is worth examining a counterexample in detail.

```java
public static void swap(Employee x, Employee y) // doesn't work
{

	Employee temp = x;
	
	x = y;
	
	y = temp;

}

```

If Java used `call by reference` for objects, this method would work:

```java
var a = new Employee("Alice", . . .);

var b = new Employee("Bob", . . .);

swap(a, b);

// does a now refer to Bob, b to Alice?
```

However, the method does not actually change the object references that are stored in the variables a and b. **The x and y parameters of the swap method are initialized with copies of these references. The method then proceeds to swap these copies.**

```java
public static void swap(Employee x, Employee y) // doesn't work

{
	// x refers to Alice, y to Bob
	Employee temp = x;
	
	x = y;
	
	y = temp;
	// now x refers to Bob, y to Alice
}

```

But ultimately, this is a **wasted** effort. When the method ends, the parameter variables x and y are abandoned. The original variables a and b still refer to the same objects as they did before the method call

This demonstrates that the Java programming language **does not** use `call by reference` for objects. Instead, `object references are passed by value`.

Here is a summary of what you can and cannot do with method parameters in Java:
- A method cannot modify a parameter of a primitive type (that is, numbers or boolean values).
- A method can change the state of an object parameter.
- A method cannot make an object parameter refer to a new object.

C++ has **both** `call by value` and `call by reference`. You tag reference parameters with `&`. For example, you can easily implement methods void `tripleValue(double& x)` or `void swap(Employee& x, Employee& y)` that modify their reference parameters.

## 2.6. Object Construction

Since object construction is so important, Java offers quite a variety of mechanisms for writing constructors.

### 2.6.1. Overloading
Overloading occurs if several methods have **the same name** (in this case, the `StringBuilder` constructor method) **but different parameters**. The compiler must sort out which method to call. It picks the correct method by matching the parameter types in the headers of  the various methods with the types of the values used in the specific method call. A **compile-time error** occurs if the compiler cannot match the  parameters, either because there is no match at all or because there is not one that is better than all others. (The process of finding a match is called `overloading resolution`.)

Classes can have more than one constructor. For example, you can construct an empty `StringBuilder` object as

```java
var messages = new StringBuilder();
```

Alternatively, you can specify an initial string:

```java
var todoList = new StringBuilder("To do:\n");
```

### 2.6.2. Default Field Initialization

If you don’t set a field explicitly in a constructor, it is automatically set to a `default value`: numbers to `0`, boolean values to `false`, and object references to `null`.

---
This is an important difference between **fields** and **local variables**. You must always explicitly initialize local variables in a method. But in a class, **if you don’t initialize a field, it is automatically initialized to a default (0, false, or null)**.

---

### 2.6.3. The Constructor with No Arguments

If you write a class with no constructors whatsoever, then a `no-argument constructor` is provided for you. This constructor sets all the instance fields to their default values. So, all numeric data contained in the instance fields would be 0, all boolean values would be false, and all object variables would be null.

If a class supplies at least one constructor but does not supply a no-argument constructor, it is **illegal** to construct objects without supplying arguments.

For example, our original Employee class in Listing 4.2 provided a single constructor:

```java
public Employee(String n, double s, int year, int month, int day)
```

With that class, it was not legal to construct default employees. That is, the call

```java
e = new Employee();
```

would have been an error.

---

**CAUTION:**

Please keep in mind that **you get a free no-argument constructor only when your class has no other constructors.** If you write your class with even a **single** constructor of your own and you want the users of your class to have the ability to create an instance by a call to

```java
new ClassName();
```

then you **must** provide a no-argument constructor. Of course, if you are happy with the default values for all fields, you can simply supply

```java
public ClassName(){} // without any initializations
```


---

### 2.6.4. Explicit Field Initialization

This assignment is carried out before the constructor executes. This syntax is particularly useful if all constructors of a class need to set a particular instance field to the same value. The initialization value doesn’t have to be a constant value. It can also be done by a method call.

### 2.6.5. Shadowing Constructor Parameters

A commonly used trick relies on the fact that parameter variables shadow instance fields with the same name. For example, if you call a parameter salary, then salary refers to the parameter, not the instance field. But you can still access the instance field as this.salary. Recall that this denotes the implicit parameter—that is, the object being constructed. Here is an example:

```java
public Employee(String name, double salary){

	this.name = name;
	this.salary = salary;

}
```


### 2.6.6. Calling Another Constructor

The keyword `this` refers to the current instance of the class.  If the first statement of a constructor has the form `this(. . .)`, then the constructor calls another constructor of the same class.

### 2.6.7. Initialization Blocks

You have already seen two ways to initialize a instance field:
- By setting a value in a constructor
- By assigning a value in the declaration

There is a third mechanism in Java, called an `initialization block`. Class declarations can contain arbitrary blocks of code. **These blocks are executed whenever an object of that class is constructed.**

```java

class Employee{

	private static int nextId;
	private int id;
	private String name;
	private double salary;
	
	// object initialization block 
	{
		id = nextId;
		nextId++;
	}

	public Employee(String n, double s){
		name = n;
		salary = s;
	}

	public Employee(){
		name = "";
		salary = 0;
	}
	
	. . .
	
}

```


In this example, the id field is initialized in the object initialization block, **no matter which constructor is used to construct an object. The initialization block runs first, and then the body of the constructor is executed.**

This mechanism is never necessary and is **not common**. It is usually more straightforward to place the initialization code inside a constructor.


Here is what happens in detail when a constructor is called:
1. If the first line of the constructor calls a second constructor, then the second constructor executes with the provided arguments. 
2. Otherwise:
	- All instance fields are initialized to their default values (0, false, or null).
	- All field initializers and initialization blocks are executed, in the order in which they occur in the class declaration.
3. The body of the constructor is executed.


If the static fields of your class require complex initialization code, use a `static initialization block`. Place the code inside a block and tag it with the keyword `static`. 

```java
private static int nextId = 1;
private static Random generator = new Random();

// static initialization block
static
{
	nextId = generator.nextInt(10000);
}
```

- **NOTE:** Like `field variables`, `static varibles` are also initialized by default variables if they are not explicitly initialized.

Static initialization occurs when the class is **first loaded**. Like instance fields, static fields are `0`, `false`, or `null` unless you explicitly set them to another value. All static field initializers and static initialization blocks are executed in the order in which they occur in the class declaration.

## 2.7. Records

A `record` is a special form of a class whose state is `immutable` and `readable by the public`. Here is how you define Point as a record:

```java
record Point(double x, double y) {
}
```

The result is a class with instance fields:

```java
private final double x;
private final double y;
```

In the Java language specification, the instance fields of a record are called its `components`. The class has a constructor:

```java
Point(double x, double y)
```

and accessor methods

```java
public double x()
public double y()
```

by default. Note that the accessors are called x and y, not getX and getY. (It is legal in Java to have an instance field and a method with the same name.)

In addition to the field accessor methods, every record has three methods defined automatically: `toString`, `equals`, and `hashCode`.
- A record is **also an object**, thus it also extends **`Object`** class.

---
**CAUTION:**

You can define your own versions of the automatically provided methods, as long as they have the same parameter and return types. For example, this definition is **legal**:

```java
record Point(double x, double y) {

public double x() { return y; } // BAD

}
```

But it is surely not a good idea.

---

You can add your **own methods** to a record:

```java

record Point(double x, double y) {

	public double distanceFromOrigin() { return Math.hypot(x, y); }
	
}
```

A record, like any class, can have `static fields` and `methods`:

```java
record Point(double x, double y) {

	public static Point ORIGIN = new Point(0, 0);
	
	public static double distance(Point p, Point q) {
	
		return Math.hypot(p.x - q.x, p.y - q.y);
	
	}

	...

}
```

However, you **cannot** add instance fields to a record. (Because you declare class fields by **constructor**, and a record doesn't have a constructor in the same way as objects.)

```java

record Point(double x, double y) {

	private double r; // ERROR

}
```

---
Instance fields of a record are automatically `final`. However, they may be references to mutable objects. 
- **As always, having an object defined as `final` means that the reference of the object cannot be changed once initialized.**

---

---
**TIP:**
Use a record instead of a class for immutable data that is completely represented by a set of variables. Use a class if the data is mutable, or if the representation may evolve over time. Records are easier to read, more efficient, and safer in concurrent programs.

---

### 2.7.1. Constructors: Canonical, Custom, and Compact

The automatically defined constructor that sets all instance fields is called the `canonical constructor`. You can define additional `custom constructors`. The first statement of **such a constructor must call another constructor**, so that **ultimately the canonical constructor is invoked**.

```java
record Point(double x, double y) {
	
	public Point() { this(0, 0); }

}
```

This record has two constructors: **the canonical constructor** and a **no-argument custom constructor** yielding the origin. If the canonical constructor needs to do additional work, you can provide your own implementation:

```java
record Range(int from, int to) {

	public Range(int from, int to) {
		if (from <= to){
			this.from = from;
			this.to = to;		
		}
		else{
			this.from = to;
			this.to = from;
		}
	}

}
```


However, you are encouraged to use a `compact form` when implementing the canonical constructor. **You don’t specify the parameter list**:

```java
record Range(int from, int to) {

	public Range{ // Compact form 
		if (from > to){ // Swap the bounds
			int temp = from;
			from = to;
			to = temp;
		}
	}
	
}
```

The body of the compact form is the **“prelude”** to the canonical constructor. It merely modifies the parameter variables from and to before they are assigned to the instance fields `this.from` and `this.to`. You cannot read or modify the instance fields in the body of the compact constructor.