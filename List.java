import java.util.Arrays;

import javax.swing.text.html.parser.Entity;

/**
 *
 * List.java - an array/list implementation of the Collection ADT
 * 
 * @author Alex Schor
 * @version 1.0
 *
 */



public class List<T> implements MyCollectionInterface<T> {


    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_CAPACITY = 1000;
    private T[] array;

    public List() {
        // This is NOT type-safe! This array must remain encapsulated in this
        // class so that all access to and from the array is done with the
        // generic T. When it is output to the user via toArray the type must
        // be specified by the user.
        
        array = (T[]) new Object[DEFAULT_CAPACITY];

    } // end construtor

   /**
    * Adds a new entry to this list
    * 
    * @param newItem The object to be added to the list
    * @return True if the addition is successful, or false if not.
    */
    public boolean add (T newItem) {

        if (size == array.length) {
            if (!ensureCapacity()) {
                return false;
            } // end if
        } // end if

        array[size++] = newItem;

        return true;

    } // end add method
    
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
                } // end if
            } // end if
            array = Arrays.copyOf(array, newCapacity);
        } // end if
        return true;
    } // end ensureCapacity method
    
    
    //************************************************************************
 
    /**
     * Removes one unspecified entry from the list, if possible.
     *
     * @return Either the removed entry, if the removal was successful, or null.
     */
    public T remove () {
        if (size == 0) {
            return null;
        } // end if
        size--;
        T removed = array[size];
        array[size] = null;
        return removed;
    } // end remove method
 
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
                for (int j = i; j < size; j++) {
                    array[j] = array[j + 1];
                }
                array[size] = null;
                size--;
                return true;
            } // end if
        } // end for
    
        return false;
    } // end remove method


    
 
    //************************************************************************
 
    /**
     * Removes all entries from this list.
     */
    public void clear() {
        size = 0;
        array = (T[]) new Object[DEFAULT_CAPACITY];
    } // end clear method
 
    //************************************************************************
 
    /**
     * Gets the current number of entries in this list.
     *
     * @return The integer number of entries currently in the list.
     */
    public int getCurrentSize() {
        return size;
    } // end getCurrentSize method
 
    //************************************************************************
 
    /**
     * Check to see if the list is empty.
     *
     * @return True if the list is empty, or false if not.
     */
    public boolean isEmpty() {
        return (size==0);
    } // end isEmpty method
 
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
            } // end if
        } // end for
        return count;
    } // end getFrequencyOf method
 
    //************************************************************************
 
    /**
     * Tests whether this list contains a given entry.
     *
     * @param anEntry The entry to locate.
     * @return True if the list contains anEntry, or false if not.
     */
    public boolean contains (T anEntry) {
        for (T entry : array) {
            if (entry !=  null && entry.equals(anEntry)) {
                return true;
            } // end if
        } // end for
        return false;
    } // end contains method
 
    //************************************************************************
 
    /**
     * Retrieves all entries that are in this list.
     *
     * @return A newly allocated array of all the entries in the list. 
     * Note: If the list is empty, the returned array is empty.
     */
    public Object[] toArray () {
        return Arrays.copyOf(array, size);
    } // end toArray method

    /**
     * Retrieves all entries that are in this list and returns them as an array
     * with the runtime type of the given array. If the given array is long
     * enough, it is returned with the entries inside it. Otherwise, a new
     * array is initialized with the same type as the given array. This allows
     * you to obtain a typed array from this class.
     * 
     * @param givenArray
     * @return a typed array of the contents of the list
     */
    public <T> T[] toArray(T[] givenArray) {
        // If the given array has insufficient space, create a new one with the
        // same type.
        if (givenArray.length < size) {
            return (T[]) Arrays.copyOf(array, size,
            givenArray.getClass());
        } // end if

        // Othewise, copy the data into the given array and return that.
        System.arraycopy(array, 0, givenArray, 0, size);

        return givenArray;
    } // end toArray(givenArray) method
 
}