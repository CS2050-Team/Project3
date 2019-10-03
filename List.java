import java.util.Arrays;

import javax.swing.text.html.parser.Entity;

/**
 * List
 */


public class List<T> implements MyCollectionInterface<T> {


    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_CAPACITY = 1000;
    private T[] array;

    public MyList() {
        array = new T[DEFAULT_CAPACITY];
    }

   /**
    * Adds a new entry to this list
    * 
    * @param newItem The object to be added to the list
    * @return True if the addition is successful, or false if not.
    */
    public boolean add (T newItem) {

        if (size == elements.length) {
            if (!ensureCapacity()) {
                return false;
            }
        }

        elements[size++] = newItem;

        return true;


    }
    
    //************************************************************************
    
    private boolean ensureCapacity() {
        int capacity = array.length;
        if (size == capacity) {
            int newCapacity = capacity * (3 / 2) + 1;
            if (newCapacity > MAX_CAPACITY) {
                if (MAX_CAPACITY > size) {
                    newCapacity = MAX_CAPACITY;
                } else {
                    return false;
                }
            }
            array = Arrays.copyOf(array, newCapacity);
        }
        return true;
    }
    
    
    //************************************************************************
 
    /**
     * Removes one unspecified entry from the list, if possible.
     *
     * @return Either the removed entry, if the removal was successful, or null.
     */
    public T remove () {
        if (size == 0) {
            return null;
        }
        size--;
        T removed = array[size];
        array[size] = null;
        return removed;
    };
 
    //************************************************************************
 
    /**
     * Removes one occurrence of a given entry from the list.
     *
     * @param anEntry The entry to be removed.
     * @return true if the removal was successful, false if not.
     */
    public boolean remove (T anEntry) {
        for (int i = 0; i<size; i++) {
            if (array[i].equals(anEntry)) {
                for (int j = i; j < size; j++)​ {
                    list[j] = list[j + 1];​
                }
                list[size] = null;
                size--;
                return true;
            }
        }
    
        return false;
    }


    
 
    //************************************************************************
 
    /**
     * Removes all entries from this list.
     */
    public void clear() {
        size = 0;
        array = new T[DEFAULT_CAPACITY];
    }
 
    //************************************************************************
 
    /**
     * Gets the current number of entries in this list.
     *
     * @return The integer number of entries currently in the list.
     */
    public int getCurrentSize() {
        return size;
    }
 
    //************************************************************************
 
    /**
     * Check to see if the list is empty.
     *
     * @return True if the list is empty, or false if not.
     */
    public boolean isEmpty() {
        return (size==0);
    }
 
    //************************************************************************
 
    /**
     * Counts the number of times a given entry appears in this list.
     *
     * @param anEntry The entry to be counted.
     * @return The number of times anEntry appears in the list.
     */
    public int getFrequencyOf(T anEntry) {
        int count = 0;
        for (T entry : array) {
            if (entry==anEntry) {
                count++;
            }
        }
        return count;
    }
 
    //************************************************************************
 
    /**
     * Tests whether this list contains a given entry.
     *
     * @param anEntry The entry to locate.
     * @return True if the list contains anEntry, or false if not.
     */
    public boolean contains (T anEntry) {
        for (T entry : array) {
            if (entry==anEntry) {
                return true;
            }
        }
        return false;
    }
 
    //************************************************************************
 
    /**
     * Retrieves all entries that are in this list.
     *
     * @return A newly allocated array of all the entries in the list. 
     * Note: If the list is empty, the returned array is empty.
     */
    public Object[] toArray () {
        return Arrays.copyOfRange(array, 0, size);
    }
 
}