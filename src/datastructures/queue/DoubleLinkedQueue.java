package datastructures.queue;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class DoubleLinkedQueue<E> extends AbstractQueue<E> implements Queue<E> {

    private LinkedList<E> list; // an empty list

    public DoubleLinkedQueue( ) {
        list = new LinkedList<>();
    }

    @Override
    public boolean add(E e) {
        return list.add(e); // Appends e to the end of the list
    }

    @Override
    public boolean offer(E e) {
        return list.add(e); // Appends e to the end of the list
    }

    @Override
    public E remove() {
        return list.remove(); // Removes the element in the beginning of the list
    }

    @Override
    public E poll() {
        return list.remove();
    }

    @Override
    public E element() {
        return list.getFirst();
    }

    @Override
    public E peek() {
        return list.getFirst();
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public int size() {
        return list.size();
    }

}
