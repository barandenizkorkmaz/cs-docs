package list;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;

public class ArrayList<E> extends AbstractList<E> implements List<E> {

    // Data Fields
    /** The default initial capacity */
    private static final int INITIAL_CAPACITY = 10;
    /** The underlying data array */
    private E[] data;
    /** The current size */
    private int size = 0;
    /** The current capacity */
    private int capacity = 0;

    @SuppressWarnings("unchecked")
    public ArrayList(){
        this.capacity = INITIAL_CAPACITY;
        data = (E[]) new Object[capacity];
    }

    public boolean add(E anEntry) {
        if (size == capacity) {
            reallocate();
        }
        data[size] = anEntry;
        size++;
        return true;
    }

    public void add(int index, E anEntry) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        if (size == capacity) {
            reallocate();
        }
        // Shift data in elements from index to size ‐ 1
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        // Insert the new item.
        data[index] = anEntry;
        size++;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return data[index];
    }
    public E set(int index, E newValue) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        E oldValue = data[index];
        data[index] = newValue;
        return oldValue;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        E returnValue = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        return returnValue;
    }

    private void reallocate() {
        capacity = 2 * capacity;
        data = Arrays.copyOf(data, capacity);
    }

    public int size() {
        return this.size;
    }
}
