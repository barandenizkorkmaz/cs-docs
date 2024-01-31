# 6. The `Iterator`, `ListIterator`, and `Iterable` Interfaces

## The `Iterator` Interface

### Overview

- The List interface declares the method iterator, which returns an Iterator object that will iterate over the elements of that list. 
- The requirement for the iterator method is actually in the Collection interface, which is the superinterface for the List interface. The `Collection` interface extends the `Iterable` interface, so all classes that implement the `List` interface (a subinterface of `Collection`) must provide an `Iterator` method.
- An Iterator does not refer to or point to a particular object at any given time. Rather, you should think of an Iterator as pointing between objects within a list.
- Think of an iterator as a moving place marker that keeps track of the current position in a particular linked list. The Iterator object for a list starts at the first element in the list. The programmer can use the Iterator object’s `next` method to retrieve the next element. Each time it does a retrieval, the Iterator object advances to the next list element, where it waits until it is needed again. We can also ask the Iterator object to determine whether the list has more elements left to process (method `hasNext`). Iterator objects throw a `NoSuchElementException` if they are asked to retrieve the next element after all elements have been processed.
- You can use the Iterator remove method to remove elements from a list as you access them. You can remove only the element that was most recently accessed by next. Each call to remove must be preceded by a call to next to retrieve the next element.



### Interface `java.util.Iterator<E>`

| Modifier and Type | Method    | Description                                                  |
| ----------------- | --------- | ------------------------------------------------------------ |
| boolean           | hasNext() | Returns true if the iteration has more elements.             |
| E                 | next()    | Returns the next element in the iteration.                   |
| default void      | remove()  | Removes from the underlying collection the last element returned by this iterator (optional operation). |

### Extra

> #### Efficient Access to List Elements by Iterator
>
> We can use the following loop to access the list elements in sequence, starting with the one at index 0.
> 	// Access each list element.
> 	for (int index = 0; index < aList.size(); index++) {
> 		E nextElement = aList.get(index);
> 		// Do something with the element at position index (nextElement)
> 		. . .
> 	}
> The loop is executed aList.size() times; thus it is O($n$). During each iteration, we call the method get to retrieve the element at position index. If we assume that the method get begins at the first list node (head), each call to method get must advance a local reference (nodeRef) to the node at position index using a loop such as:
> 	// Advance nodeRef to the element at position index.
> 	Node<E> nodeRef = head;
> 	for (int j = 0; j < index; j++) {
> 		nodeRef = nodeRef.next;
> 	}
>
> This loop (in method get) executes index times, so it is also O($n$). Therefore, the performance of the nested loops used to process each element in a LinkedList is O($n^2$) and is very inefficient. We would like to have an alternative way to access the elements in a linked list sequentially.

> #### Removal Using `Iterator.remove` versus `List.remove`
>
> You could also use method LinkedList.remove to remove elements from a list. However, it is more efficient to remove multiple elements from a list using Iterator.remove than it would be to use LinkedList.remove. The LinkedList.remove method removes only one element at a time, so you would need to start at the beginning of the list each time and advance down the list to each element that you wanted to remove (O(n2) process). With the Iterator.remove method, you can remove elements as they are accessed by the Iterator object without having to go back to the beginning of the list (O(n) process).

> #### The Enhanced `for` Loop
>
> The enhanced for loop creates an Iterator object and implicitly calls its hasNext and next
> methods. Other Iterator methods, such as remove, are not available.
>
> **FORM**:
> 	for (formalParameter : expression) { . . . }
>
> **EXAMPLE**:
> 	for (String nextStr : myList) { . . . }
> 	for (int nextInt : aList) { . . . }
>
> **MEANING**:
> During each repetition of the loop, the variable specified by formalParameter accesses the next element of expression, starting with the first element and ending with the last. The expression must be an array or a collection that implements the Iterable interface. The Collection interface extends the Iterable interface so that all classes that implement it are implementors of the Iterable interface (see next section).



## The `ListIterator` Interface

### Overview

- The Iterator has some limitations. It can traverse the List only in the forward direction. It also provides only a remove method, not an add method. Also, to start an Iterator somewhere other than at first List element, you must write your own loop to advance the Iterator to the desired starting position.
- The `next` method moves the iterator forward and returns the element that was jumped over. The `previous` method moves the iterator backward and also returns the element that was jumped over.



### Interface `java.util.ListIterator<E>`

| Modifier and Type | Method          | Description                                                  |
| ----------------- | --------------- | ------------------------------------------------------------ |
| void              | add(E e)        | Inserts the specified element into the list (optional operation).<br />Inserts object obj into the list just before the item that would be returned by the next call to method next and after the item that would have been returned by method previous. If the method previous is called after add, the newly inserted object will be returned |
| boolean           | hasNext()       | Returns true if this list iterator has more elements when traversing the list in the forward direction. |
| boolean           | hasPrevious()   | Returns true if this list iterator has more elements when traversing the list in the reverse direction. |
| E                 | next()          | Returns the next element in the list and advances the cursor position. |
| int               | nextIndex()     | Returns the index of the element that would be returned by a subsequent call to next(). |
| E                 | previous()      | Returns the previous element in the list and moves the cursor position backwards. |
| int               | previousIndex() | Returns the index of the element that would be returned by a subsequent call to previous(). |
| void              | remove()        | Removes from the list the last element that was returned by next() or previous() (optional operation).<br />Removes the last item returned from a call to next or previous. If a call to remove is not preceded by a call to next or previous, the IllegalStateException is thrown |
| void              | set(E e)        | Replaces the last element returned by next() or previous() with the specified element (optional).<br />Replaces the last item returned from a call to next or previous with obj. If a call to set is not preceded by a call to next or previous, the IllegalStateException is thrown |





#### Methods in `java.util.LinkedList<E>` that Return `ListIterator`

| Modifier and Type | Method                  | Description                                                  |
| ----------------- | ----------------------- | ------------------------------------------------------------ |
| ListIterator\<E>  | listIterator()          | Returns a ListIterator that begins just before the first list element. |
| ListIterator\<E>  | listIterator(int index) | Returns a ListIterator that begins just before the position index. |



### Comparison of `Iterator` and `ListIterator`

- Because the interface `ListIterator<E>` is a subinterface of `Iterator<E>`, classes that implement `ListIterator` must provide all of the capabilities of both. 
- The `Iterator` interface requires fewer methods and can be used to iterate over more general data structures—that is, structures for which an index is not meaningful and ones for which traversing in only the **forward** direction is required. 
- It is for this reason that the Iterator is required by the `Collection` interface (more general), whereas the `ListIterator` is required only by the `List`
  interface (more specialized).



## The `Iterable` Interface

### Overview

- This interface requires only that a class that implements it provides an `iterator` method. As mentioned above, the `Collection` interface extends the `Iterable` interface, so all classes that implement the `List` interface (a subinterface of `Collection`) must provide an iterator method.



### Interface `java.lang.Iterable<T>`

| Modifier and Type      | Method and Description                                       |
| ---------------------- | ------------------------------------------------------------ |
| default void           | forEach(Consumer<? super T> action)<br/>Performs the given action for each element of the Iterable until all elements have been processed or the action throws an exception. |
| Iterator<T>            | iterator()<br/>Returns an iterator over elements of type T.  |
| default Spliterator<T> | spliterator()<br/>Creates a Spliterator over the elements described by this Iterable. |

