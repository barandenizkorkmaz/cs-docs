# 1. Arrays

## Overview

- Fixed length once initialized.
- Add or remove element at a specified position without shifting the other elements to make room/fill in the resulting gap.
- One feature that the array data structure provides that these classes (List classes) don’t is the ability to store primitive‐type values. The List classes all store references to Objects, so all primitive‐type values must be wrapped in objects.
  - Note that all generic classes in Java extends `Object` class.



**NOTE:** **Circular array** implementation will be shown in `Queue` chapter.




## Performance

| Operations/Complexity | Best-Case                  | Average-Case | Worst-Case |
| --------------------- | -------------------------- | ------------ | ---------- |
| Add                   | O(1) (add to the end)      | O(N)         | O(N)       |
| Delete                | O(1) (delete from the end) | O(N)         | O(N)       |
| Search                | O(1)                       | O(N)         | O(N)       |
| Get                   | O(1)                       | O(1)         | O(1)       |



## Class `java.util.Arrays`

### Method Summary

| **Modifier and Type** | **Method**                                  | **Description**                                              |
|----------------------| ------------------------------------------- | ------------------------------------------------------------ |
| static boolean       | equals(Object[] a, Object[] a2)             | Returns true if the two specified arrays of Objects are equal to one another. |
| static void          | fill(Object[] a, Object val)                | Assigns the specified Object reference to each element of the specified array of Objects. |
| static \<T> T[]      | copyOf(T[] original, int newLength)         | Copies the specified array, truncating or padding with nulls (if necessary) so the copy has the specified length. |
| static \<T> T[]      | copyOfRange(T[] original, int from, int to) | Copies the specified range of the specified array into a new array. |
| static String        | toString(Object[] a)                        | Returns a string representation of the contents of the specified array. |
| static void          | sort(Object[] a)                            | Sorts the specified array of objects into ascending order, according to the natural ordering of its elements. |
| static \<T> void     | sort(T[] a, Comparator<? super T> c)        | Sorts the specified array of objects according to the order induced by the specified comparator. |
| int                  | size()                                      | Returns the number of elements in this list.                 |
| static int           | binarySearch(Object[] a, Object key)        | Searches the specified array for the specified object using the binary search algorithm. |

Reference: https://docs.oracle.com/javase/8/docs/api/java/util/Arrays.html



```java
// A more general method of copying an array
System.arraycopy(source, sourcePos, destination, destPos, numElements);
```



# 2. List Interface

| **Modifier and Type** | **Method**                | **Description**                                              |
| --------------------- | ------------------------- | ------------------------------------------------------------ |
| boolean               | add(E e)                  | Appends the specified element to the end of this list (optional operation). |
| void                  | add(int index, E element) | Inserts the specified element at the specified position in this list (optional operation). |
| E                     | get(int index)            | Returns the element at the specified position in this list.  |
| int                   | indexOf(Object o)         | Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element. |
| Iterator\<E>          | iterator()                | Returns an iterator over the elements in this list in proper sequence. |
| E                     | remove(int index)         | Removes the element at the specified position in this list (optional operation). |
| boolean               | remove(Object o)          | Removes the first occurrence of the specified element from this list, if it is present (optional operation). |
| E                     | set(int index, E element) | Replaces the element at the specified position in this list with the specified element (optional operation). |
| int                   | size()                    | Returns the number of elements in this list.                 |
| Object[]              | toArray()                 | Returns an array containing all of the elements in this list in proper sequence (from first to last element). |

![image-20240103215047752](/home/denizkorkmaz/.config/Typora/typora-user-images/image-20240103215047752.png)



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



# 4. Single-Linked Lists

### Overview

- Java does not have a class that implements single-linked lists. Instead, it has a more general double-linked list class.



## Class `LinkedList<E>`

| **Modifier and Type** | **Method**                | **Description**                                              |
| --------------------- | ------------------------- | ------------------------------------------------------------ |
| boolean               | add(E e)                  | Appends the specified element to the end of this list.       |
| void                  | add(int index, E element) | Inserts the specified element at the specified position in this list. |
| E                     | get(int index)            | Returns the element at the specified position in this list.  |
| int                   | indexOf(Object o)         | Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element. |
| Iterator\<E>          | iterator()                | Returns an iterator over the elements in this list in proper sequence. |
| E                     | remove(int index)         | Removes the element at the specified position in this list.  |
| boolean               | remove(Object o)          | Removes the first occurrence of the specified element from this list, if it is present. |
| E                     | set(int index, E element) | Replaces the element at the specified position in this list with the specified element. |
| int                   | size()                    | Returns the number of elements in this list.                 |
| Object[]              | toArray()                 | Returns an array containing all of the elements in this list in proper sequence (from first to last element). |

