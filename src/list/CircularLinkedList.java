package list;

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
