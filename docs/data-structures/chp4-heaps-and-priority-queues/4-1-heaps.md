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



