package datastructures.queue;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** Implements the Queue interface using a circular array. */
public class ArrayQueue<E> extends AbstractQueue<E> implements Queue<E> {
    // Data Fields
    /** Index of the front of the queue. */
    private int front;
    /** Index of the rear of the queue. */
    private int rear;
    /** Current size of the queue. */
    private int size;
    /** Current capacity of the queue. */
    private int capacity;
    /** Default capacity of the queue. */
    private static final int DEFAULT_CAPACITY = 10;
    /** Array to hold the data. */
    private E[] data;

    // Constructors
    /** Construct a queue with the default initial capacity. */
    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    /** Construct a queue with the specified initial capacity.
    @param initCapacity The initial capacity
    */
    public ArrayQueue(int initCapacity) {
        capacity = initCapacity;
        data = (E[]) new Object[capacity];
        front = 0;
        rear = capacity-1;
        size = 0;
    }

    // Public Methods
    /** Inserts an item at the rear of the queue.
     @post item is added to the rear of the queue.
     @param item The element to add
     @return true (always successful)
     */
    @Override
    public boolean offer(E item) {
        if (size == capacity) {
            reallocate();
        }
        size++;
        rear = (rear + 1) % capacity; data[rear] = item;
        return true;
    }

    /** Returns the item at the front of the queue without removing it.
     @return The item at the front of the queue if successful; return null if
     the queue is empty
     */
    @Override
    public E peek() {
        if (size == 0)
            return null;
        else
            return data[front];
    }

    /** Removes the entry at the front of the queue and returns it if the queue is
     not empty.
     @post front references item that was second in the queue.
     @return The item removed if successful or null if not
     */
    @Override
    public E poll() {
        if (size == 0) {
            return null;
        }
        E result = data[front];
        front = (front + 1) % capacity;
        size--;
        return result;
    }

    @Override
    public void enqueue(E e) {
        offer(e);
    }

    @Override
    public E first() {
        return peek();
    }

    @Override
    public E dequeue() {
        return poll();
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayQueueIterator();
    }

    @Override
    public int size() {
        return size;
    }

    // Private Methods
    /** Double the capacity and reallocate the data.
     @pre The array is filled to capacity.
     @post The capacity is doubled and the first half of the expanded array is
     filled with data.
     */
    @SuppressWarnings("unchecked")
    private void reallocate() {
        int newCapacity = 2 * capacity;
        E[] newData = (E[]) new Object[newCapacity];
        int j = front;
        for (int i = 0; i < size; i++) {
            newData[i] = data[j];
            j = (j + 1) % capacity;
        }
        front = 0;
        rear = size-1;
        capacity = newCapacity;
        data = newData;
    }

    /** Inner class to implement the Iterator<E> interface. */
    private class ArrayQueueIterator implements Iterator<E> {
        // Data Fields
        // Index of next element
        private int index;
        // Count of elements accessed so far
        private int count = 0;

        // Methods

        // Constructor
        /** Initializes the Iter object to reference the first queue element. */
        public ArrayQueueIterator() {
            index = front;
        }
        /** Returns true if there are more elements in the queue to access. */
        @Override
        public boolean hasNext() {
            return count < size;
        }

        /** Returns the next element in the queue.
         @pre index references the next element to access.
         @post index and count are incremented.
         @return The element with subscript index
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E returnValue = data[index];
            index = (index + 1) % capacity;
            count++;
            return returnValue;
        }

        /** Remove the item accessed by the Iter object – not implemented. */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}