package dataStructures;

/**
 * TwoWayIterator Abstract Data Type 
 * Includes description of general methods for two way iterator.
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 * 
 */
public interface TwoWayIterator<E> extends Iterator<E>
{

    /**
     * Returns true if previous would return an element 
     * rather than throwing an exception.
     * @return true iff the iteration has more elements in the reverse direction
     */
    boolean hasPrevious( );

    /**
     * Returns the previous element in the iteration.
     * @return previous element in the iteration
     * @throws NoSuchElementException - if call is made without verifying pre-condition
     */
    E previous( ) throws NoSuchElementException;

    /**
     * Restarts the iteration in the reverse direction.
     * After fullForward, if the iteration is not empty,
     * previous will return the last element in the iteration.
     */
    void fullForward( );

}
