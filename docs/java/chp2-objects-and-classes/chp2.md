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

