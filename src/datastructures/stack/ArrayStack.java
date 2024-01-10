package datastructures.stack;

import java.util.Arrays;

public class ArrayStack<E> implements Stack<E> {

    public static final int INITIAL_CAPACITY = 10; // Default array capacity
    private E[] data; // Generic array used for storage
    private int top = -1; // Index of the top element in the stack
    private int capacity;

    public ArrayStack() {
        this(INITIAL_CAPACITY); // Constructs stack with default capacity
    }

    public ArrayStack(int capacity) {
        // Constructs stack with given capacity
        this.capacity = capacity;
        data = (E[]) new Object[capacity]; // Safe cast; compiler may give warning
    }

    public int size() {
        return (top + 1);
    }

    public boolean empty() {
        return (top == -1);
    }

    public void push(E e) throws IllegalStateException {
        if (size() == data.length) {
            reallocate();
        }
        data[++top] = e; // Increment t before storing the new item
    }

    public E top() {
        if (empty()) return null;
        return data[top];
    }

    public E pop() {
        if (empty()) return null;
        E answer = data[top];
        data[top] = null; // Dereference to help garbage collection
        top--;
        return answer;
    }

    private void reallocate(){
        capacity = 2 * capacity;
        data = Arrays.copyOf(data, capacity);
    }
}