- Note that the `LinkedList` class, part of Java API package `java.util`, is a double-linked list. However, the API description above is still valid.



### Implementation

```java
import java.util.AbstractSequentialList;
import java.util.List;
import java.util.ListIterator;

/** Class to represent a linked list with a link from each node to the next
 node. SingleLinkedList does not implement the List interface.
 */

public class SingleLinkedList<E> extends AbstractSequentialList<E> implements List<E> {

    /** A Node is the building block for a single‐linked list. */
    private static class Node<E> {
        // Data Fields
        /** The reference to the data. */
        private E data;
        /** The reference to the next node. */
        private Node<E> next;

        // Constructors
        /** Creates a new node with a null next field.
         @param dataItem The data stored
         */
        private Node(E dataItem) {
            data = dataItem;
            next = null;
        }

        /** Creates a new node that references another node.
         @param dataItem The data stored
         @param nodeRef The node referenced by new node
         */
        private Node(E dataItem, Node<E> nodeRef) {
            data = dataItem;
            next = nodeRef;
        }
    }

    /**
     * Reference to list head.
     */
    private Node<E> head = null;
    /**
     * The number of items in the list
     */
    private int size = 0;

    /** Insert the specified item at index
     @param index The position where item is to be inserted
     @param item The item to be inserted
     @throws IndexOutOfBoundsException if index is out of range
     */
    public void add(int index, E item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        if (index == 0) {
            addFirst(item);
        } else {
            Node<E> node = getNode(index-1);
            addAfter(node, item);
        }
    }

    /** Append item to the end of the list
     @param item The item to be appended
     @return true (as specified by the Collection interface)
     */
    public boolean add(E item) {
        add(size, item);
        return true;
    }

    /** Add an item to the front of the list.
     @param item The item to be added
     */
    public void addFirst(E item) {
        head = new Node<>(item, head);
        size++;
    }

    /** Add a node after a given node
     @param node The node preceding the new item
     @param item The item to insert
     */
    private void addAfter(Node<E> node, E item) {
        node.next = new Node<>(item, node.next);
        size++;
    }

    /**
     * Returns the list iterator object.
     * @param i the index of the iterator.
     * @return the list iterator object.
     */
    @Override
    public ListIterator<E> listIterator(int i) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the element at the specified position in this list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }

        E removedData;
        if (index == 0) {
            removedData = removeFirst();
        } else {
            Node<E> prevNode = getNode(index - 1);
            removedData = removeAfter(prevNode);
        }
        return removedData;
    }

    /** Remove the node after a given node
     @param node The node before the one to be removed
     @return The data from the removed node, or null
     if there is no node to remove
     */
    private E removeAfter(Node<E> node) {
        Node<E> temp = node.next;
        if (temp != null) {
            node.next = temp.next;
            size--;
            return temp.data;
        } else {
            return null;
        }
    }

    /** Remove the first node from the list
     @return The removed node's data or null if the list is empty
     */
    private E removeFirst() {
        Node<E> temp = head;
        if (head != null) {
            head = head.next;
        }
        // Return data at old head or null if list is empty
        if (temp != null) {
            size--;
            return temp.data;
        } else {
            return null;
        }
    }

    /** Get the data at index
     @param index The position of the data to return
     @return The data at index
     @throws IndexOutOfBoundsException if index is out of range
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        Node<E> node = getNode(index);
        return node.data;
    }

    /** Find the node at a specified position
     @param index The position of the node sought
     @return The node at index or null if it does not exist
     */
    private Node<E> getNode(int index) {
        Node<E> node = head;
        for (int i = 0; i < index && node != null; i++) {
            node = node.next;
        }
        return node;
    }

    /** Store a reference to anEntry in the element at position index.
     @param index The position of the item to change
     @param newValue The new data
     @return The data previously at index
     @throws IndexOutOfBoundsException if index is out of range
     */
    public E set(int index, E newValue) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        Node<E> node = getNode(index);
        E result = node.data;
        node.data = newValue;
        return result;
    }

    /**
     * Return the current number of elements in the list.
     * @return The size of the list
     */
    public int size(){
        return size;
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element. This method allows searching for
     * an object of type Object.
     *
     * @param target the object to search for in the list
     * @return the index of the first occurrence of the object in the list, or -1 if not found
     */
    public int indexOf(Object target) {
        Node<E> current = head;
        int index = 0;
        while (current != null) {
            if (current.data.equals(target)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1; // Return -1 if the element is not found
    }

}
```



### Performance

