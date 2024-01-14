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

- Before we discuss the details of implementing the required methods of the Set and Map interfaces, we will describe a data structure, the hash table, that can be used as the basis for such an implementation.
- Using a hash table enables us to retrieve an item in constant time (expected O(1)). We say expected O(1) rather than just O(1) because there will be some cases where the performance will be much worse than O(1) and may even be O(n), but on the average, we expect that it will be O(1).
- Properties of Hash Functions
  - Simple and efficient to compute
  - Provides a large set of possible values to map domain
  - Should provide an (almost) equal distribution (mapping) of values
  - The probability of two distinct items to generate the same hash value should be as low as possible



## Open Addressing

- Two ways to organize a hash tables:
  - open addressing
  - chaining
- **Procedure:** Open Addressing
  - Each hash table element (type Object) references a single key–value pair. 
  - We can use the following simple approach (called `linear probing`) to access an item in a hash table. 
  - If the index calculated for an item’s key is occupied by an item with that key, we have found
    the item. 
  - If that element contains an item with a different key, we increment the index by 1.
  - We keep incrementing the index (modulo the table length) until either we find the key we are seeking or we reach a null entry. 
  - A null entry indicates that the key is not in the table.

```java
Algorithm For Accessing an Item in a Hash Table

Compute the index by taking the item’s hashCode() % table.length.
if table[index] is null
	The item is not in the table.
else if table[index] is equal to the item
	The item is in the table.
else
	Continue to search the table by incrementing the index until either the item is found 	or a null entry is found.
```

### Table Wraparound and Search Termination

- Note that as you increment the table index, your table should wrap around (as in a circular array) so that the element with subscript 0 “follows” the element with subscript table.length ‐ 1. 
- This enables you to use the entire table, not just the part with subscripts larger than the hash code value, but it leads to the potential for an infinite loop in Step 6 of the algorithm. If the table is full and the objects examined so far do not match the one you are seeking, how do you know when to stop? 
- One approach would be to stop when the index value for the next probe is the same as the hash code value for the object. This means that you have come full circle to the starting value for the index. 
- A second approach would be to ensure that the table is never full by increasing its size after an insertion **if its occupancy rate exceeds a specifieif its occupancy rate exceeds a specified thresholdd threshold**. **This is the approach that we take in our implementation.**



### Traversing a Hash Table

- One thing that you cannot do is traverse a hash table in a meaningful way.



### Deleting an Item Using Open Addressing

- When an item is deleted, we cannot just set its table entry to `null`. If we do, then when we search for an item that may have collided with the deleted item, we may incorrectly conclude that the item is not in the table. (Because the item that collided was inserted after the deleted item, we will have stopped our search prematurely.) 
- By storing a dummy value when an item is deleted, we force the search algorithm to keep looking until either the desired item is found or a null value, representing a free cell, is located. Although the use of a dummy value solves the problem, keep in mind that it can lead to search inefficiency, particularly when there are many deletions. Removing items from the table does not reduce the search time because the dummy value is still in the table and is part of a search chain. 
- In fact, you **cannot** even replace a deleted value with a new item because you still need to go to the end of the search chain to ensure that the new item is not already present in the table. So deleted items waste storage space and reduce search efficiency. 
- In the worst case, if the table is almost full and then most of the items are deleted, you will have $O(n)$ performance when searching for the few items remaining in the table.



### Reducing Collisions by Expanding the Table Size

- The first step in reducing these collisions is to use a prime number for the size of the table. 
- In addition, the probability of a collision is proportional to how full the table is. Therefore, when the hash table becomes sufficiently full, a larger table should be allocated and the entries reinserted.

- You expand a hash table using an algorithm called `rehashing`.

```bash
Algorithm For Rehashing
1. Allocate a new hash table with twice the capacity of the original.
2. Reinsert each old table entry that has not been deleted into the new hash table.
3. Reference the new table instead of the original.
```



### Reducing Collisions Using Quadratic Probing

- The problem with `linear probing` is that it tends to form clusters of keys in the table, causing longer search chains.
- One approach to reduce the effect of clustering is to use `quadratic probing` instead of `linear probing`. In quadratic probing, the increments form a quadratic series $(1 + 2^2 + 3^2 + · · ·)$.

```java
probeNum++;
index = (startIndex + probeNum * probeNum) % table.length
```

