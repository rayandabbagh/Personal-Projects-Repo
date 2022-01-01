import java.util.NoSuchElementException;

/**
 * Your implementation of an ArrayList.
 *
 * @author Rayan Dabbagh
 * @version 1.0
 * @userid rdabbagh3
 * @GTID 903591640
 * <p>
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 * <p>
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class ArrayList<T> {

    /**
     * The initial capacity of the ArrayList.
     * <p>
     * DO NOT MODIFY THIS VARIABLE!
     */
    //CHECKED
    public static final int INITIAL_CAPACITY = 9;

    // Do not add new instance variables or modify existing ones.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     * <p>
     * Java does not allow for regular generic array creation, so you will have
     * to cast an Object[] to a T[] to get the generic typing.
     */
    //CHECKED
    public ArrayList() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Adds the element to the specified index.
     * <p>
     * Remember that this add may require elements to be shifted.
     * <p>
     * Must be amortized O(1) for index size and O(n) for all other cases.
     *
     * @param index the index at which to add the new element
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index has to be within the bounds");
        }
        if (data == null) {
            throw new IllegalArgumentException("Data can't be null");
        }
        // Scenario where the capacity of the ArrayList should be doubled
        if (backingArray.length == size) {
            int newCap = backingArray.length * 2;
            Object[] updatedArray = new Object[newCap];
            for (int i = 0; i < index; i++) {
                updatedArray[i] = backingArray[i];
            }
            updatedArray[index] = data;
            for (int i = index; i < backingArray.length; i++) {
                updatedArray[i + 1] = backingArray[i];
            }
            backingArray = (T[]) updatedArray;
            // Scenario where the size is less than the capacity
        } else {
            for (int i = size - 1; i > index - 1; i--) {
                backingArray[i + 1] = backingArray[i];
            }
            backingArray[index] = data;
        }
        size++;
    }

    /**
     * Adds the element to the front of the list.
     * <p>
     * Remember that this add may require elements to be shifted.
     * <p>
     * Must be O(n).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data you entered is null!");
        }
        if (size == backingArray.length) {
            int newCap = backingArray.length * 2;
            Object[] updatedArray = new Object[newCap];
            updatedArray[0] = data;
            for (int i = 0; i < backingArray.length; i++) {
                updatedArray[i + 1] = backingArray[i];
            }
            backingArray = (T[]) updatedArray;
        } else {
            for (int i = size - 1; i >= 0; i--) {
                backingArray[i + 1] = backingArray[i];
            }
            backingArray[0] = data;
        }
        size++;

    }
    /**
     * Adds the element to the back of the list.
     * <p>
     * Must be amortized O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data you entered is null!");
        }
        if (backingArray.length == size) {
            int newCap = backingArray.length * 2;
            Object[] updatedArray = new Object[newCap];
            for (int i = 0; i < backingArray.length; i++) {
                updatedArray[i] = backingArray[i];
            }
            backingArray = (T[]) updatedArray;
        }
        backingArray[size] = data;
        size++;
    }

    /**
     * Removes and returns the element at the specified index.
     * <p>
     * Remember that this remove may require elements to be shifted.
     * <p>
     * Must be O(1) for index size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index has to be within the bounds");
        }
        if (index == size - 1) {
            return removeFromBack();
        } else {
            T removed = backingArray[index];
            for (int k = index; k < size - 1; k++) {
                backingArray[k] = backingArray[k + 1];
            }
            backingArray[size - 1] = null;
            size--;
            return removed;
        }
    }

    /**
     * Removes and returns the first element of the list.
     * <p>
     * Remember that this remove may require elements to be shifted.
     * <p>
     * Must be O(n).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (size == 0) {
            throw new NoSuchElementException("No elements in the array");
        }
        Object removed = backingArray[0];
        for (int j = 0; j < size - 1; j++) {
            backingArray[j] = backingArray[j + 1];
        }
        backingArray[size - 1] = null;
        size--;
        return (T) removed;
    }

    /**
     * Removes and returns the last element of the list.
     * <p>
     * Must be O(1).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (size == 0) {
            throw new NoSuchElementException("No elements in the array");
        }
        Object removed = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        return (T) removed;

    }

    /**
     * Returns the element at the specified index.
     * <p>
     * Must be O(1).
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index has to be within the bounds");
        }
        return backingArray[index];

    }

    /**
     * Returns whether or not the list is empty.
     * <p>
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the list.
     * <p>
     * Resets the backing array to a new array of the initial capacity and
     * resets the size.
     * <p>
     * Must be O(1).
     */
    public void clear() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;

    }

    /**
     * Returns the backing array of the list.
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
     * Returns the size of the list.
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
}
