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

