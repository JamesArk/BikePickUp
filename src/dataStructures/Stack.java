/**
 * Package dataStructures for use in AED Curricular Unit
 */
package dataStructures;

import java.io.Serializable;

/**
 * Stack Abstract Data Type 
 * Includes description of general methods for the Stack with the LIFO discipline.
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 * 
 */
public interface Stack<E> extends Serializable
{

    /**
     *  Returns true iff the stack contains no elements.
     * @return true iff the stack contains no elements, false otherwise
     */
    public boolean isEmpty( );

    /**
     *  Returns the number of elements in the stack.
     * @return number of elements in the stack
     */
    int size( );

    /**
     *  Returns the element at the top of the stack.
     * @return element at top of stack
     * @throws EmptyStackException when stack = 0
     */
    E top( ) throws EmptyStackException;

    /**
     *  Inserts the specified <code>element</code> onto the top of the stack.
     * @param element element to be inserted onto the stack
     */
    void push( E element );

    /**
     *  Removes and returns the element at the top of the stack.
     * @return element removed from top of stack
     * @throws EmptyStackException when stack = 0
     */
    E pop( ) throws EmptyStackException;

}

