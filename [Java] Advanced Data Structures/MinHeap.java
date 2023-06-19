import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Your implementation of a MinHeap.
 *
 * @author Rayan Dabbagh
 * @version 1.0
 * @userid rdabbagh3
 * @GTID 903591640
 * <p>
 * Collaborators: NA
 * <p>
 * Resources: NA
 */
public class MinHeap<T extends Comparable<? super T>> {

    /**
     * The initial capacity of the MinHeap when created with the default
     * constructor.
     * <p>
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    // Do not add new instance variables or modify existing ones.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new MinHeap.
     * <p>
     * The backing array should have an initial capacity of INITIAL_CAPACITY.
     */
    public MinHeap() {
        backingArray = (T[]) (new Comparable[INITIAL_CAPACITY]);
        size = 0;
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     * <p>
     * You must use the BuildHeap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     * As a reminder, this is the algorithm that involves building the heap
     * from the bottom up by repeated use of downHeap operations.
     * <p>
     * Before doing the algorithm, first copy over the data from the
     * ArrayList to the backingArray (leaving index 0 of the backingArray
     * empty). The data in the backingArray should be in the same order as it
     * appears in the passed in ArrayList before you start the BuildHeap
     * algorithm.
     * <p>
     * The backingArray should have capacity 2n + 1 where n is the
     * number of data in the passed in ArrayList (not INITIAL_CAPACITY).
     * Index 0 should remain empty, indices 1 to n should contain the data in
     * proper order, and the rest of the indices should be empty.
     *
     * @param data a list of data to initialize the heap with
     * @throws java.lang.IllegalArgumentException if data or any element in data is null
     */
    public MinHeap(ArrayList<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("The data you entered is null! Cannot make a heap out of null.");
        }
        size = data.size();
        backingArray = (T[]) new Comparable[(2 * size) + 1];
        for (int j = 0; j < size; j++) {
            if (data.get(j) == null) {
                throw new IllegalArgumentException("Null is inside your collection! Cannot add null to the heap.");
            }
            backingArray[j + 1] = data.get(j);
        }
        for (int k = size / 2; k >= 1; k--) {
            helperHeap(k);
        }

    }

    /**
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     * The order property of the heap must be maintained after adding.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data entered is null! Cannot add null to the list!");
        }

        if (size == 0) {
            backingArray[1] = data;
            size++;
            return;
        }
        if (size == backingArray.length - 1) {
            Comparable[] otherArray = new Comparable[backingArray.length * 2];
            for (int j = 1; j < backingArray.length; j++) {
                otherArray[j] = backingArray[j];
            }
            backingArray = (T[]) otherArray;
        }
        backingArray[size + 1] = data;
        int childInd = size + 1;
        int parentInd = (size + 1) / 2;
        while (parentInd >= 1 && (backingArray[childInd].compareTo(backingArray[parentInd]) < 0)) {
            Object temporary = backingArray[parentInd];
            backingArray[parentInd] = backingArray[childInd];
            backingArray[childInd] = (T) temporary;

            childInd = parentInd;
            parentInd = childInd / 2;
        }
        size++;

    }

    /**
     * Removes and returns the min item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     * The order property of the heap must be maintained after adding.
     *
     * @return the data that was removed
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T remove() {
        if (size == 0) {
            throw new NoSuchElementException("The heap is empty! There is nothing to remove!");
        }
        T rem = backingArray[1];
        if (size == 1) {
            backingArray[1] = null;
            size--;
            return rem;
        }
        T tempVar = backingArray[size];
        backingArray[size] = backingArray[1];
        backingArray[1] = tempVar;


        backingArray[size] = null;
        size--;

        helperHeap(1);

        return rem;
    }


    /**
     * Returns the minimum element in the heap.
     *
     * @return the minimum element
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T getMin() {
        if (size == 0) {
            throw new NoSuchElementException("The heap is empty! There is no max element.");
        }
        return backingArray[1];
    }

    /**
     * Returns whether or not the heap is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Clears the heap.
     * <p>
     * Resets the backing array to a new array of the initial capacity and
     * resets the size.
     */
    public void clear() {
        backingArray = (T[]) (new Comparable[INITIAL_CAPACITY]);
        size = 0;
    }

    /**
     * Returns the backing array of the heap.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Heapify Helper method
     *
     * @param index represents the index of the element that you want to heapify.
     */
    private void helperHeap(int index) {

        while ((index * 2) < backingArray.length && backingArray[(index * 2)] != null) {

            if (backingArray[(index * 2) + 1] == null) {

                if (backingArray[(index * 2)].compareTo(backingArray[index]) < 0) {
                    Object temporary = backingArray[(index * 2)];
                    backingArray[(index * 2)] = backingArray[index];
                    backingArray[index] = (T) temporary;
                    index = index * 2;
                } else {
                    break;
                }
            } else {
                if (backingArray[(index * 2)].compareTo(backingArray[index]) > 0
                        && backingArray[(index * 2) + 1].compareTo(backingArray[index]) > 0) {
                    break;
                } else if (backingArray[(index * 2)].compareTo(backingArray[(index * 2) + 1]) < 0) {

                    T tempVar = backingArray[(index * 2)];
                    backingArray[(index * 2)] = backingArray[index];
                    backingArray[index] = tempVar;
                    index = index * 2;
                } else {
                    T tempVar = backingArray[(index * 2) + 1];
                    backingArray[(index * 2) + 1] = backingArray[index];
                    backingArray[index] = tempVar;
                    index = (index * 2) + 1;
                }
            }
        }
    }

}