| Operations/Complexity     | Big O (Worst-Case Complexity)                                |
| ------------------------- | ------------------------------------------------------------ |
| add(E e)                  | O(N)<br />- Insertion at the beginning: O(1)<br />- Insertion in the end: O(N)<br />- Insertion into the middle: O(N) |
| add(int index, E element) | O(N)                                                         |
| get(int index)            | O(N)                                                         |
| set(int index, E element) | O(N)                                                         |
| remove(int index)         | O(N)                                                         |
| remove(Object o)          | O(N)                                                         |
| size()                    | O(1)                                                         |



### Extra

> The keyword static in the class header indicates that the Node<E> class will not reference its outer class. (It can’t because it has no methods other than constructors.) In the Java API documentation, static inner classes are also called nested classes.
> Generally, we want to keep the details of the Node class private. Thus, the qualifier private is applied to the class as well as to the data fields and the constructor. However, the data fields and methods of an inner class are visible anywhere within the enclosing class (also called the parent class).



# 5. The `LinkedList` Class

###  Overview

- The `LinkedList` class, part of the Java API package `java.util`, is a double‐linked list that implements the `List` interface.



## Class `java.util.LinkedList<E>`

| **Modifier and Type** | **Method**                | **Description**                                              |
| --------------------- | ------------------------- | ------------------------------------------------------------ |
| boolean               | add(E e)                  | Appends the specified element to the end of this list.       |
| void                  | add(int index, E element) | Inserts the specified element at the specified position in this list. |
| void                  | addFirst(E e)             | Inserts the specified element at the beginning of this list. |
| void                  | addLast(E e)              | Appends the specified element to the end of this list.       |
| E                     | get(int index)            | Returns the element at the specified position in this list.  |
| E                     | getFirst()                | Returns the first element in this list.                      |
| E                     | getLast()                 | Returns the last element in this list.                       |
| int                   | indexOf(Object o)         | Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element. |
| Iterator\<E>          | iterator()                | Returns an iterator over the elements in this list in proper sequence. |
| E                     | remove(int index)         | Removes the element at the specified position in this list.  |
| boolean               | remove(Object o)          | Removes the first occurrence of the specified element from this list, if it is present. |
| E                     | remove()                  | Retrieves and removes the head (first element) of this list. |
| E                     | removeFirst()             | Removes and returns the first element from this list.        |
| E                     | removeLast()              | Removes and returns the last element from this list.         |
| E                     | set(int index, E element) | Replaces the element at the specified position in this list with the specified element. |
| int                   | size()                    | Returns the number of elements in this list.                 |
| Object[]              | toArray()                 | Returns an array containing all of the elements in this list in proper sequence (from first to last element). |

- Reference: https://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html



### Performance

| Operations/Complexity     | Big O (Worst-Case Complexity)                                |
| ------------------------- | ------------------------------------------------------------ |
| add(E e)                  | - General case: O(N)<br />- Insertion at the beginning: O(1)<br />- Insertion in the end: O(1)<br />- Insertion into the middle: O(N) |
| add(int index, E element) | O(N)                                                         |
| addFirst(E e)             | O(1)                                                         |
| addLast(E e)              | O(1)                                                         |
| get(int index)            | O(N)                                                         |
| getFirst()                | O(1)                                                         |
| getLast()                 | O(1)                                                         |
| set(int index, E element) | O(N)                                                         |
| remove(int index)         | O(N)                                                         |
| remove(Object o)          | O(N)                                                         |
| remove()                  | O(1)                                                         |
| removeFirst()             | O(1)                                                         |
| removeLast()              | O(1)                                                         |
| size()                    | O(1)                                                         |



# 6. The `Iterator`, `ListIterator`, and `Iterable` Interfaces

## The `Iterator` Interface

### Overview

- The List interface declares the method iterator, which returns an Iterator object that will iterate over the elements of that list. 
- The requirement for the iterator method is actually in the Collection interface, which is the superinterface for the List interface. The `Collection` interface extends the `Iterable` interface, so all classes that implement the `List` interface (a subinterface of `Collection`) must provide an `Iterator` method.
- An Iterator does not refer to or point to a particular object at any given time. Rather, you should think of an Iterator as pointing between objects within a list.
- Think of an iterator as a moving place marker that keeps track of the current position in a particular linked list. The Iterator object for a list starts at the first element in the list. The programmer can use the Iterator object’s `next` method to retrieve the next element. Each time it does a retrieval, the Iterator object advances to the next list element, where it waits until it is needed again. We can also ask the Iterator object to determine whether the list has more elements left to process (method `hasNext`). Iterator objects throw a `NoSuchElementException` if they are asked to retrieve the next element after all elements have been processed.
- You can use the Iterator remove method to remove elements from a list as you access them. You can remove only the element that was most recently accessed by next. Each call to remove must be preceded by a call to next to retrieve the next element.



