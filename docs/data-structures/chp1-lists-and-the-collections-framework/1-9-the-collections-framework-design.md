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
