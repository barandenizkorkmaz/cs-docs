# 1. Stack

### Overview

- A stack is a data structure with the property that only the top element of the stack is accessible. In a stack, the top element is the data value that was most recently stored in the stack.
- Sometimes this storage policy is known as last-in, first-out, or LIFO.



### Stack ADT

| Modifier and Type | Method       | Description                                                  |
| ----------------- | ------------ | ------------------------------------------------------------ |
| boolean           | empty()      | Tests if this stack is empty.                                |
| E                 | peek()       | Looks at the object at the top of this stack without removing it from the stack. |
| E                 | pop()        | Removes the object at the top of this stack and returns that object as the value of this function. |
| E                 | push(E item) | Pushes an item onto the top of this stack.                   |



### Class `java.util.Stack<E>`

- The `java.util.Stack` class is part of the original Java API but is not recommended for new applications. Instead, the Java designers recommend that we use the `java.util.Deque` interface and the `java.util.ArrayDeque` class to provide the methods listed above. The `Deque` interface specifies the methods in our interface `StackInt` (see Table) and also those needed for a queue. We will discuss the `Deque` interface and class `ArrayDeque` later.



### Our Interface `Stack<E>`

```java
/**
 * A collection of objects that are inserted and removed according to the last-in
 * first-out principle. Although similar in purpose, this interface differs from
 * java.util.Stack.
 */
public interface Stack<E> {

    /**
     * Returns the number of elements in the stack.
     * 
     * @return number of elements in the stack
     */
    int size();

    /**
     * Tests whether the stack is empty.
     * 
     * @return true if the stack is empty, false otherwise
     */
    boolean empty();

    /**
     * Inserts an element at the top of the stack.
     * 
     * @param e the element to be inserted
     */
    void push(E e);

    /**
     * Returns, but does not remove, the element at the top of the stack.
     * 
     * @return top element in the stack (or null if empty)
     */
    E top();

    /**
     * Removes and returns the top element from the stack.
     * 
     * @return element removed (or null if empty)
     */
    E pop();
}
```



### Performance

| Operations   | Big O (Worst-case Complexity) |
| ------------ | ----------------------------- |
| empty()      | O(1)                          |
| peek()       | O(1)                          |
| pop()        | O(1)                          |
| push(E item) | O(1)                          |



## Implementation

- We are going to cover two ways of implementing a Stack:
    - `Array (or ArrayList can also be adapted)`
    - `LinkedList`



### Array-Based Stack Implementation

- The following class implements generic `Stack<E>` interface.

#### Implementation

```java
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
```

**Drawbacks of Array-Based Implementation	**

- Fixed-capacity array
    - If the application needs much less space than the reserved capacity, memory is **wasted**.
        - Performance of a stack realized by an array. The space usage is $O(N)$, where N is the size of the array, determined at the time the stack is instantiated, and independent from the number n $\leq$ N of elements that are actually in the stack.
    - When the stack has reached the capacity, it will refuse storing a new element throwing **IllegalStateException**. This problem can be fixed by using `ArrayList` as adapter instead of implementation from scratch using `Array`.



### Singly-Linked List-Based Stack

#### Implementation

```java
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
```



### Extra

> #### The Adapter Pattern
>
> The **adapter** design pattern applies to any context where we effectively want to modify an existing class so that its methods match those of a related, but different, class or interface. One general way to apply the adapter pattern is to define a new class in such a way that it contains an instance of the existing class as a hidden field, and then to implement each method of the new class using methods of this hidden instance variable. By applying the adapter pattern in this way, we have created a new class that performs some of the same functions as an existing class, but repackaged in a more convenient way.



### Comparisons of Stack Implementations

- As we discussed before, in array-based implementation If the application needs much less space than the reserved capacity, memory is **wasted**.
- Whereas a linked list based implementation has the advantage of using exactly as much storage as needed for the stack. However, also note that since a linked-list node stores 2 data field references for the previous and next node references and 1 data field for stored data element, a linked-list based implementation of the full size is 3 times more expensive in terms of memory than a full array-based stack.



