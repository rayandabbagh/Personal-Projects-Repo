import java.util.NoSuchElementException;

/**
 * Your implementation of a non-circular DoublyLinkedList with a tail pointer.
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
public class DoublyLinkedList<T> {

    // Do not add new instance variables or modify existing ones.
    private DoublyLinkedListNode<T> head;
    private DoublyLinkedListNode<T> tail;
    private int size;

    // Do not add a constructor.

    /**
     * Adds the element to the specified index. Don't forget to consider whether
     * traversing the list from the head or tail is more efficient!
     * <p>
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index at which to add the new element
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index must be within the bounds");
        }
        if (data == null) {
            throw new IllegalArgumentException("data can't be null");
        }
        if (index <= size / 2) {
            DoublyLinkedListNode<T> current = head;
            if (index == 0) {
                if (size == 0) {
                    head = new DoublyLinkedListNode<>(data);
                    tail = head;
                } else if (size == 1) {
                    head = new DoublyLinkedListNode<>(data, null, tail);
                    tail.setPrevious(head);
                } else {
                    DoublyLinkedListNode<T> temp = head;
                    head = new DoublyLinkedListNode<>(data, null, head);
                    temp.setPrevious(head);
                }

            } else {
                for (int j = 0; j < index - 1; j++) {
                    current = current.getNext();
                }
                DoublyLinkedListNode<T> temp = current.getNext();
                DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<>(data, current, current.getNext());
                current.setNext(newNode);
                temp.setPrevious(newNode);
            }
            size++;
        } else {
            DoublyLinkedListNode<T> current = tail;
            if (index == size) {
                if (size == 1) {
                    tail = new DoublyLinkedListNode<>(data, head, null);
                    head.setNext(tail);
                } else {
                    DoublyLinkedListNode<T> temp = tail;
                    tail = new DoublyLinkedListNode<>(data, temp, null);
                    temp.setNext(tail);
                }
            } else {
                for (int k = size - 1; k > index; k--) {
                    current = current.getPrevious();
                }
                DoublyLinkedListNode<T> temp = current.getPrevious();
                DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<>(data, current.getPrevious(), current);
                current.setPrevious(newNode);
                temp.setNext(newNode);
            }
            size++;
        }


    }

    /**
     * Adds the element to the front of the list.
     * <p>
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data can't be null");
        }
        if (size == 0) {
            DoublyLinkedListNode<T> newData = new DoublyLinkedListNode<>(data);
            head = newData;
            tail = head;
            size++;

        } else {
            DoublyLinkedListNode<T> newData = new DoublyLinkedListNode<>(data);
            newData.setNext(head);
            head.setPrevious(newData);
            head = newData;
            size++;

        }
    }

    /**
     * Adds the element to the back of the list.
     * <p>
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data can't be null");
        }
        if (size == 0) {
            DoublyLinkedListNode<T> newData = new DoublyLinkedListNode<>(data);
            head = newData;
            tail = head;
            size++;

        } else {
            DoublyLinkedListNode<T> newData = new DoublyLinkedListNode<>(data);
            tail.setNext(newData);
            newData.setPrevious(tail);
            tail = newData;
            size++;

        }
    }

    /**
     * Removes and returns the element at the specified index. Don't forget to
     * consider whether traversing the list from the head or tail is more
     * efficient!
     * <p>
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index has to be within the bounds");
        }
        if (index == size - 1) {
            return removeFromBack();
        }
        if (index <= size / 2) {
            DoublyLinkedListNode<T> curr = head;
            if (index == 0) {
                if (size == 1) {
                    DoublyLinkedListNode<T> temp = head;
                    head = null;
                    tail = null;
                    size--;
                    return temp.getData();
                } else {
                    DoublyLinkedListNode<T> temp = head;
                    head = head.getNext();
                    temp.getNext().setPrevious(null);
                    size--;
                    return temp.getData();
                }

            } else {
                for (int j = 0; j < index - 1; j++) {
                    curr = curr.getNext();
                }

                DoublyLinkedListNode<T> temp = curr.getNext();
                DoublyLinkedListNode<T> temp2 = curr.getNext().getNext();
                curr.setNext(curr.getNext().getNext());
                temp2.setPrevious(curr);
                size--;
                return temp.getData();

            }

        } else {
            DoublyLinkedListNode<T> curr = tail;
            if (index == size - 1) {
                return removeFromBack();
            } else {
                for (int i = size - 1; i > index + 1; i--) {
                    curr = curr.getPrevious();
                }
                DoublyLinkedListNode<T> temp = curr.getPrevious();
                DoublyLinkedListNode<T> temp2 = curr.getPrevious().getPrevious();
                curr.setPrevious(curr.getPrevious().getPrevious());
                temp2.setNext(curr);
                size--;
                return temp.getData();
            }

        }
    }


    /**
     * Removes and returns the first element of the list.
     * <p>
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("The List is Empty");
        } else {
            if (size == 1) {
                DoublyLinkedListNode<T> sole = head;
                head = null;
                tail = null;
                size--;
                return sole.getData();
            } else {
                T first = head.getData();
                head = head.getNext();
                head.setPrevious(null);
                size--;
                return first;
            }
        }
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
            throw new NoSuchElementException("The list is empty");
        } else {
            if (size == 1) {
                DoublyLinkedListNode<T> sole = head;
                head = null;
                tail = null;
                size--;
                return sole.getData();
            } else {
                T last = tail.getData();
                tail = tail.getPrevious();
                tail.setNext(null);
                size--;
                return last;

            }
        }

    }

    /**
     * Returns the element at the specified index. Don't forget to consider
     * whether traversing the list from the head or tail is more efficient!
     * <p>
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index has to be within the bounds");
        }
        if (index == 0) {
            return head.getData();
        }
        if (index == size - 1) {
            return tail.getData();
        } else {
            if (index <= size / 2) {
                DoublyLinkedListNode<T> current = head;
                for (int k = 0; k < index - 1; k++) {
                    current = current.getNext();
                }

                return current.getNext().getData();
            } else {
                DoublyLinkedListNode<T> current = tail;
                for (int i = size - 1; i > index; i--) {
                    current = current.getPrevious();
                }
                return current.getData();

            }

        }

    }

    /**
     * Returns whether or not the list is empty.
     * <p>
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return head == null;

    }

    /**
     * Clears the list.
     * <p>
     * Clears all data and resets the size.
     * <p>
     * Must be O(1).
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;

    }

    /**
     * Removes and returns the last copy of the given data from the list.
     * <p>
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     * <p>
     * Must be O(1) if data is in the tail and O(n) for all other cases.
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data can't be null");
        }
        DoublyLinkedListNode<T> current = tail;
        if (size == 0) {
            throw new NoSuchElementException("Data is not found in the Linked List");
        }
        if (size == 1 && head.getData().equals(data)) {
            DoublyLinkedListNode<T> returndata = head;
            head = null;
            tail = null;
            size = 0;
            return returndata.getData();
        }

        while (current != null && !current.getData().equals(data)) {
            current = current.getPrevious();
        }
        if (current == null) {
            throw new NoSuchElementException("Data is not found in the Linked List");
        }
        if (current == tail) {
            current.getPrevious().setNext(null);
            tail = tail.getPrevious();
            size--;
            return current.getData();
        }
        if (current == head) {
            current.getNext().setPrevious(null);
            head = head.getNext();
            size--;
            return current.getData();
        }
        current.getPrevious().setNext(current.getNext());
        current.getNext().setPrevious(current.getPrevious());
        size--;
        return current.getData();

    }

    /**
     * Returns an array representation of the linked list. If the list is
     * size 0, return an empty array.
     * <p>
     * Must be O(n) for all cases.
     *
     * @return an array of length size holding all of the objects in the
     * list in the same order
     */
    public Object[] toArray() {
        T[] res = (T[]) new Object[size];
        if (isEmpty()) {
            return res;
        }
        DoublyLinkedListNode<T> current = head;
        for (int j = 0; j < size; j++) {
            res[j] = current.getData();
            current = current.getNext();
        }
        return res;
    }
    /**
     * Returns the head node of the list.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public DoublyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }
    /**
     * Returns the tail node of the list.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the tail of the list
     */
    public DoublyLinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
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
        // DO NOT MODIFY!
        return size;
    }
}
