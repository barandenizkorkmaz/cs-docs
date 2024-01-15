# Sorting

## Sorting in Java

- The Java API java.util provides a class Arrays with several overloaded sort methods for
  different array types. In addition, the class Collections (also part of the API java.util) con-
  tains similar sorting methods for Lists. The methods for arrays of primitive types are based
  on the quicksort algorithm (Section 8.9), and the methods for arrays of Objects and for Lists
  are based on the Timsort algorithm (Section 8.7). Both algorithms are O(n log n).
  - Method Arrays.sort is defined as a public static void method and is overloaded (see
    Table 8.1). The first argument in a call can be an array of any primitive type (although we
    have just shown int[]) or an array of objects. If the first argument is an array of objects, then
    either the class type of the array must implement the Comparable interface or a Comparator
    object must be passed as the last argument (see Section 6.6). A class that implements the
    Comparable interface must define a compareTo method that determines the natural ordering of
    its objects. If a Comparator is passed, its compare method will be used to determine the
    ordering.
  - For method Collections.sort (see Table 8.1), the first argument must be a collection of
    objects that implement the List interface (e.g., an ArrayList or a LinkedList). If only one
    argument is provided, the objects in the List must implement the Comparable interface.
    Method compareTo is called by the sorting method to determine the relative ordering of two
    objects.
  - Optionally, a Comparator can be passed as a second argument. Using a Comparator, you can
    compare objects based on some other information rather than using their natural ordering
    (as determined by method compareTo). The Comparator object must be the last argument in
    the call to the sorting method. Rather than rearranging the elements in the List, method sort
    first copies the List elements to an array, sorts the array using Arrays.sort, and then copies
    them back to the List.

| Method `sort` in Class `Arrays`                              | Description                                                  |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| `sort(int[] items)`                                          | Sorts the array `items` in ascending order                   |
| `sort(int[] items, int fromIndex, int toIndex)`              | Sorts array elements `items[fromIndex]` to `items[toIndex]` in ascending order |
| `sort(Object[] items)`                                       | Sorts the objects in array `items` in ascending order using their natural ordering (defined by `compareTo`) |
| `sort(Object[] items, int fromIndex, int toIndex)`           | Sorts array elements `items[fromIndex]` to `items[toIndex]` in ascending order using natural ordering |
| `<T> void sort(T[] items, Comparator<? super T> comp)`       | Sorts the objects in `items` in ascending order as defined by method `comp.compare` |
| `<T> void sort(T[] items, int fromIndex, int toIndex, Comparator<? super T> comp)` | Sorts array elements `items[fromIndex]` to `items[toIndex]` in ascending order using method `comp.compare` |

| Method `sort` in Class `Collections`                      | Description                                                  |
| --------------------------------------------------------- | ------------------------------------------------------------ |
| `<T extends Comparable<T>> void sort(List<T> list)`       | Sorts the objects in `list` in ascending order using their natural ordering (defined by `compareTo`) |
| `<T> void sort(List<T> list, Comparator<? super T> comp)` | Sorts the objects in `list` in ascending order as defined by method `comp.compare` |

| Method `sort` in Interface `List`               | Description                                                  |
| ----------------------------------------------- | ------------------------------------------------------------ |
| `default void sort(Comparator<? super E> comp)` | Sorts the objects in the list in ascending order as defined by method `comp.compare` |

- For the second method parameter, the notation `Comparator<? super T>` means that comp must be an object that implements the Comparator interface for type T or for a superclass of type T.

- In Java 8, you can pass a lambda expression as a `Comparator` object to the `List.sort` method
  instead of writing a class that implements `Comparator`.



## Selection Sort

### Algorithm

```java
1.	for fill = 0 to n – 2 do
2.		Set posMin to the subscript of the smallest item in the subarray starting at subscript fill.
3.		Exchange the item at posMin with the one at fill.
```

```java
Refinement of Selection Sort Algorithm (Step 2)

2.1 Initialize posMin to fill.
2.2 for next = fill + 1 to n ‐ 1
2.3 	if the item at next is less than the item at posMin
2.4 		Reset posMin to next.
```