### Interface `java.util.Iterator<E>`

| Modifier and Type | Method    | Description                                                  |
| ----------------- | --------- | ------------------------------------------------------------ |
| boolean           | hasNext() | Returns true if the iteration has more elements.             |
| E                 | next()    | Returns the next element in the iteration.                   |
| default void      | remove()  | Removes from the underlying collection the last element returned by this iterator (optional operation). |

### Extra

> #### Efficient Access to List Elements by Iterator
>
> We can use the following loop to access the list elements in sequence, starting with the one at index 0.
> 	// Access each list element.
> 	for (int index = 0; index < aList.size(); index++) {
> 		E nextElement = aList.get(index);
> 		// Do something with the element at position index (nextElement)
> 		. . .
> 	}
> The loop is executed aList.size() times; thus it is O($n$). During each iteration, we call the method get to retrieve the element at position index. If we assume that the method get begins at the first list node (head), each call to method get must advance a local reference (nodeRef) to the node at position index using a loop such as:
> 	// Advance nodeRef to the element at position index.
> 	Node<E> nodeRef = head;
> 	for (int j = 0; j < index; j++) {
> 		nodeRef = nodeRef.next;
> 	}
>
> This loop (in method get) executes index times, so it is also O($n$). Therefore, the performance of the nested loops used to process each element in a LinkedList is O($n^2$) and is very inefficient. We would like to have an alternative way to access the elements in a linked list sequentially.

> #### Removal Using `Iterator.remove` versus `List.remove`
>
> You could also use method LinkedList.remove to remove elements from a list. However, it is more efficient to remove multiple elements from a list using Iterator.remove than it would be to use LinkedList.remove. The LinkedList.remove method removes only one element at a time, so you would need to start at the beginning of the list each time and advance down the list to each element that you wanted to remove (O(n2) process). With the Iterator.remove method, you can remove elements as they are accessed by the Iterator object without having to go back to the beginning of the list (O(n) process).

> #### The Enhanced `for` Loop
>
> The enhanced for loop creates an Iterator object and implicitly calls its hasNext and next
> methods. Other Iterator methods, such as remove, are not available.
>
> **FORM**:
> 	for (formalParameter : expression) { . . . }
>
> **EXAMPLE**:
> 	for (String nextStr : myList) { . . . }
> 	for (int nextInt : aList) { . . . }
>
> **MEANING**:
> During each repetition of the loop, the variable specified by formalParameter accesses the next element of expression, starting with the first element and ending with the last. The expression must be an array or a collection that implements the Iterable interface. The Collection interface extends the Iterable interface so that all classes that implement it are implementors of the Iterable interface (see next section).



## The `ListIterator` Interface

### Overview

- The Iterator has some limitations. It can traverse the List only in the forward direction. It also provides only a remove method, not an add method. Also, to start an Iterator somewhere other than at first List element, you must write your own loop to advance the Iterator to the desired starting position.
- The `next` method moves the iterator forward and returns the element that was jumped over. The `previous` method moves the iterator backward and also returns the element that was jumped over.



### Interface `java.util.ListIterator<E>`

| Modifier and Type | Method          | Description                                                  |
| ----------------- | --------------- | ------------------------------------------------------------ |
| void              | add(E e)        | Inserts the specified element into the list (optional operation).<br />Inserts object obj into the list just before the item that would be returned by the next call to method next and after the item that would have been returned by method previous. If the method previous is called after add, the newly inserted object will be returned |
| boolean           | hasNext()       | Returns true if this list iterator has more elements when traversing the list in the forward direction. |
| boolean           | hasPrevious()   | Returns true if this list iterator has more elements when traversing the list in the reverse direction. |
| E                 | next()          | Returns the next element in the list and advances the cursor position. |
| int               | nextIndex()     | Returns the index of the element that would be returned by a subsequent call to next(). |
| E                 | previous()      | Returns the previous element in the list and moves the cursor position backwards. |
| int               | previousIndex() | Returns the index of the element that would be returned by a subsequent call to previous(). |
| void              | remove()        | Removes from the list the last element that was returned by next() or previous() (optional operation).<br />Removes the last item returned from a call to next or previous. If a call to remove is not preceded by a call to next or previous, the IllegalStateException is thrown |
| void              | set(E e)        | Replaces the last element returned by next() or previous() with the specified element (optional).<br />Replaces the last item returned from a call to next or previous with obj. If a call to set is not preceded by a call to next or previous, the IllegalStateException is thrown |





