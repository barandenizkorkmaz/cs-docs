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



# 2. Maps

### The `Map` Hierarchy

- Figure below shows part of the `Map` hierarchy in the `Java API`. Although not strictly part of the `Collection` hierarchy, the `Map` interface defines a structure that relates elements in one set to elements in another set. The first set, called the keys, must implement the `Set` interface; that is, the keys are unique. The second set is not strictly a `Set` but an arbitrary `Collection` known as the values. These are not required to be unique. The `Map` is a more useful structure than the `Set`. In fact, the `Java API` implements the `Set` using a `Map`.

![image-20240113234358226](/home/denizkorkmaz/.config/Typora/typora-user-images/image-20240113234358226.png)

- The `TreeMap` uses a `Red–Black binary search tree` (discussed in future chapters) as its underlying data structure, and the `ConcurrentSkipListMap` uses a `skip list` (discussed in future chapters) as its underlying data structure. We will focus on the `HashMap` and show how to implement it later in the chapter.



### Interface `java.util.Map`

| **Modifier and Type**  | **Method**                                                   | **Description**                                              |
| ---------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| `void`                 | `clear()`                                                    | Removes all of the mappings from this map (optional operation). |
| `boolean`              | `containsKey(Object key)`                                    | Returns true if this map contains a mapping for the specified key. |
| `boolean`              | `containsValue(Object value)`                                | Returns true if this map maps one or more keys to the specified value. |
| `Set<Map.Entry<K, V>>` | `entrySet()`                                                 | Returns a Set view of the mappings contained in this map.    |
| `boolean`              | `equals(Object o)`                                           | Compares the specified object with this map for equality.    |
| `default void`         | `forEach(BiConsumer<? super K, ? super V> action)`           | Performs the given action for each entry in this map until all entries have been processed or the action throws an exception. |
| `V`                    | `get(Object key)`                                            | Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key. |
| `default V`            | `getOrDefault(Object key, V defaultValue)`                   | Returns the value to which the specified key is mapped, or `defaultValue` if this map contains no mapping for the key. |
| `int`                  | `hashCode()`                                                 | Returns the hash code value for this map.                    |
| `boolean`              | `isEmpty()`                                                  | Returns true if this map contains no key-value mappings.     |
| `Set<K>`               | `keySet()`                                                   | Returns a Set view of the keys contained in this map.        |
| `default V`            | `merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction)` | If the specified key is not already associated with a value or is associated with null, associates it with the given non-null value. |
| `V`                    | `put(K key, V value)`                                        | Associates the specified value with the specified key in this map (optional operation). |
| `void`                 | `putAll(Map<? extends K, ? extends V> m)`                    | Copies all of the mappings from the specified map to this map (optional operation). |
| `default V`            | `putIfAbsent(K key, V value)`                                | If the specified key is not already associated with a value (or is mapped to null) associates it with the given value and returns null, else returns the current value. |
| `V`                    | `remove(Object key)`                                         | Removes the mapping for a key from this map if it is present (optional operation). |
| `default boolean`      | `remove(Object key, Object value)`                           | Removes the entry for the specified key only if it is currently mapped to the specified value. |
| `default V`            | `replace(K key, V value)`                                    | Replaces the entry for the specified key only if it is currently mapped to some value. |
| `default boolean`      | `replace(K key, V oldValue, V newValue)`                     | Replaces the entry for the specified key only if currently mapped to the specified value. |
| `default void`         | `replaceAll(BiFunction<? super K, ? super V, ? extends V> function)` | Replaces each entry's value with the result of invoking the given function on that entry until all entries have been processed or the function throws an exception. |
| `int`                  | `size()`                                                     | Returns the number of key-value mappings in this map.        |
| `Collection<V>`        | `values()`                                                   | Returns a Collection view of the values contained in this map. |



# 3. Hash Tables

