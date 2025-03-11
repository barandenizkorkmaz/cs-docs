# 1.1. Data Types

- There are eight primitive types in Java. 
  - Four of them are integer types
  - Two are ﬂoating-point number types
  - One is the character type `char`, used for code units in the Unicode encoding scheme
  - One is a `boolean` type for truth values.
- Since Java programs must run with the same results on all machines, the ranges for the various types are ﬁxed.



## 1.1.1. Integer Types

| **Type** | **Storage Requirement** | **Range (Inclusive)**                                   |
| -------- | ----------------------- | ------------------------------------------------------- |
| int      | 4 bytes                 | -2,147,483,648 to 2,147,483,647 (just over 2 billion)   |
| short    | 2 bytes                 | -32,768 to 32,767                                       |
| long     | 8 bytes                 | -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807 |
| byte     | 1 byte                  | -128 to 127                                             |



## 1.1.2. Floating-Point Types

| **Type** | **Storage Requirement** | **Range**                                                    |
| -------- | ----------------------- | ------------------------------------------------------------ |
| float    | 4 bytes                 | Approximately ±3.40282347×10³⁸ (6–7 significant decimal digits) |
| double   | 8 bytes                 | Approximately ±1.79769313486231570×10³⁰⁸ (15 significant decimal digits) |

- **NOTE:** If you need precise numerical computations without roundoff errors, use the `BigDecimal` class



## 1.1.3. The `char` Type

- In Java, the char type describes a code unit in the UTF-16 encoding **(2 bytes)**.
- It is strongly recommended against using the char type in your programs unless you are actually manipulating UTF-16 code units. You are almost always better off treating `strings` as abstract data types.



## 1.1.4. The `boolean` Type

- The boolean type has two values, `false` and `true`.



# 1.2. Variables and Constants

- It is probably more common in Java to create a **constant** so it’s available to multiple methods inside a single class. 
- These are usually called class constants.
- Set up a class constant with the keywords `static final`.
- Note that the deﬁnition of the class constant appears outside the main method. Thus, the constant can also be used in other methods of the same class. 
- Furthermore, if the constant is declared, as in this example, `public`, methods of other classes can also use it.



# 1.3. Operators



## 1.3.1. Mathematical Functions

- Useful mathematical functions:

  - `Math.floorMod`
  - `Math.pow`

- **Static Imports** You can avoid the Math preﬁx for the mathematical methods and constants by adding the following line to the top of your source ﬁle:

  ```java 
  import static java.lang.Math.*;
  ```

- The Math class provides several methods to make integer arithmetic **safer**. The mathematical operators quietly return wrong results when a
  computation overﬂows. If you call `Math.multiplyExact(1000000000, 3)` instead, an exception is generated. You can catch that exception or let the program terminate rather than quietly continue with a wrong result. There are also methods `addExact`, `subtractExact`, `incrementExact`, `decrementExact`, `negateExact`, and `absExact`, all with `int` and `long` parameters.



## 1.3.2 Conversion between Numeric Types

![image-20241010210324418](/home/denizkorkmaz/Git/BDK-Repositories/Personal/cs-docs/docs/assets/images/java/chp1/conversion-between-types.png)

- The three dotted arrows denote conversions that may lose precision.



## 1.3.3. Increment and Decrement Operators

There is also a preﬁx form, `++n`. Both change the value of the variable by 1. The difference between the two appears only when they are used inside expressions. The preﬁx form does the addition ﬁrst; the postﬁx form evaluates to the old value of the variable.



## 1.3.4. Relational and `boolean` Operators

- The `&&` and `||` operators are evaluated in **short circuit** fashion: The second argument is not evaluated if the ﬁrst argument already determines the value.
- This behavior can be exploited to avoid errors.



## 1.3.5. The Conditional Operator

```java
condition ? expression1 : expression2
```



## 1.3.6. Switch Expressions

- If you need to choose among more than two values, then you can use a `switch` expression (as of `Java 14`).
- The case labels can also be strings or constants of an enumerated type.

```java
String seasonName = switch (seasonCode)
{
    case 0 -> "Spring";
    case 1 -> "Summer";
    case 2 -> "Fall";
    case 3 -> "Winter";
    default -> "???";
};
```

- As of Java 14, there are **four** (!) forms of `switch`.



## 1.3.7. Bitwise Operators

```java
& (“and”)
| (“or”)
^ (“xor”)
~ (“not”)
```

- There are also `>>` and `<<` operators which shift a bit pattern right or left.
  - The right-hand argument of the shift operators is reduced modulo 32 (unless the left-hand argument is a long, in which case the right-hand argument is reduced modulo 64).

- Representing a binary number is as follows: `0b1000` or `0B100`



# 1.4. Strings

--- 
NOTE: We should include `Integer`, `Double`, and `String` classes. I had some notes for them at some interview prep place (maybe for AWS?).



