# 7. Double-Linked List (Linked List)

### Overview

- A double‐linked list object would consist of a separate object with data fields `head` (a reference to the first list Node), `tail` (a reference to the last list Node), and size (the number of Nodes). Because both ends of the list are directly accessible, now insertion at either end is O(1); insertion else- where is still O(n).



### Implementation

- We can implement most of the `MyLinkedList` methods by delegation to the class `MyListIterator`, which will implement the `ListIterator` interface

#### Data Fields for Class `MyLinkedList<E>` (Double-Linked List)

| Data Field           | Attribute                                  |
| -------------------- | ------------------------------------------ |
| private Node<E> head | A reference to the first item in the list  |
| private Node<E> tail | A reference to the last item in the list   |
| private int size     | A count of the number of items in the list |



```java
package datastructures.list;

import java.util.*;
/** Class KWLinkedList implements a double‐linked list and
 a ListIterator. */
public class LinkedList<E> extends AbstractSequentialList<E> implements List<E>{

    /** A Node is the building block for a single‐linked list. */
    private static class Node<E> {
        // Data Fields
        /** The reference to the data. */
        private E data;
        /** The reference to the next node. */
        private Node<E> next;
        /** The reference to the previous node. */
        private Node<E> prev;

        // Constructors
        /** Creates a new node with a null next field.
         @param dataItem The data stored
         */
        private Node(E dataItem) {
            data = dataItem;
            next = null;
        }

        /** Creates a new node that references another node.
         @param dataItem The data stored
         @param nodeRef The node referenced by new node
         */
        private Node(E dataItem, Node<E> nodeRef) {
            data = dataItem;
            next = nodeRef;
        }
    }

    /** Inner class to implement the ListIterator interface. */
    private class MyListIterator implements ListIterator<E> {
        /** A reference to the next item. */
        private Node<E> nextItem;
        /** A reference to the last item returned. */
        private Node<E> lastItemReturned;
        /** The index of the current item. */
        private int index = 0;

        /** Construct a KWListIter that will reference the ith item.
         @param i The index of the item to be referenced
         */
        public MyListIterator(int i) {
            // Validate i parameter.
            if (i < 0 || i > size) {
                throw new IndexOutOfBoundsException("Invalid index " + i);
            }
            lastItemReturned = null; // No item returned yet.
            // Special case of last item.
            if (i == size) {
                index = size;
                nextItem = null;
            } else { // Start at the beginning
                nextItem = head;
                for (index = 0; index < i; index++) {
                    nextItem = nextItem.next;
                }
            }
        }

        /** Indicate whether movement forward is defined.
         @return true if call to next will not throw an exception
         */
        public boolean hasNext() {
            return nextItem != null;
        }

        /** Move the iterator forward and return the next item.
         @return The next item in the list
         @throws NoSuchElementException if there is no such object
         */
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastItemReturned = nextItem;
            nextItem = nextItem.next;
            index++;
            return lastItemReturned.data;
        }

        /** Indicate whether movement backward is defined.
         @return true if call to previous will not throw an exception
         */
        public boolean hasPrevious() {
            return (nextItem == null && size != 0)
                    || nextItem.prev != null;
        }

        /** Move the iterator backward and return the previous item.
         @return The previous item in the list
         @throws NoSuchElementException if there is no such object
         */
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            if (nextItem == null) { // Iterator is past the last element
                nextItem = tail;
            } else {
                nextItem = nextItem.prev;
            }
            lastItemReturned = nextItem;
            index--;
            return lastItemReturned.data;
        }

        /**
         * Returns the index of the element that would be returned by a subsequent call to next().
         *
         * @return the index of the element that would be returned by a subsequent call to next(),
         *         or the size of the list if there is no next element
         */
        @Override
        public int nextIndex() {
            return (hasNext()) ? index : size;
        }

        /**
         * Returns the index of the element that would be returned by a subsequent call to previous().
         *
         * @return the index of the element that would be returned by a subsequent call to previous(),
         *         or -1 if there is no previous element
         */
        @Override
        public int previousIndex() {
            return (hasPrevious()) ? index - 1 : -1;
        }

        /**
         * Removes from the list the last element that was returned by next() or previous().
         *
         * This method has not been verified!
         *
         * @throws IllegalStateException if no element has been previously returned by next() or previous()
         */
        @Override
        public void remove() {
            if (lastItemReturned == null) {
                throw new IllegalStateException("No element to remove");
            }
            Node<E> lastNext = lastItemReturned.next;
            Node<E> lastPrev = lastItemReturned.prev;

            if (lastPrev == null) { // Removing first element
                head = lastNext;
            } else {
                lastPrev.next = lastNext;
                lastItemReturned.prev = null;
            }

            if (lastNext == null) { // Removing last element
                tail = lastPrev;
            } else {
                lastNext.prev = lastPrev;
                lastItemReturned.next = null;
            }

            if (nextItem == lastItemReturned) {
                nextItem = lastNext;
            } else {
                index--;
            }

            lastItemReturned.data = null;
            lastItemReturned = null;
            size--;
        }

        /**
         * Replaces the last element returned by next() or previous() with the specified element.
         *
         * @param e the element with which to replace the last returned element
         * @throws IllegalStateException if no element has been previously returned by next() or previous()
         */
        @Override
        public void set(E e) {
            if (lastItemReturned == null) {
                throw new IllegalStateException("No element to set");
            }
            lastItemReturned.data = e;
        }

        /** Add a new item between the item that will be returned
         by next and the item that will be returned by previous.
         If previous is called after add, the element added is
         returned.
         @param obj The item to be inserted
         */
        public void add(E obj) {
            if (head == null) { // Add to an empty list.
                head = new Node<>(obj);
                tail = head;
            } else if (nextItem == head) { // Insert at head
                // Create a new node.
                Node<E> newNode = new Node<>(obj);
                // Link it to the nextItem.
                newNode.next = nextItem;
                // Link nextItem to the new node.
                nextItem.prev = newNode;
                // The new node is now the head.
                head = newNode;
            }
            else if(nextItem == null){ // Insert at tail
                // Create a new node.
                Node<E> newNode = new Node<>(obj);
                // Link the tail to the new node.
                tail.next = newNode;
                // Link the new node to the tail.
                newNode.prev = tail;
                // The new node is the new tail.
                tail = newNode;
            }
            else { // Insert into the middle.
                // Create a new node.
                Node<E> newNode = new Node<>(obj);
                // Link it to nextItem.prev.
                newNode.prev = nextItem.prev;
                nextItem.prev.next = newNode;
                // Link it to the nextItem.
                newNode.next = nextItem;
                nextItem.prev = newNode;
            }
            // Increase size and index and set lastItemReturned.
            size++;
            index++;
            lastItemReturned = null;
        } // End of method add.


    }
    // Data Fields
    /** A reference to the head of the list. */
    private Node<E> head = null;
    /** A reference to the end of the list. */
    private Node<E> tail = null;
    /** The size of the list. */
    private int size = 0;

    /** Add an item at position index.
     @param index The position at which the object is to be
     inserted
     @param obj The object to be inserted
     @throws IndexOutOfBoundsException if the index is out
     of range (i < 0 || i > size())
     */
    public void add(int index, E obj) {
        listIterator(index).add(obj);
    }

    @Override
    public ListIterator<E> listIterator(int i) {
        return new MyListIterator(i);
    }

    /** Get the element at position index.
     @param index Position of item to be retrieved
     @return The item at index
     */
    public E get(int index) {
        return listIterator(index).next();
    }

    /**
     * Return the number of elements in this list.
     * @return The number of elements in this list.
     */
    @Override
    public int size() {
        return size;
    }
}
```



### Extra

> #### Inner Classes: Static and Nonstatic
>
> There are two inner classes in class LinkedList\<E>: class Node<E> and class MyListIterator. We declare Node\<E> to be static because there is no need for its methods to access the data fields of its parent class (LinkedList\<E>). We can’t declare MyListIterator to be static because its methods access and modify the data fields of the LinkedList object that creates the MyListIterator object. An inner class that is not static contains an implicit reference to its parent object, just as it contains an implicit reference to itself. Because MyListIterator is not static and can reference data fields of its parent class LinkedList\<E>, the type parameter <E> is considered to be previously defined; therefore, it cannot appear as part of the class name.

> #### PITFALL
>
> **Defining MyListIterator as a Generic Inner Class**
> If you define class MyListIterator as
> 	private class MyListIterator<E>...
> you will get an incompatible types syntax error when you attempt to reference data field head or tail (type Node\<E>) inside class MyListIterator.
