# 1. Heaps

### Overview

- At each level of a heap, the value in a node is less than all values in its two subtrees.
  - Implies that minimum element is always the root (a "min-heap").
  - **Variation**: "max-heap" stores largest element at root, reverses ordering
- More formally, a heap is a complete binary tree with the following properties:
  - The value in the root is the smallest item in the tree.
  - Every subtree is a heap.



### Operations

#### Insertion

```bash
Insert the new item in the next position at the bottom of the heap.
while new item is not at the root and new item is smaller than its parent
	Swap the new item with its parent, moving the new item up the heap.
```

#### Removal

```bash
Remove the item in the root node by replacing it with the last item in the heap (LIH).
while item LIH has children, and item LIH is larger than either of its children
	Swap item LIH with its smaller child, moving LIH down the heap.
```



### Implementation

- Because a heap is a complete binary tree, we can implement it efficiently using an `array` (or
  ArrayList) instead of a linked data structure.
- Definitions:
  - Root index = 0
  - For a node at position p
    - left child = 2p + 1
    - right child = 2p +2
    - parent: (c - 1)/2




```java
public class Heap<E extends Comparable<E>> implements IPriorityQueue<E> {

    private E[] elements;
    private int size;
    private int capacity;
    public static final int INITIAL_CAPACITY = 10;

    // constructs a new empty priority queue
    public Heap() {
        this(INITIAL_CAPACITY);
    }

    public Heap(int capacity){
        this.elements = (E[]) new Object[capacity];
        this.size = 0;
        this.capacity = capacity;
    }

    // Adds the given value to this priority queue in order.
    public void add(E value) {
        // resize to enlarge the heap if necessary
        if (size == capacity) {
            reallocate();
        }

        elements[size] = value;  // add as rightmost leaf

        // "bubble up" as necessary to fix ordering
        int index = size;
        boolean found = false;
        while (!found && hasParent(index)) {
            int parent = parent(index);
            if (elements[index].compareTo(elements[parent]) < 0) {
                swap(elements, index, parent(index));
                index = parent(index);
            } else {
                found = true;  // found proper location; stop
            }
        }

        size++;
    }

    public E peek() {
        return elements[0];
    }

    public E remove() {   // precondition: queue is not empty
        E result = elements[0];      // last leaf -> root
        elements[0] = elements[size - 1];
        size--;
        int index = 0;    // "bubble down" to fix ordering
        boolean found = false;
        while (!found && hasLeftChild(index)) {
            int left = leftChild(index);
            int right = rightChild(index);
            int child = left;
            if (hasRightChild(index) &&
                    elements[right].compareTo(elements[left]) < 0) {
                child = right;
            }
            if (elements[index].compareTo(elements[child]) > 0) {
                swap(elements, index, child);
                index = child;
            } else {
                found = true;  // found proper location; stop
            }
        }
        return result;
    }

    @Override
    public void clear() {
        Arrays.fill(elements, null);
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size > 0;
    }

    @Override
    public int size() {
        return size;
    }

    // helpers for navigating indexes up/down the tree
    private int parent(int index)        { return (index - 1)/2; }
    private int leftChild(int index)     { return 2 * index + 1; }
    private int rightChild(int index)    { return 2 * index + 2; }
    private boolean hasParent(int index) { return index > 0; }
    private boolean hasLeftChild(int index) {
        return leftChild(index) < size;
    }
    private boolean hasRightChild(int index) {
        return rightChild(index) < size;
    }
    private void swap(E[] a, int index1, int index2) {
        E temp = a[index1];
        a[index1] = a[index2];
        a[index2] = temp;
    }

    private void reallocate(){
        this.capacity = 2 * this.capacity;
        elements = Arrays.copyOf(elements, capacity);
    }
}

```



### Performance

| Operations | Big-O Complexity         |
| ---------- | ------------------------ |
| Add        | O(logN) [height of tree] |
| Peek       | O(1)                     |
| Remove     | O(logN) [height of tree] |



# 2. Priority Queues

### Overview

