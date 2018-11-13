package dataStructures;

/**
 * Ordered Dictionary interface
 *
 * @author AED team
 * @version 1.0
 *
 * @param <K> Generic type Key, must extend comparable
 * @param <V> Generic type Value
 */
public interface OrderedDictionary<K extends Comparable<K>, V>
        extends Dictionary<K,V>
{

    /**
     * Returns the entry with the smallest key in the dictionary.
     *
     * @return
     * @throws EmptyDictionaryException
     */
    Entry<K,V> minEntry( ) throws EmptyDictionaryException;

    /**
     * Returns the entry with the largest key in the dictionary.
     *
     * @return
     * @throws EmptyDictionaryException
     */
    Entry<K,V> maxEntry( ) throws EmptyDictionaryException;

    /* (non-Javadoc)
     * Returns an iterator of the entries in the dictionary
     * which preserves the key order relation.
     *
     * @see dataStructures.Dictionary#iterator()
     */
    // Iterator<Entry<K,V>> iterator( );

}

