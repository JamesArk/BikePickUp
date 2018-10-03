package bikePickUp.dataStructures;

import java.io.Serializable;

/**
 * Queue Abstract Data Type 
 * Includes description of general methods for Queue with FIFO discipline.
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 * 
 */

public interface Queue<E> extends Serializable
{

    /**
     * Returns true iff the queue contains no elements.
     * @return true iff the queue contains no elements, false otherwise
     */
    boolean isEmpty( );

    /**
     *  Returns the number of elements in the queue.
     * @return number of elements in the queue
     */
    int size( );

    /**
     * Inserts the specified element at the rear of the queue.
     * @param element - element to be added to the end of the queue
     */
    void enqueue( E element );

    /**
     * Removes and returns the element at the front of the queue.             
     * @return element removed from the front of the queu
     * @throws EmptyQueueException - if size() = 0
     */
    E dequeue( ) throws EmptyQueueException;
}                                                                       