- A priority queue is a data structure in which only the highest priority item is accessible.
  - In computer science, a heap is used as the basis of a very efficient algorithm for sorting arrays, called heapsort (we'll cover later). 
  - The heap is also used to implement a special kind of queue called a priority queue. However, the heap is not very useful as an abstract data type (ADT) on its own. Consequently, we will not create a Heap interface or code a class that implements it. 
  - Instead we will incorporate its algorithms when we implement a priority queue class and heapsort.



### Class `java.util.PriorityQueue<E>`

| **Constructor**                                   | **Description**                                              |
| ------------------------------------------------- | ------------------------------------------------------------ |
| `PriorityQueue()`                                 | Creates a PriorityQueue with the default initial capacity (11) that orders its elements according to their natural ordering. |
| `PriorityQueue(Comparator<? super E> comparator)` | Creates a PriorityQueue with the default initial capacity and whose elements are ordered according to the specified comparator. |

| **Modifier and Type**   | **Method**           | **Description**                                              |
| ----------------------- | -------------------- | ------------------------------------------------------------ |
| `boolean`               | `add(E e)`           | Inserts the specified element into this priority queue.      |
| `void`                  | `clear()`            | Removes all of the elements from this priority queue.        |
| `Comparator<? super E>` | `comparator()`       | Returns the comparator used to order the elements in this queue, or null if this queue is sorted according to the natural ordering of its elements. |
| `boolean`               | `contains(Object o)` | Returns true if this queue contains the specified element.   |
| `Iterator<E>`           | `iterator()`         | Returns an iterator over the elements in this queue.         |
| `boolean`               | `offer(E e)`         | Inserts the specified element into this priority queue.      |
| `E`                     | `peek()`             | Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty. |
| `E`                     | `poll()`             | Retrieves and removes the head of this queue, or returns null if this queue is empty. |
| `boolean`               | `remove(Object o)`   | Removes a single instance of the specified element from this queue, if it is present. |
| `int`                   | `size()`             | Returns the number of elements in this collection.           |

- The class `java.util.PriorityQueue` uses an array of type `Object[]` for heap storage.



### Class `PriorityQueue<E>`

- In our case, as an example, we use `ArrayList` for implementing a PriorityQueue class.

| **Data Field/Attribute**   | **Description**                                              |
| -------------------------- | ------------------------------------------------------------ |
| `ArrayList<E> theData`     | An ArrayList to hold the data                                |
| `Comparator<E> comparator` | An optional object that implements the `Comparator<E>` interface by providing a `compare` method |

| **Constructor**                       | **Description**                                              |
| ------------------------------------- | ------------------------------------------------------------ |
| `KWPriorityQueue()`                   | Constructs a heap-based priority queue that uses the elements' natural ordering |
| `KWPriorityQueue(Comparator<E> comp)` | Constructs a heap-based priority queue that uses the `compare` method of `Comparator comp` to determine the ordering of the elements |

| **Private Method**                     | **Description**                                              |
| -------------------------------------- | ------------------------------------------------------------ |
| `private int compare(E left, E right)` | Compares two objects and returns a negative number if object `left` is less than object `right`, zero if they are equal, and a positive number if object `left` is greater than object `right` |
| `private void swap(int i, int j)`      | Exchanges the object references in `theData` at indexes `i` and `j` |

```java
package datastructures.heap;

import java.util.*;

/** The PriorityQueue implements the Queue interface
 by building a heap in an ArrayList. The heap is structured
 so that the “smallest” item is at the top.
 */

public class PriorityQueue<E> extends AbstractQueue<E> implements Queue<E> {
// Data Fields
    /**
     * The ArrayList to hold the data.
     */
    private ArrayList<E> data;
    /**
     * An optional reference to a Comparator object.
     */
    Comparator<E> comparator = null;

    // Methods
// Constructor
    public PriorityQueue() {
        data = new ArrayList<>();
    }

    /** Creates a heap‐based priority queue with the specified initial
     capacity that orders its elements according to the specified
     comparator.
     @param capacity The initial capacity for this priority queue
     @param comp The comparator used to order this priority queue
     @throws IllegalArgumentException if capacity is less than 1
     */
    public PriorityQueue(int capacity, Comparator<E> comp) {
        if (capacity < 1)
            throw new IllegalArgumentException();
        data = new ArrayList<>();
        comparator = comp;
    }

    /**
     * Insert an item into the priority queue.
     * pre: The ArrayList theData is in heap order.
     * post: The item is in the priority queue and
     * theData is in heap order.
     *
     * @param item The item to be inserted
     * @throws NullPointerException if the item to be inserted is null.
     */
    @Override
    public boolean offer(E item) {
        // Add the item to the heap.
        data.add(item);
        // child is newly inserted item.
        int child = data.size()-1;
        int parent = (child - 1) /2; // Find child's parent.
        // Reheap
        while (parent >= 0 && compare(data.get(parent),
                data.get(child)) > 0) {
            swap(parent, child);
            child = parent;
            parent = (child - 1) /2;
        }
        return true;
    }

    /**
     * Remove an item from the priority queue
     * pre: The ArrayList theData is in heap order.
     * post: Removed smallest item, theData is in heap order.
     *
     * @return The item with the smallest priority value or null if empty.
     */
    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        // Save the top of the heap.
        E result = data.get(0);
        // If only one item then remove it.
        if (data.size() == 1) {
            data.remove(0);
            return result;
        }
        /* Remove the last item from the ArrayList and place it into the first position. */
        data.set(0, data.remove(data.size() - 1));
        // The parent starts at the top.
        int parent = 0;
        while (true) {
            int leftChild = 2 * parent + 1;
            if (leftChild >= data.size()) {
                break; // Out of heap.
            }
            int rightChild = leftChild + 1;
            int minChild = leftChild; // Assume leftChild is smaller.
            // See whether rightChild is smaller.
            if (rightChild < data.size()
                    && compare(data.get(leftChild),
                    data.get(rightChild)) > 0) {
                minChild = rightChild;
            }
            // assert: minChild is the index of the smaller child.
            // Move smaller child up heap if necessary.
            if (compare(data.get(parent),
                    data.get(minChild)) > 0) {
                swap(parent, minChild);
                parent = minChild;
            } else { // Heap property is restored.
                break;
            }
        }
        return result;
    }

    @Override
    public E peek() {
        return data.get(0);
    }

    /** Compare two items using either a Comparator object's compare method
     or their natural ordering using method compareTo.
     @pre: If comparator is null, left and right implement Comparable<E>.
     @param left One item
     @param right The other item
     @return Negative int if left less than right,
     0 if left equals right,
     positive int if left > right
     @throws ClassCastException if items are not Comparable
     */
    @SuppressWarnings("unchecked")
    private int compare(E left, E right) {
        if (comparator != null) {
            // A Comparator is defined.
            return comparator.compare(left, right);
        } else {
            // Use left's compareTo method.
            return ((Comparable<E>) left).compareTo(right);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public int size() {
        return data.size();
    }

    private void swap(int index1, int index2) {
        E temp = data.get(index1);
        data.set(index1, data.get(index2));
        data.set(index2, temp);
    }
}
```



# Interface `Comparable<E>` and `Comparator<E>` 

- How do we compare elements in a PriorityQueue? 



### Interface `Comparable<E>`

- In many cases, we will insert objects that implement `Comparable<E> `and use their natural ordering as specified by method `compareTo`.
- `Comparable<E>` is used to define the natural ordering of an object type.



### Interface `Comparator<E>`

- However, we may need to insert objects that do not implement `Comparable<E>`, or we may
  want to specify a different ordering from that defined by the object’s `compareTo` method. 
  - For example, files to be printed may be ordered by their name using the compareTo method, but
    we may want to assign priority based on their length. 
- The Java API contains the `Comparator<E>` interface, which allows us to specify alternative ways to compare objects. An implementer of the `Comparator<E>` interface must define a `compare` method that is similar to `compareTo` except that it has two parameters.

```java
public interface Comparator<T> {
    public int compare(T first, T second);
}
```

**Example**

```java
public class RectangleAreaComparator implements Comparator<Rectangle> {
    // compare in ascending order by area (WxH)
    public int compare(Rectangle r1, Rectangle r2) {
        return r1.getArea() - r2.getArea();
    }
}
```



#### Using Comparators

1. TreeSet, TreeMap , PriorityQueue can use Comparator:

   ```java
   Comparator<Rectangle> comp = new RectangleAreaComparator();
   Set<Rectangle> set = new TreeSet<Rectangle>(comp);
   Queue<Rectangle> pq = new PriorityQueue<Rectangle>(10,comp);
   ```

2. Searching and sorting methods can accept Comparators.

   ```java
   Arrays.binarySearch(array, value, comparator)
   Arrays.sort(array, comparator)
   Collections.binarySearch(list, comparator)
   Collections.max(collection, comparator)
   Collections.min(collection, comparator)
   Collections.sort(list, comparator)
   ```

3. Methods are provided to reverse a Comparator's ordering:

   ```java
   public static Comparator Collections.reverseOrder()
   public static Comparator Collections.reverseOrder(comparator)
   ```



**Example:** Using Comparator for ordering a Priority Queue

```java
public class LengthComparator implements Comparator<String> {
    public int compare(String s1, String s2) {
        if (s1.length() != s2.length()) {
            // if lengths are unequal, compare by length
            return s1.length() - s2.length();
        } else {
            // break ties by ABC order
            return s1.compareTo(s2);
        }
    }
}

Queue<String> pq = new PriorityQueue<String>(100, new LengthComparator());
```

- Observe that, in the example, we are still making use of the fact that the class `String` is implementing `Comparable<E>` interface to define its own natural ordering by implementing `compareTo` method.