#### Methods in `java.util.LinkedList<E>` that Return `ListIterator`

| Modifier and Type | Method                  | Description                                                  |
| ----------------- | ----------------------- | ------------------------------------------------------------ |
| ListIterator\<E>  | listIterator()          | Returns a ListIterator that begins just before the first list element. |
| ListIterator\<E>  | listIterator(int index) | Returns a ListIterator that begins just before the position index. |



### Comparison of `Iterator` and `ListIterator`

- Because the interface `ListIterator<E>` is a subinterface of `Iterator<E>`, classes that implement `ListIterator` must provide all of the capabilities of both. 
- The `Iterator` interface requires fewer methods and can be used to iterate over more general data structures—that is, structures for which an index is not meaningful and ones for which traversing in only the **forward** direction is required. 
- It is for this reason that the Iterator is required by the `Collection` interface (more general), whereas the `ListIterator` is required only by the `List`
  interface (more specialized).



## The `Iterable` Interface

### Overview

- This interface requires only that a class that implements it provides an `iterator` method. As mentioned above, the `Collection` interface extends the `Iterable` interface, so all classes that implement the `List` interface (a subinterface of `Collection`) must provide an iterator method.



### Interface `java.lang.Iterable<T>`

| Modifier and Type      | Method and Description                                       |
| ---------------------- | ------------------------------------------------------------ |
| default void           | forEach(Consumer<? super T> action)<br/>Performs the given action for each element of the Iterable until all elements have been processed or the action throws an exception. |
| Iterator<T>            | iterator()<br/>Returns an iterator over elements of type T.  |
| default Spliterator<T> | spliterator()<br/>Creates a Spliterator over the elements described by this Iterable. |



# 7. Double-Linked List (Linked List)

### Overview

- A double‐linked list object would consist of a separate object with data fields `head` (a reference to the first list Node), `tail` (a reference to the last list Node), and size (the number of Nodes). Because both ends of the list are directly accessible, now insertion at either end is O(1); insertion else- where is still O(n).



### Implementation

- We can implement most of the `MyLinkedList` methods by delegation to the class `MyListIterator`, which will implement the `ListIterator` interface

#### Data Fields for Class `MyLinkedList<E>` (Double-Linked List)

| Data Field           | Attribute                                  |
| -------------------- | ------------------------------------------ |
| private Node<E> head | A reference to the first item in the list  |
| private Node<E> tail | A reference to the last item in the list   |
| private int size     | A count of the number of items in the list |



