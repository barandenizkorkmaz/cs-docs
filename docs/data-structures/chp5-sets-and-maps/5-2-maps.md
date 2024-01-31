
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

