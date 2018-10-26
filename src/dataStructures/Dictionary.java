package dataStructures; 

import java.io.Serializable;

/**
 * Dictionary Abstract Data Type 
 * Includes description of general methods to be implemented by dictionaries.
 * @author AED  Team
 * @version 1.0
 * @param <K> Generic Key
 * @param <V> Generic Value 
 */

public interface Dictionary<K,V> extends Serializable
{                                                                   

    /**
     * Returns true iff the dictionary contains no entries.
     * @return true if dictionary is empty
     */
    boolean isEmpty( );                                           

    /**
     * Returns the number of entries in the dictionary.
     * @return number of elements in the dictionary
     */
    int size( );                                                  

    /**
     * If there is an entry in the dictionary whose key is the specified key,
     * returns its value; otherwise, returns null.
     * @param key whose associated value is to be returned
     * @return value of entry in the dictionary whose key is the specified key, 
     * or null if the dictionary does not has an entry with that key
     */
    V find( K key );                                      

    /**
     * If there is an entry in the dictionary whose key is the specified key,
     * replaces its value by the specified value and returns the old value;
     * otherwise, inserts the entry (key, value) and returns null.
     * @param key with which the specified value is to be associated
     * @param value to be associated with the specified key
     * @return previous value associated with key, 
     * or null if the dictionary does not has an entry with that key
     */
    V insert( K key, V value );

    /**
     * If there is an entry in the dictionary whose key is the specified key,
     * removes it from the dictionary and returns its value;
     * otherwise, returns null.
     * @param key whose entry is to be removed from the map
     * @return previous value associated with key, 
     * or null if the dictionary does not an entry with that key
     */
    V remove( K key );                                

    /**
     * Returns an iterator of the entries in the dictionary.
     * @return  iterator of the entries in the dictionary
     */
    Iterator<Entry<K,V>> iterator( );  

} 