```java
package datastructures.list;

import java.util.*;
/** Class KWLinkedList implements a double‐linked list and
 a ListIterator. */
public class LinkedList<E> extends AbstractSequentialList<E> implements List<E>{

    /** A Node is the building block for a single‐linked list. */
    private static class Node<E> {
        // Data Fields
        /** The reference to the data. */
        private E data;
        /** The reference to the next node. */
        private Node<E> next;
        /** The reference to the previous node. */
        private Node<E> prev;

        // Constructors
        /** Creates a new node with a null next field.
         @param dataItem The data stored
         */
        private Node(E dataItem) {
            data = dataItem;
            next = null;
        }

        /** Creates a new node that references another node.
         @param dataItem The data stored
         @param nodeRef The node referenced by new node
         */
        private Node(E dataItem, Node<E> nodeRef) {
            data = dataItem;
            next = nodeRef;
        }
    }

    /** Inner class to implement the ListIterator interface. */
    private class MyListIterator implements ListIterator<E> {
        /** A reference to the next item. */
        private Node<E> nextItem;
        /** A reference to the last item returned. */
        private Node<E> lastItemReturned;
        /** The index of the current item. */
        private int index = 0;

        /** Construct a KWListIter that will reference the ith item.
         @param i The index of the item to be referenced
         */
        public MyListIterator(int i) {
            // Validate i parameter.
            if (i < 0 || i > size) {
                throw new IndexOutOfBoundsException("Invalid index " + i);
            }
            lastItemReturned = null; // No item returned yet.
            // Special case of last item.
            if (i == size) {
                index = size;
                nextItem = null;
            } else { // Start at the beginning
                nextItem = head;
                for (index = 0; index < i; index++) {
                    nextItem = nextItem.next;
                }
            }
        }

        /** Indicate whether movement forward is defined.
         @return true if call to next will not throw an exception
         */
        public boolean hasNext() {
            return nextItem != null;
        }

        /** Move the iterator forward and return the next item.
         @return The next item in the list
         @throws NoSuchElementException if there is no such object
         */
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastItemReturned = nextItem;
            nextItem = nextItem.next;
            index++;
            return lastItemReturned.data;
        }

        /** Indicate whether movement backward is defined.
         @return true if call to previous will not throw an exception
         */
        public boolean hasPrevious() {
            return (nextItem == null && size != 0)
                    || nextItem.prev != null;
        }

        /** Move the iterator backward and return the previous item.
         @return The previous item in the list
         @throws NoSuchElementException if there is no such object
         */
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            if (nextItem == null) { // Iterator is past the last element
                nextItem = tail;
            } else {
                nextItem = nextItem.prev;
            }
            lastItemReturned = nextItem;
            index--;
            return lastItemReturned.data;
        }

        /**
         * Returns the index of the element that would be returned by a subsequent call to next().
         *
         * @return the index of the element that would be returned by a subsequent call to next(),
         *         or the size of the list if there is no next element
         */
        @Override
        public int nextIndex() {
            return (hasNext()) ? index : size;
        }

        /**
         * Returns the index of the element that would be returned by a subsequent call to previous().
         *
         * @return the index of the element that would be returned by a subsequent call to previous(),
         *         or -1 if there is no previous element
         */
        @Override
        public int previousIndex() {
            return (hasPrevious()) ? index - 1 : -1;
        }

        /**
         * Removes from the list the last element that was returned by next() or previous().
         *
         * This method has not been verified!
         *
         * @throws IllegalStateException if no element has been previously returned by next() or previous()
         */
        @Override
        public void remove() {
            if (lastItemReturned == null) {
                throw new IllegalStateException("No element to remove");
            }
            Node<E> lastNext = lastItemReturned.next;
            Node<E> lastPrev = lastItemReturned.prev;

            if (lastPrev == null) { // Removing first element
                head = lastNext;
            } else {
                lastPrev.next = lastNext;
                lastItemReturned.prev = null;
            }

            if (lastNext == null) { // Removing last element
                tail = lastPrev;
            } else {
                lastNext.prev = lastPrev;
                lastItemReturned.next = null;
            }

            if (nextItem == lastItemReturned) {
                nextItem = lastNext;
            } else {
                index--;
            }

            lastItemReturned.data = null;
            lastItemReturned = null;
            size--;
        }

        /**
         * Replaces the last element returned by next() or previous() with the specified element.
         *
         * @param e the element with which to replace the last returned element
         * @throws IllegalStateException if no element has been previously returned by next() or previous()
         */
        @Override
        public void set(E e) {
            if (lastItemReturned == null) {
                throw new IllegalStateException("No element to set");
            }
            lastItemReturned.data = e;
        }

        /** Add a new item between the item that will be returned
         by next and the item that will be returned by previous.
         If previous is called after add, the element added is
         returned.
         @param obj The item to be inserted
         */
        public void add(E obj) {
            if (head == null) { // Add to an empty list.
                head = new Node<>(obj);
                tail = head;
            } else if (nextItem == head) { // Insert at head
                // Create a new node.
                Node<E> newNode = new Node<>(obj);
                // Link it to the nextItem.
                newNode.next = nextItem;
                // Link nextItem to the new node.
                nextItem.prev = newNode;
                // The new node is now the head.
                head = newNode;
            }
            else if(nextItem == null){ // Insert at tail
                // Create a new node.
                Node<E> newNode = new Node<>(obj);
                // Link the tail to the new node.
                tail.next = newNode;
                // Link the new node to the tail.
                newNode.prev = tail;
                // The new node is the new tail.
                tail = newNode;
            }
            else { // Insert into the middle.
                // Create a new node.
                Node<E> newNode = new Node<>(obj);
                // Link it to nextItem.prev.
                newNode.prev = nextItem.prev;
                nextItem.prev.next = newNode;
                // Link it to the nextItem.
                newNode.next = nextItem;
                nextItem.prev = newNode;
            }
            // Increase size and index and set lastItemReturned.
            size++;
            index++;
            lastItemReturned = null;
        } // End of method add.


    }
    // Data Fields
    /** A reference to the head of the list. */
    private Node<E> head = null;
    /** A reference to the end of the list. */
    private Node<E> tail = null;
    /** The size of the list. */
    private int size = 0;

    /** Add an item at position index.
     @param index The position at which the object is to be
     inserted
     @param obj The object to be inserted
     @throws IndexOutOfBoundsException if the index is out
     of range (i < 0 || i > size())
     */
    public void add(int index, E obj) {
        listIterator(index).add(obj);
    }

    @Override
    public ListIterator<E> listIterator(int i) {
        return new MyListIterator(i);
    }

    /** Get the element at position index.
     @param index Position of item to be retrieved
     @return The item at index
     */
    public E get(int index) {
        return listIterator(index).next();
    }

    /**
     * Return the number of elements in this list.
     * @return The number of elements in this list.
     */
    @Override
    public int size() {
        return size;
    }
}
```



