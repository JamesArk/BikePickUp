package dataStructures;  

/**
 * Chained Hash table implementation
 * @author AED  Team
 * @version 1.0
 * @param <K> Generic Key, must extend comparable
 * @param <V> Generic Value 
 */

public class ChainedHashTable<K extends Comparable<K>, V> 
    extends HashTable<K,V>
{ 
	/**
	 * Serial Version UID of the Class.
	 */
    static final long serialVersionUID = 0L;

	/**
	 * The array of dictionaries.
	 */
    protected Dictionary<K,V>[] table;


    /**
     * Constructor of an empty chained hash table,
     * with the specified initial capacity.
     * Each position of the array is initialized to a new ordered list
     * maxSize is initialized to the capacity.
     * @param capacity defines the table capacity.
     */
    @SuppressWarnings("unchecked")
    public ChainedHashTable( int capacity )
    {
        int arraySize = nextPrime((int) (1.1 * capacity));
        // Compiler gives a warning.
        table = (Dictionary<K,V>[]) new Dictionary[arraySize];
        for ( int i = 0; i < arraySize; i++ )
            table[i] = new OrderedDoubleList<>();

        maxSize = capacity;
        this.currentSize = 0;
    }                                      


    public ChainedHashTable( )
    {
        this(DEFAULT_CAPACITY);
    }                                                                

    /**
     * Returns the hash value of the specified key.
     * @param key to be encoded
     * @return hash value of the specified key
     */
    protected int hash( K key )
    {
        return Math.abs( key.hashCode() ) % table.length;
    }

    @Override
    public V find( K key )
    {
        return table[ this.hash(key) ].find(key);
    }

    @Override
    public V insert( K key, V value ) {
        if ( this.isFull() )
             this.rehash();
        currentSize++;
        return table[hash(key)].insert(key,value);
    }

    @Override
    public V remove( K key ) {
        Dictionary<K,V> dictionary = table[hash(key)];
        if(!dictionary.isEmpty()) {
            currentSize--;
            return dictionary.remove(key);
        }
        return null;
    }

    @Override
    public Iterator<Entry<K,V>> iterator( ) {
        return new OrderedListIterator<>(table);
    }


    /**
     * Creates a new table with 1.5x its size and reinserts every element the old table had.
     */
    @SuppressWarnings("unchecked")
    private void rehash() {
        maxSize += maxSize/2;
        int arraySize = nextPrime((int) (1.1 * maxSize));
        Dictionary<K,V>[] newTable = (Dictionary<K,V>[]) new Dictionary[arraySize];
        for ( int i = 0; i < arraySize; i++ )
            newTable[i] = new OrderedDoubleList<>();
        Iterator<Entry<K,V>> iterator = iterator();
        table = newTable;
        while(iterator.hasNext()) {
            Entry<K,V> entry = iterator.next();
            table[hash(entry.getKey())].insert(entry.getKey(),entry.getValue());
        }
    }
}