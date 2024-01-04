package list;

import java.util.AbstractSequentialList;
import java.util.List;
import java.util.ListIterator;

/** Class to represent a linked list with a link from each node to the next
 node. SingleLinkedList does not implement the List interface.
 */

public class SingleLinkedList<E> extends AbstractSequentialList<E> implements List<E> {

    /** A Node is the building block for a single‐linked list. */
    private static class Node<E> {
        // Data Fields
        /** The reference to the data. */
        private E data;
        /** The reference to the next node. */
        private Node<E> next;

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

    /**
     * Reference to list head.
     */
    private Node<E> head = null;
    /**
     * The number of items in the list
     */
    private int size = 0;

    /** Insert the specified item at index
     @param index The position where item is to be inserted
     @param item The item to be inserted
     @throws IndexOutOfBoundsException if index is out of range
     */
    public void add(int index, E item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        if (index == 0) {
            addFirst(item);
        } else {
            Node<E> node = getNode(index-1);
            addAfter(node, item);
        }
    }

    /** Append item to the end of the list
     @param item The item to be appended
     @return true (as specified by the Collection interface)
     */
    public boolean add(E item) {
        add(size, item);
        return true;
    }

    /** Add an item to the front of the list.
     @param item The item to be added
     */
    public void addFirst(E item) {
        head = new Node<>(item, head);
        size++;
    }

    /** Add a node after a given node
     @param node The node preceding the new item
     @param item The item to insert
     */
    private void addAfter(Node<E> node, E item) {
        node.next = new Node<>(item, node.next);
        size++;
    }

    /**
     * Returns the list iterator object.
     * @param i the index of the iterator.
     * @return the list iterator object.
     */
    @Override
    public ListIterator<E> listIterator(int i) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the element at the specified position in this list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }

        E removedData;
        if (index == 0) {
            removedData = removeFirst();
        } else {
            Node<E> prevNode = getNode(index - 1);
            removedData = removeAfter(prevNode);
        }
        return removedData;
    }

    /** Remove the node after a given node
     @param node The node before the one to be removed
     @return The data from the removed node, or null
     if there is no node to remove
     */
    private E removeAfter(Node<E> node) {
        Node<E> temp = node.next;
        if (temp != null) {
            node.next = temp.next;
            size--;
            return temp.data;
        } else {
            return null;
        }
    }

    /** Remove the first node from the list
     @return The removed node's data or null if the list is empty
     */
    private E removeFirst() {
        Node<E> temp = head;
        if (head != null) {
            head = head.next;
        }
        // Return data at old head or null if list is empty
        if (temp != null) {
            size--;
            return temp.data;
        } else {
            return null;
        }
    }

    /** Get the data at index
     @param index The position of the data to return
     @return The data at index
     @throws IndexOutOfBoundsException if index is out of range
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        Node<E> node = getNode(index);
        return node.data;
    }

    /** Find the node at a specified position
     @param index The position of the node sought
     @return The node at index or null if it does not exist
     */
    private Node<E> getNode(int index) {
        Node<E> node = head;
        for (int i = 0; i < index && node != null; i++) {
            node = node.next;
        }
        return node;
    }

    /** Store a reference to anEntry in the element at position index.
     @param index The position of the item to change
     @param newValue The new data
     @return The data previously at index
     @throws IndexOutOfBoundsException if index is out of range
     */
    public E set(int index, E newValue) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        Node<E> node = getNode(index);
        E result = node.data;
        node.data = newValue;
        return result;
    }

    /**
     * Return the current number of elements in the list.
     * @return The size of the list
     */
    public int size(){
        return size;
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element. This method allows searching for
     * an object of type Object.
     *
     * @param target the object to search for in the list
     * @return the index of the first occurrence of the object in the list, or -1 if not found
     */
    public int indexOf(Object target) {
        Node<E> current = head;
        int index = 0;
        while (current != null) {
            if (current.data.equals(target)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1; // Return -1 if the element is not found
    }

}