### Extra

> #### Inner Classes: Static and Nonstatic
>
> There are two inner classes in class LinkedList\<E>: class Node<E> and class MyListIterator. We declare Node\<E> to be static because there is no need for its methods to access the data fields of its parent class (LinkedList\<E>). We can’t declare MyListIterator to be static because its methods access and modify the data fields of the LinkedList object that creates the MyListIterator object. An inner class that is not static contains an implicit reference to its parent object, just as it contains an implicit reference to itself. Because MyListIterator is not static and can reference data fields of its parent class LinkedList\<E>, the type parameter <E> is considered to be previously defined; therefore, it cannot appear as part of the class name.

> #### PITFALL
>
> **Defining MyListIterator as a Generic Inner Class**
> If you define class MyListIterator as
> 	private class MyListIterator<E>...
> you will get an incompatible types syntax error when you attempt to reference data field head or tail (type Node\<E>) inside class MyListIterator.



# 8. Circular Linked List

### Overview

- **Circularly Linked Lists:** Linked lists are traditionally viewed as storing a sequence of items in a linear order, from first to last. However, there are many applications in which data can be more naturally viewed as having a cyclic order, with well-defined neighboring relationships, but no fixed beginning or end.
- Example Application: Round-Robin Scheduling
  - A process is given a short turn to execute, known as a time slice, but it is interrupted when the slice ends, even if its job is not yet complete. Each active process is given its own time slice, taking turns in a cyclic order.



### Class `CircularlyLinkedList<E>`

#### Data Fields

| Data Field           | Attribute                                  |
| -------------------- | ------------------------------------------ |
| private Node<E> tail | A reference to the last item in the list   |
| private int size     | A count of the number of items in the list |

#### Method Summary

| Modifier and Type | Method        | Description                                                  |
| ----------------- | ------------- | ------------------------------------------------------------ |
| void              | addFirst(E e) | Inserts the specified element at the beginning of this list. |
| void              | addLast(E e)  | Appends the specified element to the end of this list.       |
| E                 | getFirst()    | Returns the first element in this list.                      |
| E                 | getLast()     | Returns the last element in this list.                       |
| void              | rotate()      | Rotates the first element to the back of the list.           |
| E                 | removeFirst() | Removes and returns the first element from this list.        |

### Implementation

```java
public class CircularLinkedList<E> {
    // Nested node class identical to that of the SinglyLinkedList class
    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }

    private Node<E> tail = null; // We store tail (but not head)
    private int size = 0; // Number of nodes in the list

    public CircularLinkedList() { } // Constructs an initially empty list

    // Access methods
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E getFirst() {
        // Returns (but does not remove) the first element
        if (isEmpty()) return null;
        return tail.getNext().getElement(); // The head is *after* the tail
    }

    public E getLast() {
        // Returns (but does not remove) the last element
        if (isEmpty()) return null;
        return tail.getElement();
    }

    // Update methods
    public void rotate() {
        // Rotate the first element to the back of the list
        if (tail != null) // If empty, do nothing
            tail = tail.getNext(); // The old head becomes the new tail
    }

    public void addFirst(E e) {
        // Adds element e to the front of the list
        if (size == 0) {
            tail = new Node<>(e, null);
            tail.setNext(tail); // Link to itself circularly
        } else {
            Node<E> newest = new Node<>(e, tail.getNext());
            tail.setNext(newest);
        }
        size++;
    }

    public void addLast(E e) {
        // Adds element e to the end of the list
        addFirst(e); // Insert new element at front of list
        tail = tail.getNext(); // Now new element becomes the tail
    }

    public E removeFirst() {
        // Removes and returns the first element
        if (isEmpty()) return null; // Nothing to remove
        Node<E> head = tail.getNext();
        if (head == tail)
            tail = null; // Must be the only node left
        else
            tail.setNext(head.getNext()); // Removes ”head” from the list
        size--;
        return head.getElement();
    }
}

```



> TODO: The implementations of some methods are missing and should be fixed in the future.



### Performance

| Operations/Complexity | Big O |
| --------------------- | ----- |
| addFirst(E e)         | O(1)  |
| addLast(E e)          | O(1)  |
| getFirst()            | O(1)  |
| getLast()             | O(1)  |
| rotate()              | O(1)  |
| removeFirst()         | O(1)  |



# 9. The Collections Framework Design

## The `Collection` Interface

