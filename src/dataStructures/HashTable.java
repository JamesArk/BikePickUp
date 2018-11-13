package dataStructures;  

/**
 * Hash table implementation
 * @author AED  Team
 * @version 1.0
 * @param <K> Generic Key
 * @param <V> Generic Value 
 */

public abstract class HashTable<K,V> implements Dictionary<K,V>
{ 
	/**
	 * Serial Version UID of the Class.
	 */
    static final long serialVersionUID = 0L;

	/**
	 * Default size of the hash table.
	 */
    public static final int DEFAULT_CAPACITY = 50;

	/**
	 * Number of entries in the hash table.
	 */
    protected int currentSize;

	/**
	 * Maximum number of entries.
	 */
    protected int maxSize;
    
    
    //
    // Public Static Methods
    //
    
    
    /**
     * Returns the hash code of the specified key,
     * which is an integer in the range 0, ..., b-1.
     * @param key to be encoded
     * @return hash code of the specified key
     */
    public static int hash( String key )
    {
        int a = 127;          // a is a prime number.
        int b = 2147483647;   // b is a prime number.
        int hashCode = 0;

        for ( int i = 0; i < key.length(); i++ )
            hashCode = ( hashCode * a + key.charAt(i) ) % b;
        return hashCode;
    }


    //
    // Protected Static Methods
    //


    /**
     * Returns a prime number that is not less than the specified number;
     * or zero if all such primes are greater than Integer.MAX_VALUE.
     * @param number
     * @return next prime number not less than <code>number</code>,
     * or zero if all such primes are greater than Integer.MAX_VALUE
     */
    protected static int nextPrime( int number )
    {
        for ( int i = 0; i < PRIMES.length; i++ )
            if ( PRIMES[i] >= number )
                return PRIMES[i];
        return 0;
    }


	/**
	 * Prime numbers between 11 and Integer.MAX_VALUE.
	 */
    protected static final int[] PRIMES = 
    {
        11, 19, 31, 47, 73,
        113, 181, 277, 421, 643, 967,
        1451, 2179, 3299, 4951, 7433,
        11173, 16763, 25163, 37747, 56671, 85009,
        127529, 191299, 287003, 430517, 645787, 968689,
        1453043, 2179571, 3269377, 4904077, 7356119, 
        11034223, 16551361, 24827059, 37240597, 55860923, 83791441,
        125687173, 188530777, 282796177, 424194271, 636291413, 954437161,
        1431655751, 2147483647
    };


    //
    // Public Instance Methods
    //


    @Override
    public boolean isEmpty( )
    {    
        return currentSize == 0; 
    }

    @Override
    public int size( )
    {    
        return currentSize; 
    }

    @Override
    public abstract V find( K key );

    @Override
    public abstract V insert( K key, V value );

    @Override
    public abstract V remove( K key );

    @Override
    public abstract Iterator<dataStructures.Entry<K,V>> iterator( );


    //
    // Protected Instance Methods
    //

    
    /**
     * Returns true iff the hash table cannot contain more entries.
     * @return true if the hash table cannot contain more entries.
     */
    protected boolean isFull( )
    {    
        return currentSize == maxSize;
    }
}




























