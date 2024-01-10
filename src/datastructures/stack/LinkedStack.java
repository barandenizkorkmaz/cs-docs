package datastructures.stack;

import java.util.LinkedList;

public class LinkedStack<E> implements Stack<E> {

    private LinkedList<E> list = new LinkedList<>(); // An empty list

    public LinkedStack() { }
    // New stack relies on the initially empty list

    public int size() {
        return list.size();
    }

    public boolean empty() {
        return list.isEmpty();
    }

    public void push(E element) {
        list.addFirst(element);
    }

    public E top() {
        return list.getFirst();
    }

    public E pop() {
        return list.removeFirst();
    }
}