- The `Collection` interface specifies a subset of the methods specified in the `List` interface.
- Specifically, the add(int, E), get(int), remove(int), set(int, E), and related methods (all of which have an int parameter that represents a position) are not in the `Collection` interface, but the add(E) and remove(Object) methods, which do not specify a position, are included.
- The `iterator` method is also included in the `Collection` interface. Thus, you can use an `Iterator` to access all of the items in a `Collection`, but the order in which they are retrieved is not necessarily related to the order in which they were inserted
- The Collection interface is part of the Collections Framework. This interface has three subinterfaces: the `List` interface, the `Queue` interface, and the `Set` interface. The Java API does not provide any direct implementation of the `Collection` interface. The interface is used to reference collections of data in the most general way.

![image-20240104223001533](/home/denizkorkmaz/.config/Typora/typora-user-images/image-20240104223001533.png)

### Common Features of Collections

- A few features can be considered fundamental:

  - Collections grow as needed.

  - Collections hold references to objects.

  - Collections have at least two constructors: one to create an empty collection and one to make a copy of another collection.

- For collections implementing the List interface, the order of the elements is determined by the index of the elements. In the more general Collection, the order is not specified.



### Interface `java.util.Collection<E>`

| Modifier and Type | Method and Description                                       |
| ----------------- | ------------------------------------------------------------ |
| boolean           | add(E e)<br/>Ensures that this collection contains the specified element (optional operation). |
| boolean           | addAll(Collection<? extends E> c)<br/>Adds all of the elements in the specified collection to this collection (optional operation). |
| void              | clear()<br/>Removes all of the elements from this collection (optional operation). |
| boolean           | contains(Object o)<br/>Returns true if this collection contains the specified element. |
| boolean           | containsAll(Collection<?> c)<br/>Returns true if this collection contains all of the elements in the specified collection. |
| boolean           | equals(Object o)<br/>Compares the specified object with this collection for equality. |
| boolean           | isEmpty()<br/>Returns true if this collection contains no elements. |
| Iterator<E>       | iterator()<br/>Returns an iterator over the elements in this collection. |
| boolean           | remove(Object o)<br/>Removes a single instance of the specified element from this collection, if it is present (optional operation). |
| boolean           | removeAll(Collection<?> c)<br/>Removes all of this collection's elements that are also contained in the specified collection (optional operation). |
| boolean           | retainAll(Collection<?> c)<br/>Retains only the elements in this collection that are contained in the specified collection (optional operation). |
| int               | size()<br/>Returns the number of elements in this collection. |
| Object[]          | toArray()<br/>Returns an array containing all of the elements in this collection. |



## The `AbstractCollection`, `AbstractList`, and `AbstractSequentialList` Classes

- If you look at the Java API documentation, you will see that the `Collection` and `List` interfaces specify a large number of methods. To help implement these interfaces, the Java API includes the `AbstractCollection` and `AbstractList` classes. You can think of these classes as a
  kit that can be used to build implementations of their corresponding interface. Most of the methods are provided, but you need to add a few to make it complete.

  - To implement the `Collection` interface completely, you need only extend the `AbstractCollection` class, provide an implementation of the add, size, and iterator methods, and supply an inner class to implement the Iterator interface. 

  - To implement the `List` interface, you can extend the `AbstractList` class and provide an implementation of the add(int, E), get(int), remove(int),
    set(int, E), and size() methods. 

    - Since we provided these methods in our `ArrayList`, we can make it a complete implementation of the `List` interface by changing the class declaration to 
      ```java
      public class ArrayList<E> extends AbstractList<E> implements List<E>
      ```

      Note that the `AbstractList` class implements the iterator and listIterator methods using the index associated with the elements.

    - Another way to implement the List interface is to extend the `AbstractSequentialList` class, implement the listIterator and size methods, and provide an inner class that implements the `ListIterator` interface. This was the approach we took in our `LinkedList`. Thus, by
      changing the class declaration to

      ```java
       public class LinkedList<E> extends AbstractSequentialList<E> implements List<E>
      ```

      it becomes a complete implementation of the List interface. 

    - Our `LinkedList` class included the add, get, remove, and set methods. These are provided by the `AbstractSequentialList`, so we could remove them from our `LinkedList` class and still have a complete List implementation.



## The `List` and `RandomAccess` Interface

- The `RandomAccess` interface is applied only to those implementations in which indexed operations are efficient (e.g., ArrayList). An algorithm can then test to see if a parameter of type List is also of type `RandomAccess` and, if not, copy its contents into an `ArrayList` temporarily so that the indexed operations can proceed more efficiently. After the indexed operations are completed, the contents of the `ArrayList` are copied back to the original.
