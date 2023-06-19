import java.util.NoSuchElementException;

/**
 * Your implementation of a LinkedDeque.
 *
 * @author Rayan Dabbagh
 * @version 1.0
 * @userid rdabbagh3
 * @GTID 903591640
 * <p>
 * Collaborators: N/A
 * <p>
 * Resources: N/A
 */
public class LinkedDeque<T> {

    // Do not add new instance variables or modify existing ones.
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    // Do not add a constructor.

    /**
     * Adds the element to the front of the deque.
     * <p>
     * Must be O(1).
     *
     * @param data the data to add to the front of the deque
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addFirst(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null and can't be added to the deque");
        }
        LinkedNode<T> newNode = new LinkedNode<>(data);
        if (head == null) {
            head = newNode;
            tail = head;
        } else {
            head.setPrevious(newNode);
            newNode.setNext(head);
            head = head.getPrevious();

        }
        size++;
    }

    /**
     * Adds the element to the back of the deque.
     * <p>
     * Must be O(1).
     *
     * @param data the data to add to the back of the deque
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addLast(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null and can't be added to the deque");
        }
        LinkedNode<T> newNode = new LinkedNode<>(data);

        if (head == null) {
            head = newNode;
            tail = head;
        } else {

            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = tail.getNext();
        }
        size++;
    }

    /**
     * Removes and returns the first element of the deque.
     * <p>
     * Must be O(1).
     *
     * @return the data formerly located at the front of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("Since the list is empty, then there is nothing to remove");
        }
        T rem = head.getData();
        head = head.getNext();
        if (head != null) {
            head.setPrevious(null);
        }
        size--;
        if (size == 0) {
            tail = null;
        }
        return rem;
    }

    /**
     * Removes and returns the last element of the deque.
     * <p>
     * Must be O(1).
     *
     * @return the data formerly located at the back of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("Since the list is empty, then there is nothing to remove");
        }
        T rem = tail.getData();
        tail = tail.getPrevious();
        if (tail != null) {
            tail.setNext(null);
        }
        size--;
        if (size == 0) {
            head = null;
        }
        return rem;
    }

    /**
     * Returns the first data of the deque without removing it.
     * <p>
     * Must be O(1).
     *
     * @return the data located at the front of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T getFirst() {
        if (size == 0) {
            throw new NoSuchElementException("Since the deque is empty,we can't take a look at the first data");
        }
        T first = head.getData();
        return first;

    }

    /**
     * Returns the last data of the deque without removing it.
     * <p>
     * Must be O(1).
     *
     * @return the data located at the back of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T getLast() {
        if (size == 0) {
            throw new NoSuchElementException("Since the deque is empty,we can't take a look at the last data");
        }
        T last = tail.getData();
        return last;
    }

    /**
     * Returns the head node of the deque.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return node at the head of the deque
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }

    /**
     * Returns the tail node of the deque.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return node at the head of the deque
     */
    public LinkedNode<T> getTail() {
        // DO NOT MODIFY THIS METHOD!
        return tail;
    }

    /**
     * Returns the size of the deque.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the deque
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
