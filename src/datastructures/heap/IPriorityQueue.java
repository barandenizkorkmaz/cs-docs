package datastructures.heap;

public interface IPriorityQueue<E> {
    void add(E e);

    void clear();

    boolean isEmpty();

    E peek();

    E remove();

    int size();
}
