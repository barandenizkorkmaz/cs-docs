# 1. Sets

### The `Set` Hierarchy

![image-20240113234433002](/home/denizkorkmaz/.config/Typora/typora-user-images/image-20240113234433002.png)

- Figure above shows the part of the `Collections Framework` that relates to sets. It includes interfaces `Set`, `Sortedset`, and `Navigableset`; abstract class `AbstractSet`; and actual classes `HashSet`, `TreeSet`, and `ConcurrentSkipListSet`. The `HashSet` is a set that is implemented using a hash table (discussed later). The `TreeSet` is implemented using a special kind of binary search tree, called the `Red–Black tree` (discussed in future chapters). The `ConcurrentSkipListSet` is implemented using a `skip list` (discussed in future chapters).



### Interface `java.util.Set`

| **Modifier and Type** | **Method**                          | **Description**                                              |
| --------------------- | ----------------------------------- | ------------------------------------------------------------ |
| `boolean`             | `add(E e)`                          | Adds the specified element to this set if it is not already present (optional operation). |
| `boolean`             | `addAll(Collection<? extends E> c)` | Adds all of the elements in the specified collection to this set if they're not already present (optional operation). |
| `void`                | `clear()`                           | Removes all of the elements from this set (optional operation). |
| `boolean`             | `contains(Object o)`                | Returns true if this set contains the specified element.     |
| `boolean`             | `containsAll(Collection<?> c)`      | Returns true if this set contains all of the elements of the specified collection. |
| `boolean`             | `equals(Object o)`                  | Compares the specified object with this set for equality.    |
| `int`                 | `hashCode()`                        | Returns the hash code value for this set.                    |
| `boolean`             | `isEmpty()`                         | Returns true if this set contains no elements.               |
| `Iterator<E>`         | `iterator()`                        | Returns an iterator over the elements in this set.           |
| `boolean`             | `remove(Object o)`                  | Removes the specified element from this set if it is present (optional operation). |
| `boolean`             | `removeAll(Collection<?> c)`        | Removes from this set all of its elements that are contained in the specified collection (optional operation). |
| `boolean`             | `retainAll(Collection<?> c)`        | Retains only the elements in this set that are contained in the specified collection (optional operation). |
| `int`                 | `size()`                            | Returns the number of elements in this set (its cardinality). |
| `Object[]`            | `toArray()`                         | Returns an array containing all of the elements in this set. |

#### Comparison of `List` and `Set`

- Collections implementing the Set interface must contain unique elements. Unlike the List.add method, the Set.add method will return false if you attempt to insert a duplicate item.
- Unlike a List, a Set does not have a get method. Therefore, elements cannot be accessed by index. So if setA is a Set object, the method call setA.get(0) would cause the syntax error method get(int) not found.
- Although you can’t reference a specific element of a Set, you can iterate through all its elements using an Iterator object. The loop below accesses each element of Set object setA. However, the elements will be accessed in arbitrary order. This means that they will not necessarily be accessed in the order in which they were inserted.



### `HashSet` vs `TreeSet` vs `LinkedHashSet`

|         Features         |                           HashSet                            |                           TreeSet                            |                        LinkedHashSet                         |
| :----------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
|     Internal Working     |     HashSet internally uses HashMap for storing objects      |       TreeSet uses TreeMap internally to store objects       | LinkedHashSet uses LinkedHashMap internally to store objects |
|       When To Use        | If you don’t want to maintain insertion order but want to store unique objects | If you want to sort the elements according to some Comparator then use TreeSet | If you want to maintain the insertion order of elements then you can use LinkedHashSet |
|          Order           |          HashSet does not maintain insertion order           | While TreeSet orders the elements according to supplied Comparator. By default, objects will be placed according to their natural ascending order. |    LinkedHashSet maintains the insertion order of objects    |
| Complexity of Operations | HashSet gives O(1) complexity for insertion, removing, and retrieving objects | While TreeSet gives the performance of order O(log(n)) for insertion, removing, and retrieving operations. | LinkedHashSet gives insertion, removing, and retrieving operations performance in order O(1). |
|       Performance        | The performance of HashSet is better when compared to LinkedHashSet and TreeSet. | TreeSet performance is better than LinkedHashSet except for insertion and removal operations because it has to sort the elements after each insertion and removal operation. | The performance of LinkedHashSet is slower than TreeSet. It is almost similar to HashSet but slower because LinkedHashSet internally maintains LinkedList to maintain the insertion order of elements |
|         Compare          | HashSet uses equals() and hashCode() methods to compare the objects | TreeSet uses compare() and compareTo() methods to compare the objects | LinkedHashSet uses equals() and hashCode() methods to compare it’s objects |
|      Null Elements       |             HashSet allows only one null value.              | TreeSet does not permit null value. If you insert null value into TreeSet, it will throw NullPointerException. |          LinkedHashSet allows only one null value.           |
|          Syntax          |                 HashSet obj = new HashSet();                 |                 TreeSet obj = new TreeSet();                 |           LinkedHashSet obj = new LinkedHashSet();           |
