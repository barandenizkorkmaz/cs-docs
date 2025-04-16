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

## 4.2. Lambda Expressions

A lambda expression is a block of code that you can pass around so it can be executed later, once or multiple times.

### 4.2.1. The Syntax of Lambda Expressions

A simple form of lambda expressions in Java:

parameters, the -> arrow, and an expression. If the code carries out a

computation that doesn’t fit in a single expression, write it exactly like you

would have written a method: enclosed in {} and with explicit return

statements.

```java
// FIRST

(String first, String second) -> first.length() - second.length();

// SECOND

(String first, String second) ->
{
	if (first.length() < second.length()) return -1;
	else if (first.length() > second.length()) return 1;
	else return 0;
}
```

- If a lambda expression has no parameters, you still supply empty parentheses

```java
() -> { for (int i = 100; i >= 0; i--) System.out.println(i); }
```

- If the parameter types of a lambda expression can be inferred, you can omit

them.

```java
Comparator<String> comp = (first, second) // same as (String first, String second)
-> first.length() - second.length();
```

- If a method has a single parameter with inferred type, you can even omit the

parentheses

```java
ActionListener listener = event -> System.out.println("The time is "

+ Instant.ofEpochMilli(event.getWhen()));

// instead of (event) -> . . . or (ActionEvent event) -> .

. .
```

- You never specify the result type of a lambda expression. It is always inferred from context. The following can be used in a context where a result of type `int` is expected.

```java
(String first, String second) -> first.length() -

second.length()
```

### 4.2.2. Functional Interfaces

- As we discussed, there are many existing interfaces in Java that encapsulate blocks of code, such as `ActionListener` or `Comparator`. 
- Lambdas are compatible with these interfaces. 
- You can supply a lambda expression whenever an object of an interface with a single abstract method is expected. Such an interface is called a functional interface.
- To demonstrate the conversion to a functional interface, consider the Arrays.sort method. Its second parameter requires an instance of `Comparator`, an interface with a single method. Simply supply a lambda. Behind the scenes, the `Arrays.sort` method receives an object of some class that implements `Comparator<String>`. Invoking the compare method on that object executes the body of the lambda expression. **In fact, conversion to a functional interface is the only thing that you can do with a lambda expression in Java.**
```java
Arrays.sort(words, (first, second) -> first.length() - second.length());
```

---

A particularly useful interface in the java.util.function package is `Predicate`:

```java
public interface Predicate<T>
{
	
	boolean test(T t);
	
	// additional default and static methods
}
```

- The ArrayList class has a `removeIf` method whose parameter is a `Predicate`.

```java
list.removeIf(e -> e == null);
```

---

---
Another useful functional interface is `Supplier<T>`:

```java
public interface Supplier<T>
{
	T get();
}
```

A supplier has no arguments and yields a value of type T when it is called. Suppliers are used for `lazy evaluation`. For example, consider the call:

```java
LocalDate hireDay = Objects.requireNonNullElseGet(day, () -> new LocalDate.of(1970, 1, 1));
```

---

### 4.2.3. Method References

- Sometimes, a lambda expression involves a single method. For example, suppose you simply want to print the event object whenever a timer event occurs.

```java
var timer = new Timer(1000, System.out::println);
```

- The expression `System.out::println` is a method reference. It directs the compiler to produce an instance of a functional interface, overriding the single abstract method of the interface to call the given method. In this example, an `ActionListener` is produced whose `actionPerformed(ActionEvent e)` method calls `System.out.println(e)`.
- **Like a lambda expression, a method reference is not an object. It gives rise to an object when assigned to a variable whose type is a functional interface.**
- The `::` operator separates the method name from the name of an object or class. There are three variants:
	1. `object::instanceMethod`
	2. `Class:instanceMethod`
	3. `Class:staticMethod`

| Method Reference    | Equivalent Lambda Expression |
| ------------------- | ---------------------------- |
| `separator::equals` | `x -> separator.equals(x)`   |
| `String::trim`      | `x -> x.strip()`             |
| `String::concat`    | `(x,y) -> x.concat(y)`       |
| `Integer::valueOf`  | `x -> Integer.valueOf(x)`    |
| `Integer::sum`      | `(x,y) -> Integer.sum(x,y)`  |
| `String::new`       | `x -> new String(x)`         |
| `String[]::new`     | `n -> new String[n]`         |
- **NOTE:** You can capture the `this` parameter in a method reference. For example, `this::equals` is the same as `x -> this.equals(x)`. It is also valid to use `super`. The method expression `super::instanceMethod` uses this as the target and invokes the superclass version of the given method.

