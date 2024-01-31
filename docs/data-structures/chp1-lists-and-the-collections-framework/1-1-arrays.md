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
| static <T> T[]       | copyOf(T[] original, int newLength)         | Copies the specified array, truncating or padding with nulls (if necessary) so the copy has the specified length. |
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