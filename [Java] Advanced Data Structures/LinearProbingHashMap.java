import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.ArrayList;

/**
 * Your implementation of a LinearProbingHashMap.
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
public class LinearProbingHashMap<K, V> {

    /**
     * The initial capacity of the LinearProbingHashMap when created with the
     * default constructor.
     * <p>
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /**
     * The max load factor of the LinearProbingHashMap
     * <p>
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final double MAX_LOAD_FACTOR = 0.67;

    // Do not add new instance variables or modify existing ones.
    private LinearProbingMapEntry<K, V>[] table;
    private int size;

    /**
     * Constructs a new LinearProbingHashMap.
     * <p>
     * The backing array should have an initial capacity of INITIAL_CAPACITY.
     * <p>
     * Use constructor chaining.
     */
    public LinearProbingHashMap() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Constructs a new LinearProbingHashMap.
     * <p>
     * The backing array should have an initial capacity of initialCapacity.
     * <p>
     * You may assume initialCapacity will always be positive.
     *
     * @param initialCapacity the initial capacity of the backing array
     */
    public LinearProbingHashMap(int initialCapacity) {
        table = (LinearProbingMapEntry<K, V>[]) new LinearProbingMapEntry[initialCapacity];
        size = 0;
    }

    /**
     * Adds the given key-value pair to the map. If an entry in the map
     * already has this key, replace the entry's value with the new one
     * passed in.
     * <p>
     * In the case of a collision, use linear probing as your resolution
     * strategy.
     * <p>
     * Before actually adding any data to the HashMap, you should check to
     * see if the array would violate the max load factor if the data was
     * added. For example, let's say the array is of length 5 and the current
     * size is 3 (LF = 0.6). For this example, assume that no elements are
     * removed in between steps. If another entry is attempted to be added,
     * before doing anything else, you should check whether (3 + 1) / 5 = 0.8
     * is larger than the max LF. It is, so you would trigger a resize before
     * you even attempt to add the data or figure out if it's a duplicate. Be
     * careful to consider the differences between integer and double
     * division when calculating load factor.
     * <p>
     * When regrowing, resize the length of the backing table to
     * 2 * old length + 1. You must use the resizeBackingTable method to do so.
     * <p>
     * Return null if the key was not already in the map. If it was in the map,
     * return the old value associated with it.
     *
     * @param key   the key to add
     * @param value the value to add
     * @return null if the key was not already in the map. If it was in the
     * map, return the old value associated with it
     * @throws java.lang.IllegalArgumentException if key or value is null
     */
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("key/value is null!");
        }
        LinearProbingMapEntry<K, V> entry = new LinearProbingMapEntry<>(key, value);

        if (((double) (size + 1) / (double) table.length) > MAX_LOAD_FACTOR) {
            resizeBackingTable((table.length * 2) + 1);
        }
        int index;
        int count = 0;
        boolean found = false;
        int hashKey = key.hashCode();
        V oldValue = null;
        int removedIndex = -1;

        while (count < table.length && !found) {
            index = Math.abs((hashKey + count) % table.length);
            if (table[index] == null) {
                if (removedIndex >= 0) {
                    table[removedIndex] = entry;
                    table[removedIndex].setRemoved(false);
                } else {
                    table[index] = entry;
                }
                found = true;
                size++;
            } else if (table[index].getKey().equals(key) && !table[index].isRemoved()) {
                found = true;
                oldValue = table[index].getValue();
                table[index] = entry;
            } else if (table[index].getKey().equals(key) && table[index].isRemoved()) {
                if (removedIndex >= 0) {
                    table[removedIndex] = entry;
                    table[removedIndex].setRemoved(false);
                } else {
                    table[index] = entry;
                }
                found = true;
                size++;
            }
            if (table[index] != null && table[index].isRemoved() && removedIndex < 0) {
                removedIndex = index;
            }
            count++;
        }
        return oldValue;

    }


    /**
     * Removes the entry with a matching key from map by marking the entry as
     * removed.
     *
     * @param key the key to remove
     * @return the value previously associated with the key
     * @throws java.lang.IllegalArgumentException if key is null
     * @throws java.util.NoSuchElementException   if the key is not in the map
     */
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        int hashKey = key.hashCode();
        int index;
        int count = 0;
        while (count < table.length) {
            index = Math.abs((hashKey + count) % table.length);
            if (table[index] == null) {
                throw new NoSuchElementException("key can't be found in the map");
            }
            if (table[index].getKey().equals(key) && table[index].isRemoved()) {
                throw new NoSuchElementException("key can't be found in the map");
            }
            if (table[index].getKey().equals(key) && !table[index].isRemoved()) {
                V removed = table[index].getValue();
                table[index].setRemoved(true);
                size--;
                return removed;
            }
            count++;
        }
        throw new NoSuchElementException("key can't be found in the map");
    }

    /**
     * Gets the value associated with the given key.
     *
     * @param key the key to search for in the map
     * @return the value associated with the given key
     * @throws java.lang.IllegalArgumentException if key is null
     * @throws java.util.NoSuchElementException   if the key is not in the map
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("null key");
        }
        int hashKey = key.hashCode();
        int index;
        int count = 0;
        while (count < table.length) {
            index = Math.abs((hashKey + count) % table.length);
            if (table[index] == null) {
                throw new NoSuchElementException("key not found within the map");
            }
            if (table[index].getKey().equals(key) && table[index].isRemoved()) {
                throw new NoSuchElementException("key not found within the map");
            }
            if (table[index].getKey().equals(key) && !table[index].isRemoved()) {
                return table[index].getValue();
            }
            count++;
        }
        throw new NoSuchElementException("key not found within the map");
    }

    /**
     * Returns whether or not the key is in the map.
     *
     * @param key the key to search for in the map
     * @return true if the key is contained within the map, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if key is null
     */
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key -> null");
        }
        int hashKey = key.hashCode();
        int index;
        boolean found = false;
        int count = 0;
        while (count < table.length) {
            index = Math.abs((hashKey + count) % table.length);
            if (table[index] == null) {
                break;
            }
            if (table[index].getKey().equals(key) && table[index].isRemoved()) {
                break;
            }
            if (table[index].getKey().equals(key) && !table[index].isRemoved()) {
                found = true;
                break;
            }
            count++;
        }
        return found;
    }

    /**
     * Returns a Set view of the keys contained in this map.
     * <p>
     * Use java.util.HashSet.
     *
     * @return the set of keys in this map
     */
    public Set<K> keySet() {
        HashSet<K> keySet = new HashSet<>();
        int counter = 0;
        for (int j = 0; j < table.length && counter < size; j++) {
            if (table[j] != null && !table[j].isRemoved()) {
                keySet.add(table[j].getKey());
                counter++;
            }
        }
        return keySet;
    }

    /**
     * Returns a List view of the values contained in this map.
     * <p>
     * Use java.util.ArrayList or java.util.LinkedList.
     * <p>
     * You should iterate over the table in order of increasing index and add
     * entries to the List in the order in which they are traversed.
     *
     * @return list of values in this map
     */
    public List<V> values() {
        ArrayList<V> valuesList = new ArrayList<>();
        int counter = 0;
        for (int z = 0; z < table.length && counter < size; z++) {
            if (table[z] != null && !table[z].isRemoved()) {
                valuesList.add(table[z].getValue());
                counter++;
            }
        }
        return valuesList;
    }

    /**
     * Resize the backing table to length.
     * <p>
     * Disregard the load factor for this method. So, if the passed in length is
     * smaller than the current capacity, and this new length causes the table's
     * load factor to exceed MAX_LOAD_FACTOR, you should still resize the table
     * to the specified length and leave it at that capacity.
     * <p>
     * You should iterate over the old table in order of increasing index and
     * add entries to the new table in the order in which they are traversed.
     * You should NOT copy over removed elements to the resized backing table.
     * <p>
     * Since resizing the backing table is working with the non-duplicate
     * data already in the table, you shouldn't explicitly check for
     * duplicates.
     * <p>
     * Hint: You cannot just simply copy the entries over to the new array.
     *
     * @param length new length of the backing table
     * @throws java.lang.IllegalArgumentException if length is less than the
     *                                            number of items in the hash
     *                                            map
     */
    public void resizeBackingTable(int length) {
        if (length < size) {
            throw new IllegalArgumentException("length less than number of items in the map");
        }
        LinearProbingMapEntry<K, V>[] otherTable = (LinearProbingMapEntry<K, V>[]) new LinearProbingMapEntry[length];
        int index;
        int count;
        int hash;
        int counter = 0;
        for (int i = 0; i < table.length && counter < size; i++) {
            if (table[i] != null && !table[i].isRemoved()) {
                count = 0;
                hash = table[i].getKey().hashCode();
                counter++;
                while (count < table.length) {
                    index = Math.abs((hash + count) % length);
                    if (otherTable[index] == null) {
                        otherTable[index] = new LinearProbingMapEntry<K, V>(table[i].getKey(), table[i].getValue());
                        break;
                    }
                    count++;
                }
            }
        }
        table = otherTable;
    }

    /**
     * Clears the map.
     * <p>
     * Resets the table to a new array of the INITIAL_CAPACITY and resets the
     * size.
     * <p>
     * Must be O(1).
     */
    public void clear() {
        table = (LinearProbingMapEntry<K, V>[]) new LinearProbingMapEntry[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Returns the table of the map.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the table of the map
     */
    public LinearProbingMapEntry<K, V>[] getTable() {
        // DO NOT MODIFY THIS METHOD!
        return table;
    }

    /**
     * Returns the size of the map.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the map
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
