
# Interface `Comparable<E>` and `Comparator<E>`

- How do we compare elements in a PriorityQueue?



## Interface `Comparable<E>`

- In many cases, we will insert objects that implement `Comparable<E> `and use their natural ordering as specified by method `compareTo`.
- `Comparable<E>` is used to define the natural ordering of an object type.



## Interface `Comparator<E>`

- However, we may need to insert objects that do not implement `Comparable<E>`, or we may
  want to specify a different ordering from that defined by the object’s `compareTo` method.
    - For example, files to be printed may be ordered by their name using the compareTo method, but
      we may want to assign priority based on their length.
- The Java API contains the `Comparator<E>` interface, which allows us to specify alternative ways to compare objects. An implementer of the `Comparator<E>` interface must define a `compare` method that is similar to `compareTo` except that it has two parameters.

```java
public interface Comparator<T> {
    public int compare(T first, T second);
}
```

**Example**

```java
public class RectangleAreaComparator implements Comparator<Rectangle> {
    // compare in ascending order by area (WxH)
    public int compare(Rectangle r1, Rectangle r2) {
        return r1.getArea() - r2.getArea();
    }
}
```



### Using Comparators

1. TreeSet, TreeMap , PriorityQueue can use Comparator:

   ```java
   Comparator<Rectangle> comp = new RectangleAreaComparator();
   Set<Rectangle> set = new TreeSet<Rectangle>(comp);
   Queue<Rectangle> pq = new PriorityQueue<Rectangle>(10,comp);
   ```

2. Searching and sorting methods can accept Comparators.

   ```java
   Arrays.binarySearch(array, value, comparator)
   Arrays.sort(array, comparator)
   Collections.binarySearch(list, comparator)
   Collections.max(collection, comparator)
   Collections.min(collection, comparator)
   Collections.sort(list, comparator)
   ```

3. Methods are provided to reverse a Comparator's ordering:

   ```java
   public static Comparator Collections.reverseOrder()
   public static Comparator Collections.reverseOrder(comparator)
   ```



**Example:** Using Comparator for ordering a Priority Queue

```java
public class LengthComparator implements Comparator<String> {
    public int compare(String s1, String s2) {
        if (s1.length() != s2.length()) {
            // if lengths are unequal, compare by length
            return s1.length() - s2.length();
        } else {
            // break ties by ABC order
            return s1.compareTo(s2);
        }
    }
}

Queue<String> pq = new PriorityQueue<String>(100, new LengthComparator());
```

- Observe that, in the example, we are still making use of the fact that the class `String` is implementing `Comparable<E>` interface to define its own natural ordering by implementing `compareTo` method.