### 4.2.4. Constructor References

- Constructor references are just like method references, except that the name of the method is new.
- For example, `Person::new` is a reference to a Person constructor. Which constructor? It depends on the context.

```java
ArrayList<String> names = . . .;
Stream<Person> stream = names.stream().map(Person::new);
List<Person> people = stream.toList();
```

### 4.2.5. Variable Scope

- To understand what is happening, we need to refine our understanding of a lambda expression. A lambda expression has three ingredients: 
	1. A block of code
	2. Parameters
	3. Values for the free variables—that is, the variables that are not parameters and not defined inside the code
- The technical term for a block of code together with the values of the free variables is a `closure`. If someone gloats that their language has closures, rest assured that Java has them as well. In Java, lambda expressions are closures.
- As you have seen, a lambda expression can capture the value of a variable in the enclosing scope.
- Mutating variables in a lambda expression is not safe when multiple actions are executed concurrently. The rule is that any captured variable in a lambda expression must be effectively `final`. 
- The body of a lambda expression has the same scope as a nested block. The same rules for name conflicts and shadowing apply. It is illegal to declare a parameter or a local variable in the lambda that has the same name as a local variable.

### 4.2.6. Processing Lambda Expressions

Up to now, you have seen how to produce lambda expressions and pass them to a method that expects a functional interface. Now let us see how to write methods that can consume lambda expressions.

The point of using lambdas is `deferred execution`. To accept the lambda, we need to pick (or, in rare cases, provide) a functional interface. Table below the most important functional interfaces that are provided in the Java API.

**NOTE:** If you design your own interface with a single abstract method, you can tag it with the `@FunctionalInterface` annotation. This has two advantages. The compiler gives an error message if you accidentally add another abstract method. And the javadoc page includes a statement that your interface is a functional interface. It is not required to use the annotation. Any interface with a single abstract method is, by definition, a functional interface. But using the `@FunctionalInterface` annotation is a good idea.

| Functional Interface  | Parameter Types | Return Type | Abstract Method Name | Description | Other Methods |
|----------------------|----------------|-------------|----------------------|-------------|--------------|
| `Runnable`         | none           | `void`      | `run`                | Runs an action without arguments or return value | |
| `Supplier<T>`      | none           | `T`         | `get`                | Supplies a value of type `T` | |
| `Consumer<T>`      | `T`            | `void`      | `accept`             | Consumes a value of type `T` | `andThen` |
| `BiConsumer<T, U>` | `T, U`         | `void`      | `accept`             | Consumes values of types `T` and `U` | `andThen` |
| `Function<T, R>`   | `T`            | `R`         | `apply`              | A function with argument of type `T` | `compose`, `andThen`, `identity` |
| `BiFunction<T, U, R>` | `T, U`      | `R`         | `apply`              | A function with arguments of types `T` and `U` | `andThen` |
| `UnaryOperator<T>` | `T`            | `T`         | `apply`              | A unary operator on the type `T` | `compose`, `andThen`, `identity` |
| `BinaryOperator<T>`| `T, T`         | `T`         | `apply`              | A binary operator on the type `T` | `andThen`, `maxBy`, `minBy` |
| `Predicate<T>`     | `T`            | `boolean`   | `test`               | A boolean-valued function | `and`, `or`, `negate`, `isEqual`, `not` |
| `BiPredicate<T, U>`| `T, U`         | `boolean`   | `test`               | A boolean-valued function with two arguments | `and`, `or`, `negate` |

## 4.3. Inner Classes

An inner class is a class that is defined inside another class. Why would you want to do that? There are two reasons: 
- Inner classes can be hidden from other classes in the same package.
- Inner class methods can access the data from the scope in which they are defined—including the data that would otherwise be private.
An object that comes from an inner class has an implicit reference to the outer class object that instantiated it. Through this pointer, it gains access to the total state of the outer object.
- For example, in Java, the `Iterator` class would not need an explicit pointer to the `LinkedList` into which it points.
- An inner class object has a reference to an outer class object.
	- The syntax for the outer reference is the following