- **Problems with Quadratic Probing**

  - One disadvantage of quadratic probing is that the next index calculation is a bit time‐consuming as it involves a multiplication, an addition, and a modulo division.

  - A more efficient way to calculate the next index follows:

    ```java
    k += 2;
    index = (index + k) % table.length;
    ```

  - A more serious problem with quadratic probing is that not all table elements are examined when looking for an insertion index, so it is possible that an item can’t be inserted even when the table is not full.

  - It is also possible that your program can get stuck in an infinite loop while searching for an empty slot. 

  - It can be proved that if the table size is a prime number and the table is never more than half full, this can’t happen. However, requiring that the table be half empty at all times wastes quite a bit of memory.

  - For these reasons, we will use linear probing in our implementation.



## Chaining

- An alternative to open addressing is a technique called `chaining`, in which each table element references a linked list that contains all the items that hash to the same table index.
- This linked list is often called a `bucket`, and this approach is sometimes called `bucket hashing`.
- Instead of incrementing the table index to access the next item with a particular hash code value, you traverse the linked list referenced by the table element with index `hashCode() % table.length`.
- One advantage of chaining is that only items that have the same value for `hashCode() % table. length` will be examined when looking for an object. In open addressing, search chains can overlap, so a search chain may include items in the table that have different starting index values.
- A second advantage is that you can store more elements in the table than the number of table slots (indexes), which is not the case for open addressing.
- To **delete** an item, simply remove it from the list. In contrast to open addressing, removing an item actually deletes it, so it will not be part of future search chains.



## Performance of Hash Tables

- `load factor`: The number of filled cells divided by table size.
  - The load factor has the greatest effect on hash table performance.
  - Lower the load factor the better the performance because there is less chance of collision.
  - If there are no collisions, the performance for search and retrieval is $O(1)$.
