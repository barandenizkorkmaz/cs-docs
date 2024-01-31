# 3. ArrayList

### Overview

- Java uses `array` internally to contain the data of a `ArrayList`.
- The physical size of the array is indicated by the data field `capacity`.
- The number of data items is indicated by the data field `size`.
- The data type of the references stored in the underlying array theData (type E[]) is also determined when the `MyArrayList` object is declared. If no parameter type is specified, the implicit parameter type is Object, and the underlying data array is type Object[].

### Class `java.util.ArrayList<E>`

| **Modifier and Type** | **Method and Description**                                   |
| --------------------- | ------------------------------------------------------------ |
| boolean               | `add(E e)`<br />Appends the specified element to the end of this list. |
| void                  | `add(int index, E element)`<br />Inserts the specified element at the specified position in this list. |
| E                     | get(int index)<br/>Returns the element at the specified position in this list. |
| Iterator\<E>          | iterator()<br/>Returns an iterator over the elements in this list in proper sequence. |
| E                     | remove(int index)<br/>Removes the element at the specified position in this list. |
| boolean               | remove(Object o)<br/>Removes the first occurrence of the specified element from this list, if it is present. |
| E                     | set(int index, E element)<br/>Replaces the element at the specified position in this list with the specified element. |
| int                   | size()<br/>Returns the number of elements in this list.      |
| Object[]              | toArray()<br/>Returns an array containing all of the elements in this list in proper sequence (from first to last element). |



### Implementation

```java
import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;

public class MyArrayList<E> extends AbstractList<E> implements List<E> {

    // Data Fields
    /** The default initial capacity */
    private static final int INITIAL_CAPACITY = 10;
    /** The underlying data array */
    private E[] data;
    /** The current size */
    private int size = 0;
    /** The current capacity */
    private int capacity = 0;

    @SuppressWarnings("unchecked")
    public MyArrayList(){
        this.capacity = INITIAL_CAPACITY;
        data = (E[]) new Object[capacity];
    }

    public boolean add(E anEntry) {
        if (size == capacity) {
            reallocate();
        }
        data[size] = anEntry;
        size++;
        return true;
    }

    public void add(int index, E anEntry) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        if (size == capacity) {
            reallocate();
        }
        // Shift data in elements from index to size ‐ 1
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        // Insert the new item.
        data[index] = anEntry;
        size++;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return data[index];
    }
    public E set(int index, E newValue) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        E oldValue = data[index];
        data[index] = newValue;
        return oldValue;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        E returnValue = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        return returnValue;
    }

    private void reallocate() {
        capacity = 2 * capacity;
        data = Arrays.copyOf(data, capacity);
    }

    public int size() {
        return this.size;
    }
}
```



### Performance

| Operations/Complexity     | Big O (Worst-Case Complexity) |
| ------------------------- | ----------------------------- |
| add(E e)                  | O(1)                          |
| add(int index, E element) | O(N)                          |
| get(int index)            | O(1)                          |
| set(int index, E element) | O(1)                          |
| remove(int index)         | O(N)                          |
| remove(Object o)          | O(N)                          |
| size()                    | O(1)                          |

- Recall that when we reallocate the array, we double its size. Doubling an array of size n allows us to add n more items before we need to do another array copy. Therefore, we can add n new items after we have copied over n existing items. This averages out to 1 copy per add. Therefore, reallocation is effectively an O(1) operation, so the insertion (to any index) is still O(n).



### Extra

> #### Syntax: Creating a Generic Collection
>
> **FORM:**
> 	CollectionClassName\<E> variable = new CollectionClassName<>();
> 	CollectionClassName\<E> variable = new CollectionClassName\<E>();
>
> **EXAMPLE:**
> 	List\<Person> people = new ArrayList<>();
> 	List\<String> myList = new ArrayList\<String>();
> 	ArrayList\<Integer> numList = new ArrayList<>();
>
> **MEANING:**
> An initially empty CollectionClassName\<E> object is created that can be used to store references to objects of type E (the type parameter). The  actual object type stored in an object of type CollectionClassName\<E> is specified when the object is created. If the CollectionClassName on the left is an interface, the CollectionClassName on the right must be a class that implements it. Otherwise, it must be the same class or a subclass of the one on the left.
> The examples above show different ways to create an ArrayList. In this text, we normally specify the interface name on the left of the = operator and the implementing class name on the right as shown in the first two examples. Since the type parameter E must be the same on both sides of the assignment operator, Java 7 introduced the diamond operator <> which eliminates the need to specify the type parameter twice. We will follow this convention. In some cases, we will declare the variable type in one statement and create it in a later statement. In earlier versions of Java, generic collections were not supported. In these versions, you use the statement
> 	List yourList = new ArrayList();
> to create an initially empty ArrayList. Each element of yourList is a type Object reference. The data types of the actual objects referenced by elements of yourList are not specified, and in fact, different elements can reference objects of different types. Use of the adjective “generic” is a bit confusing. A nongeneric collection in Java is very general in that it can store objects of different data types. A generic collection, however, can store objects of one specified data type only. Therefore, generics enable the compiler to do more strict type checking to detect errors at compile time instead of at run time. They also eliminate the need to downcast from type Object to a specific type. For these reasons, we will always use generic collections.

> #### Constructor Declaration for Generic Classes
>
> The constructor declaration follows. Because the constructor is for a generic class, the type parameter \<E> is implied but it must not appear in the constructor heading.
>
> ​	public MyArrayList() {
> ​		capacity = INITIAL_CAPACITY;
> ​		data = (E[]) new Object[capacity];
> ​	}
>
> The statement
> 	data = (E[]) new Object[capacity];
> allocates storage for an array with type Object references and then casts this array object to type E[] so that it is type compatible with variable theData. Because the actual type corresponding to E is not known, the compiler issues the warning message: MyArrayList.java uses unchecked or unsafe operations. Don’t be concerned about this warning—everything is fine.

> #### PITFALL
>
> #### **Declaring a Generic Array**
>
> Rather than use the approach shown in the above constructor, you might try to create a
> generic array directly using the statement
> 	theData = new E[capacity]; // Invalid generic array type.
> However, this statement will not compile because Java does not allow you to create an array with an unspecified type. Remember, E is a type parameter that is not specified until a generic ArrayList object is created. Therefore, the constructor must create an array of type Object[] since Object is the superclass of all types and then downcast this array object to type E[].
