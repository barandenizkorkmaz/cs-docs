# Heaps

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
```



### Performance

| Operations | Big-O Complexity         |
| ---------- | ------------------------ |
| Add        | O(logN) [height of tree] |
| Peek       | O(1)                     |
| Remove     | O(logN) [height of tree] |



# Comparator vs Comparable???