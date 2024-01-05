package datastructures.stack;

public class ArrayStack<E> implements Stack<E> {

    public static final int CAPACITY = 1000; // Default array capacity
    private E[] data; // Generic array used for storage
    private int t = -1; // Index of the top element in the stack

    public ArrayStack() {
        this(CAPACITY); // Constructs stack with default capacity
    }

    public ArrayStack(int capacity) {
        // Constructs stack with given capacity
        data = (E[]) new Object[capacity]; // Safe cast; compiler may give warning
    }

    public int size() {
        return (t + 1);
    }

    public boolean empty() {
        return (t == -1);
    }

    public void push(E e) throws IllegalStateException {
        if (size() == data.length) throw new IllegalStateException("Stack is full");
        data[++t] = e; // Increment t before storing the new item
    }

    public E top() {
        if (empty()) return null;
        return data[t];
    }

    public E pop() {
        if (empty()) return null;
        E answer = data[t];
        data[t] = null; // Dereference to help garbage collection
        t--;
        return answer;
    }
}
