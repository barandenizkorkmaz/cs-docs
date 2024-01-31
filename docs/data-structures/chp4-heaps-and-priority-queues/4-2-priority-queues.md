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