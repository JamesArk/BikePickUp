package bikePickUp.dataStructures;                                          

/**
 * Queue List Implementation 
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 * 
 */
public class QueueInList<E> implements Queue<E>
{ 

	/**
	 * Serial Version UID of the Class
	 */
    static final long serialVersionUID = 0L;


    /**
     * Memory of the queue: a list.
     */
    protected List<E> list;                     

    /**
     * Constructor create an empty Doubly Linked List.
     */
    public QueueInList( )           
    {
        list = new DoublyLinkedList<E>();
    }


    @Override
    public boolean isEmpty( ) 
    {
        return list.isEmpty();
    }


    @Override
    public int size( )        
    {
        return list.size();
    }

                                             
    @Override
    public void enqueue( E element )
    {    
        list.addLast(element);
    }


    @Override
    public E dequeue( ) throws EmptyQueueException   
    {
        if ( list.isEmpty() )
            throw new EmptyQueueException();

        return list.removeFirst();
    }


}