- **Performance of Open Addressing versus Chaining**
  - Expected number of comparisons, c, for open addressing with linear probing and a load factor L:
    - $c = \dfrac{1}{2}(1 + \dfrac{1}{1 - L})$
    - L = number of filled cells/table size
  - Expected number of comparisons, c, for chaining and a load factor L (L is average number of items in a list here = # of items divided by table size:
    - $c = 1 + \dfrac{L}{2}$
  - For values of L between 0.0 and 0.75, the results for chaining are similar to those of linear probing. But chaining gives better performance than linear probing for higher load factors.
  - **Quadratic probing** gives performance that is between those of linear probing and chaining.
- **Performance of Hash Tables versus Sorted Arrays and Binary Trees**
  - The performance of hashing is certainly preferable to that of binary search of an array (or a binary search tree), particularly if L is less than 0.75. 
  - However, the trade-off is that the lower the load factor, the more unfilled storage cells there are in a hash table, whereas there are no empty cells in a sorted array. 
  - Because a binary search tree requires three references per node (the item, the left subtree, and the right subtrees), more storage would be required for a binary search tree than for a hash table with a load factor of 0.75.
- **Storage Requirements for Open Addressing and Chaining**
  - Next, we consider the effect of chaining on storage requirements. For a table with a load factor of L, the number of table elements required is n (the size of the table). 
  - For open addressing, the number of references to an item (a key–value pair) is n. 
  - For chaining, the average number of nodes in a list is **L**. If we use the `Java API LinkedList`, there will be three references in each node (the item, the next list element, and the previous element). However, we could use our own single‐linked list and eliminate the previous‐element reference (at some time cost for deletions). Therefore, we will require storage for $n + n*2L$ references.





## Implementing the Hash Table

### Interface `IHashMap`

```java
package datastructures.setmap;

public interface IHashMap<K, V> {
    V get(Object key);

    boolean isEmpty();

    V put(K key, V value);

    V remove(Object key);

    int size();
}
```



### Class `HashTableOpen`

```java
package datastructures.setmap;

/** Hash table implementation using open addressing. */
public class HashTableOpen<K, V> implements IHashMap<K, V> {
    // Insert inner class Entry<K, V> here.
    /** Contains key‐value pairs for a hash table. */
    private static class Entry<K, V> {
        /** The key */
        private final K key;
        /** The value */
        private V value;
        /** Creates a new key‐value pair.
         @param key The key
         @param value The value
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        /** Retrieves the key.
         @return The key
         */
        public K getKey() {
            return key;
        }
        /** Retrieves the value.
         @return The value
         */
        public V getValue() {
            return value;
        }
        /** Sets the value.
         @param val The new value
         @return The old value
         */
        public V setValue(V val) {
            V oldVal = value;
            value = val;
            return oldVal;
        }
    }

    // Data Fields
    private Entry<K, V>[] table;
    private static final int START_CAPACITY = 101;

    private double LOAD_THRESHOLD = 0.75;
    private int numKeys;
    private int numDeletes;
    private final Entry<K, V> DELETED =
            new Entry<>(null, null);

    // Constructor
    public HashTableOpen() {
        table = new Entry[START_CAPACITY];
    }

    /** Finds either the target key or the first empty slot in the
     search chain using linear probing.
     @pre The table is not full.
     @param key The key of the target object
     @return The position of the target or the first empty slot if
     the target is not in the table.
     */
    private int find(Object key) {
        // Calculate the starting index.
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length;
            // Make it positive.
            // Increment index until an empty slot is reached or the key is found.
        while ((table[index] != null)
                && (!key.equals(table[index].getKey()))) {
            index++;
            // Check for wraparound.
            if (index >= table.length)
                index = 0;
                // Wrap around.
        }
        return index;
    }

    /** Method get for class HashtableOpen.
     @param key The key being sought
     @return the value associated with this key if found;
     otherwise, null
     */
    @Override
    public V get(Object key) {
        // Find the first table element that is empty
        // or the table element that contains the key.
        int index = find(key);
        // If the search is successful, return the value.
        if (table[index] != null)
            return table[index].getValue();
        else
            return null; // key not found.
    }

    /** Method put for class HashtableOpen.
     @post This key‐value pair is inserted in the
     table and numKeys is incremented. If the key is already
     in the table, its value is changed to the argument
     value and numKeys is not changed. If the LOAD_THRESHOLD
     is exceeded, the table is expanded.
     @param key The key of item being inserted
     @param value The value for this key
     @return Old value associated with this key if found;
     otherwise, null
     */
    @Override
    public V put(K key, V value) {
        // Find the first table element that is empty
        // or the table element that contains the key.
        int index = find(key);
        // If an empty element was found, insert new entry.
        if (table[index] == null) {
            table[index] = new Entry<>(key, value);
            numKeys++;
            // Check whether rehash is needed.
            double loadFactor =
                    (double) (numKeys + numDeletes) / table.length;
            if (loadFactor > LOAD_THRESHOLD)
                rehash();
            return null;
        }
        // assert: table element that contains the key was found.
        // Replace value for this key.
        V oldVal = table[index].getValue();
        table[index].setValue(value);
        return oldVal;
    }

    @Override
    public V remove(Object key) {
        // Find the first table element that is empty or the table element that contains the key.
        int index = find(key);
        // if an empty element was found return null
        if(table[index] == null || table[index] == this.DELETED){
            return null;
        }
        // Key was found. Remove this table element by setting it to reference DELETED, increment
        // numDeletes, and decrement numKeys.
        V oldVal = table[index].getValue();
        table[index] = this.DELETED;
        this.numDeletes++;
        this.numKeys--;
        // Return the value associated with this key.
        return oldVal;
    }

    @Override
    public int size() {
        return this.numKeys;
    }

    @Override
    public boolean isEmpty() {
        return (this.numKeys != 0);
    }

    /** Expands table size when loadFactor exceeds LOAD_THRESHOLD
     @post The size of the table is doubled and is an odd integer.
     Each nondeleted entry from the original table is
     reinserted into the expanded table.
     The value of numKeys is reset to the number of items
     actually inserted; numDeletes is reset to 0.
     */
    private void rehash() {
        // Save a reference to oldTable.
        Entry<K, V>[] oldTable = table;
        // Double capacity of this table.
        table = new Entry[2 * oldTable.length + 1];
        // Reinsert all items in oldTable into expanded table.
        numKeys = 0;
        numDeletes = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if ((oldTable[i] != null) && (oldTable[i] != DELETED)) {
                // Insert entry in expanded table
                put(oldTable[i].getKey(), oldTable[i].getValue());
            }
        }
    }
}
```



### Class `HashTableChain`

```java
package datastructures.setmap;

import java.util.*;
/** Hash table implementation using chaining. */
public class HashTableChain<K, V> implements IHashMap<K, V> {
    // Insert inner class Entry<K, V> here.
    /** Contains key‐value pairs for a hash table. */
    private static class Entry<K, V> {
        /** The key */
        private final K key;
        /** The value */
        private V value;
        /** Creates a new key‐value pair.
         @param key The key
         @param value The value
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        /** Retrieves the key.
         @return The key
         */
        public K getKey() {
            return key;
        }
        /** Retrieves the value.
         @return The value
         */
        public V getValue() {
            return value;
        }
        /** Sets the value.
         @param val The new value
         @return The old value
         */
        public V setValue(V val) {
            V oldVal = value;
            value = val;
            return oldVal;
        }
    }

    /** The table */
    private LinkedList<Entry<K, V>>[] table;
    /** The number of keys */
    private int numKeys;
    /** The capacity */
    private static final int CAPACITY = 101;
    /** The maximum load factor */
    private static final double LOAD_THRESHOLD = 3.0;
    // Constructor
    public HashTableChain() {
        table = new LinkedList[CAPACITY];
    }

    /** Method get for class HashtableChain.
     @param key The key being sought
     @return The value associated with this key if found;
     otherwise, null
     */
    @Override
    public V get(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length;
        if (table[index] == null)
            return null; // key is not in the table.
        // Search the list at table[index] to find the key.
        for (Entry<K, V> nextItem : table[index]) {
            if (nextItem.getKey().equals(key))
                return nextItem.getValue();
        }
        // assert: key is not in the table.
        return null;
    }

    /** Method put for class HashtableChain.
     @post This key‐value pair is inserted in the
     table and numKeys is incremented. If the key is already
     in the table, its value is changed to the argument
     value and numKeys is not changed.
     @param key The key of item being inserted
     @param value The value for this key
     @return The old value associated with this key if
     found; otherwise, null
     */
    @Override
    public V put(K key, V value) {
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length;
        if (table[index] == null) {
            // Create a new linked list at table[index].
            table[index] = new LinkedList<>();
        }
        // Search the list at table[index] to find the key.
        for (Entry<K, V> nextItem : table[index]) {
            // If the search is successful, replace the old value.
            if (nextItem.getKey().equals(key)) {
                // Replace value for this key.
                V oldVal = nextItem.getValue();
                nextItem.setValue(value);
                return oldVal;
            }
        }
        // assert: key is not in the table, add new item.
        table[index].addFirst(new Entry<>(key, value));
        numKeys++;
        if (numKeys > (LOAD_THRESHOLD * table.length))
            rehash();
        return null;
    }

    @Override
    public V remove(Object key) {
        // Set index to key.hashCode() % table.length.
        // if index is negative, add table.length.
        int index = key.hashCode() % table.length;
        if(index < 0){
            index += table.length;
        }

        // if table[index] is null
        // key is not in the table; return null.
        if(table[index] == null){
            return null;
        }
        for(int i = 0 ; i < table[index].size(); i++){
            //Search the list at table[index] to find the key.
            if(table[index].get(i).getKey() == key){
                V oldVal = table[index].get(i).getValue();

                // if the search is successful
                // Remove the entry with this key and decrement numKeys.
                table[index].remove(i);
                this.numKeys--;

                //if the list at table[index] is empty
                //Set table[index] to null.
                if(table[index].isEmpty()){
                    table[index] = null;
                }
                //Return the value associated with this key.
                return oldVal;
            }
        }
        //The key is not in the table; return null.
        return null;
    }

    @Override
    public int size() {
        return this.numKeys;
    }

    @Override
    public boolean isEmpty() {
        return (this.numKeys != 0);
    }

    private void rehash() {
        // Save a reference to oldTable.
        LinkedList<Entry<K, V>>[] oldTable = table;
        // Double capacity of this table.
        table = new LinkedList[2 * oldTable.length + 1];

        // Reinsert all items in oldTable into expanded table.
        this.numKeys = 0;
        for (LinkedList<Entry<K, V>> entries : oldTable) {
            for (Entry<K, V> entry : entries) {
                int index = entry.getKey().hashCode() % table.length;
                if (index < 0)
                    index += table.length;
                if (table[index] == null) {
                    // Create a new linked list at table[index].
                    table[index] = new LinkedList<>();
                }
                table[index].add(entry);
                this.numKeys++;
            }
        }
    }
}
```

