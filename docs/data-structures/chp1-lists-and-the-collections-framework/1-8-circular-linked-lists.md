# 8. Circular Linked List

### Overview

- **Circularly Linked Lists:** Linked lists are traditionally viewed as storing a sequence of items in a linear order, from first to last. However, there are many applications in which data can be more naturally viewed as having a cyclic order, with well-defined neighboring relationships, but no fixed beginning or end.
- Example Application: Round-Robin Scheduling
    - A process is given a short turn to execute, known as a time slice, but it is interrupted when the slice ends, even if its job is not yet complete. Each active process is given its own time slice, taking turns in a cyclic order.



### Class `CircularlyLinkedList<E>`

#### Data Fields

| Data Field           | Attribute                                  |
| -------------------- | ------------------------------------------ |
| private Node<E> tail | A reference to the last item in the list   |
| private int size     | A count of the number of items in the list |

#### Method Summary

| Modifier and Type | Method        | Description                                                  |
| ----------------- | ------------- | ------------------------------------------------------------ |
| void              | addFirst(E e) | Inserts the specified element at the beginning of this list. |
| void              | addLast(E e)  | Appends the specified element to the end of this list.       |
| E                 | getFirst()    | Returns the first element in this list.                      |
| E                 | getLast()     | Returns the last element in this list.                       |
| void              | rotate()      | Rotates the first element to the back of the list.           |
| E                 | removeFirst() | Removes and returns the first element from this list.        |

### Implementation

```java
public class CircularLinkedList<E> {
    // Nested node class identical to that of the SinglyLinkedList class
    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }

    private Node<E> tail = null; // We store tail (but not head)
    private int size = 0; // Number of nodes in the list

    public CircularLinkedList() { } // Constructs an initially empty list

    // Access methods
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E getFirst() {
        // Returns (but does not remove) the first element
        if (isEmpty()) return null;
        return tail.getNext().getElement(); // The head is *after* the tail
    }

    public E getLast() {
        // Returns (but does not remove) the last element
        if (isEmpty()) return null;
        return tail.getElement();
    }

    // Update methods
    public void rotate() {
        // Rotate the first element to the back of the list
        if (tail != null) // If empty, do nothing
            tail = tail.getNext(); // The old head becomes the new tail
    }

    public void addFirst(E e) {
        // Adds element e to the front of the list
        if (size == 0) {
            tail = new Node<>(e, null);
            tail.setNext(tail); // Link to itself circularly
        } else {
            Node<E> newest = new Node<>(e, tail.getNext());
            tail.setNext(newest);
        }
        size++;
    }

    public void addLast(E e) {
        // Adds element e to the end of the list
        addFirst(e); // Insert new element at front of list
        tail = tail.getNext(); // Now new element becomes the tail
    }

    public E removeFirst() {
        // Removes and returns the first element
        if (isEmpty()) return null; // Nothing to remove
        Node<E> head = tail.getNext();
        if (head == tail)
            tail = null; // Must be the only node left
        else
            tail.setNext(head.getNext()); // Removes ”head” from the list
        size--;
        return head.getElement();
    }
}

```



> TODO: The implementations of some methods are missing and should be fixed in the future.



### Performance

| Operations/Complexity | Big O |
| --------------------- | ----- |
| addFirst(E e)         | O(1)  |
| addLast(E e)          | O(1)  |
| getFirst()            | O(1)  |
| getLast()             | O(1)  |
| rotate()              | O(1)  |
| removeFirst()         | O(1)  |