### Implementation

```java
/** Implements the selection sort algorithm. */
public class SelectionSort {
    /** Sort the array using selection sort algorithm.
     @pre table contains Comparable objects.
     @post table is sorted.
     @param table The array to be sorted
     */
    public static void sort(Comparable[] table) {
        int n = table.length;
        for (int fill = 0; fill < n - 1; fill++) {
            // Invariant: table[0 . . . fill – 1] is sorted.
            int posMin = fill;
            for (int next = fill + 1; next < n; next++) {
                // Invariant: table[posMin] is the smallest item in
                // table[fill . . . next ‐ 1].
                if (table[next].compareTo(table[posMin]) < 0) {
                    posMin = next;
                }
            }
            // assert: table[posMin] is the smallest item in
            // table[fill . . . n ‐ 1].
            // Exchange table[fill] and table[posMin].
            Comparable temp = table[fill];
            table[fill] = table[posMin];
            table[posMin] = temp;
            // assert: table[fill] is the smallest item in
            // table[fill . . . n ‐ 1].
        }
        // assert: table[0 . . . n ‐ 1] is sorted.
    }
}
```



### Analysis

- The number of comparisons is $O(n^2)$.
- The number of exchanges is $O(n)$.

- The selection sort is a quadratic sort.



> #### PROGRAM STYLE
>
> ##### Making Sort Methods Generic
>
> The code above will compile, but it will generate a warning message regarding an unchecked call to `compareTo`. You can eliminate this warning message by making the sort a generic sort. To accomplish this for the sort above, change the method heading to
> 	`public static <T extends Comparable<T>> void sort(T[] table) {`
> where the generic type parameter, T, must implement the Comparable<T> interface.
> Also, change the data type of variable temp from Comparable to type T, the data type of the array elements.
> 	`T temp = table[fill];`
> We will code the other sorting algorithms in this chapter as generic methods.



## Insertion Sort

### Algorithm

```java
1. for each array element from the second (nextPos = 1) to the last
2.    Insert the element at nextPos where it belongs in the array, increasing the length of the sorted subarray by 1 element.

```

```java
Refinement of Insertion Sort Algorithm (Step 2)

2.1 nextPos is the position of the element to insert.
2.2 Save the value of the element to insert in nextVal.
2.3 while nextPos > 0 and the element at nextPos – 1 > nextVal
2.4   Shift the element at nextPos – 1 to position nextPos.
2.5   Decrement nextPos by 1.
2.6 Insert nextVal at nextPos.
```



### Implementation

```java
/** Implements the insertion sort algorithm. */
public class InsertionSort {
    /**
     * Sort the table using insertion sort algorithm.
     * @pre table contains Comparable objects.
     * @post table is sorted.
     * @param table The array to be sorted
     */
    public static <T extends Comparable<T>> void sort(T[] table) {
        for (int nextPos = 1; nextPos < table.length; nextPos++) {
            // Invariant: table[0 . . . nextPos - 1] is sorted.
            // Insert element at position nextPos in the sorted subarray.
            insert(table, nextPos);
        } // End for.
    } // End sort.

    /**
     * Insert the element at nextPos where it belongs in the array.
     * @pre table[0 . . . nextPos - 1] is sorted.
     * @post table[0 . . . nextPos] is sorted.
     * @param table The array being sorted.
     * @param nextPos The position of the element to insert.
     */
    private static <T extends Comparable<T>> void insert(T[] table, int nextPos) {
        T nextVal = table[nextPos]; // Element to insert.

        while (nextPos > 0 && nextVal.compareTo(table[nextPos - 1]) < 0) {
            table[nextPos] = table[nextPos - 1]; // Shift down.
            nextPos--; // Check next smaller element.
        }

        // Insert nextVal at nextPos.
        table[nextPos] = nextVal;
    }
}
```

- We use the method `insert` to perform the insertion step so that it would be easier to implement the `Shell sort` algorithm later. 



### Analysis

- The maximum number of comparisons is represented by the series $1 + 2 + ... + (n -2) + (n -1)$, which is $O(n^2)$.