`OuterClass.this`.
- We could declare an inner class as `private`. Then only the outer class would be able to construct inner class objects. Only inner classes can be private. Regular classes always have either package or public access.
- An inner class **cannot** have `static` methods.
- Any static-fields declared in an inner class must be `final` and initialized with a compile-time constant.
- The inner class can access the private data of the outer class, but the outer class cannot. Thus, inner classes are genuinely more powerful than regular classes because they have more access privileges.
### 4.3.1. Local Inner Classes

- You can define the class locally in a single method.

```java
public void start()

{

	class TimePrinter implements ActionListener
	
	{
	
		public void actionPerformed(ActionEvent event)
		
		{
		
		System.out.println("At the tone, the time is "
		
		+ Instant.ofEpochMilli(event.getWhen()));
		
		if (beep) Toolkit.getDefaultToolkit().beep();
		
		}
	
	}
	
	var listener = new TimePrinter();
	var timer = new Timer(interval, listener);
	timer.start();

}
```

- Local classes are never declared with an access specifier (that is, public or private). Their scope is always restricted to the block in which they are declared.
- **Local classes have one great advantage: They are completely hidden from the outside world.**
- Local classes have another advantage over other inner classes.
	- Not only can they access the fields of their outer classes; they can even access `local variables`! However, those local variables must be effectively final. That means, they may never change once they have been assigned.

#### 4.3.1.1. Anonymous Inner Classes

If you want to make only a single object of this class, you don’t even need to give the class a name. Such a class is called an `anonymous inner class`.

```java
public void start(int interval, boolean beep)

{

var listener = new ActionListener() {

		public void actionPerformed(ActionEvent event)
		
		{
			
			System.out.println("At the tone, the time is "
			
			+ Instant.ofEpochMilli(event.getWhen()));
			
			if (beep) Toolkit.getDefaultToolkit().beep();
		
		}

	};

var timer = new Timer(interval, listener);

timer.start();
}
```

- In general, the syntax is
```
new SuperType(construction parameters) {

	inner class methods and data
}

```

- Here, ``SuperType`` can be an **interface**, such as ActionListener; then, **the inner class implements that interface**. `SuperType` can also be a **class**; then, **the inner class extends that class.**
- An anonymous inner class **cannot** have constructors because the name of a constructor must be the same as the name of a class, and the class has no name. Instead, the construction parameters are given to the superclass constructor.
- You have to look carefully to see the difference between the construction of a new object of a class and the construction of an object of an anonymous inner class extending that class.

```java
var queen = new Person("Mary");

// a Person object

var count = new Person("Dracula") { . . . };

// an object of an inner class extending Person
```

- For many years, Java programmers routinely used anonymous inner classes for event listeners and other callbacks. Nowadays, you are better off using a lambda expression.
```java
public void start(int interval, boolean beep)

{

// Simply by using lambda

	var timer = new Timer(interval, event -> {
	
	System.out.println(
	
	"At the tone, the time is " +
	
	Instant.ofEpochMilli(event.getWhen()));
	
	if (beep) Toolkit.getDefaultToolkit().beep(); });
	
	timer.start();

}
```

---
**NOTE:** Double Brace Initialization

The following trick, called double brace initialization, takes advantage of the inner class syntax. Suppose you want to construct an array list and pass it to a method:

```java
var friends = new ArrayList<String>();
friends.add("Harry");
friends.add("Tony");
invite(friends);
```

If you don’t need the array list again, it would be nice to make it anonymous. But then how can you add the elements?

```java
invite(new ArrayList<String>() {{ add("Harry"); add("Ron");}});

// double brace initialization
var friends = new ArrayList<String>() {
	{
		add("Harry");
		add("Ron");
	}
}
```

Note the double braces. The outer braces make an anonymous subclass of ArrayList. **The inner braces are an object initialization block** (see previous chapter).

---

### 4.3.2. Static Inner Class

Occasionally, you may want to use an inner class simply to hide one class inside another—**but you don’t need the inner class to have a reference to the outer class object.** You can suppress the generation of that reference by declaring the inner class `static`.
- Use a static inner class whenever the inner class does not need to access an outer class object.
- **Unlike regular inner classes, static inner classes can have static fields and methods.**
- **Classes that are declared inside an interface are automatically static and public.**
- **Interfaces, records, and enumerations that are declared inside a class are automatically static.**