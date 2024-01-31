
# 4. Implementation Considerations for Maps and Sets

### Methods `hashCode` and `equals`

- Method `Object.equals` compares two objects based on their addresses, not their contents. Similarly, method `Object.hashCode` calculates an object’s hash code based on its address, not its contents.

- Most predefined classes (e.g., String and Integer) override method `equals` and method `hashCode`.

- If you override the equals method, Java recommends you also override the `hashCode` method. Otherwise, your class will violate the Java contract for `hashCode`, which states:

  ```java
  if obj1.equals(obj2) is true, then obj1.hashCode() == obj2.hashCode().
  ```



### Implementing `HashSetOpen`

- We can modify the hash table methods to implement a hash set. Table below compares corresponding Map and Set methods.

  | **Map Method**        | **Set Method**               |
    | --------------------- | ---------------------------- |
  | V get(Object key)     | boolean contains(Object key) |
  | V put(K key, V value) | boolean add(K key)           |
  | V remove(Object key)  | boolean remove(Object key)   |



### Writing `HashSetOpen` as an Adapter Class

- Instead of writing new methods from scratch, we can implement `HashSetOpen` as an adapter class with the data field.

```java
private final IHashMap<K, V> setMap = new HashtableOpen<>();
```

- We can write methods contains, add, and remove as follows. Because the map stores key–value pairs, we will have each set element reference an Entry object with the same key and value.



### Implementing the Java `Map` and `Set` Interfaces

- Java API uses a hash table to implement both the `Map` and `Set` interfaces (class `HashMap` and class `HashSet`).
- The task of implementing these interfaces is simplified by the inclusion of abstract classes `AbstractMap` and `AbstractSet` in the `Collections` framework. These classes provide implementations of several methods for the Map and Set interfaces. So if class `HashTableOpen` extends class `AbstractMap`, we can reduce the amount of additional work we need to do.
    - We should also replace `IHashMap` with `Map`. Thus, the declaration for `HashTableOpen` would be class `HashTableOpen<K, V>` extends `AbstractMap<K, V>` implements `Map<K, V>`.
- The `AbstractMap` provides relatively inefficient $O(n)$ implementations of the get and put methods. Because we overrode these methods in both our implementations (`HashTableOpen` and `HashTableChain`), we will get $O(1)$ expected performance. There are other, less critical methods that we don’t need to provide because they are implemented in `AbstractMap` or its superclasses, such as `clear`, `isEmpty`, `putAll`, `equals`, `hashCode`, and `toString`.



### Interface `Map.Entry` and Class `AbstractMap.SimpleEntry`

- One requirement on the key–value pairs for a Map object is that they implement the interface `Map.Entry<K, V>`, which is an inner interface of interface `Map`.
- This may sound a bit confusing, but what it means is that an implementer of the Map interface must contain an inner class `Entry`. The `AbstractMap` includes the inner class `SimpleEntry` that implements the `Map.Entry` interface. We can remove the inner class `Entry<K, V> `and replace new `Entry` with new `SimpleEntry`.

### Creating a Set View of a Map

- Method `entrySet` creates a set view of the entries in a Map. This means that method entrySet
  returns an object that implements the Set interface—that is, a set.



### Classes `TreeMap` and `TreeSet`

- Besides `HashMap` and `HashSet`, the `Java Collections Framework` provides classes `TreeMap` and
  `TreeSet` that implement the `Map` and `Set` interfaces. These classes use a `Red–Black tree`, which is a balanced binary search tree.
- We discussed earlier that the performances for search, retrieval, insertion, and removal operations are better for a hash table than for a binary search tree (expected O(1) versus O(log n)).
- However, the primary advantage of a binary search tree is that it can be traversed in sorted order. Hash tables, however, can’t be traversed in any meaningful way. Also, subsets based on a range of key values can be selected using a `TreeMap` but not by using a `HashMap`