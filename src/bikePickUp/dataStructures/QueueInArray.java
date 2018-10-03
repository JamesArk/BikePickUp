package bikePickUp.dataStructures;

/**
 * Queue Array Implementation 
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 * 
 */
public class QueueInArray<E> implements Queue<E>
{                                     

	/**
	 * Serial Version UID of the Class
	 */
    static final long serialVersionUID = 0L;


    /**
     *  Default capacity of the queue.     
     */
    public static final int DEFAULT_CAPACITY = 1000;     

    /**
     * Memory of the queue: a circular array.
     */
    protected E[] array; 
                                              
    /**
     * Index of the element at the front of the queue.         
     */
    protected int front;                                       
                                              
    /**
     * Index of the element at the rear of the queue.          
     */
    protected int rear;
                                              
    /**
     * Number of elements in the queue.
     */
    protected int currentSize;

    /**
     * Creates a Queue on an array with capacity <code> capacity</code>
     * @param capacity - maximum capacity of the stack
     */
    public QueueInArray( int capacity )
    {
        // Compiler gives a warning.
        array = (E[]) new Object[capacity];
        front = 0;                    
        rear = capacity - 1;      
        currentSize = 0;              
    }

    /**
     * Creates a Queue on an array with capacity DEFAULT_CAPACITY
     */
    public QueueInArray( )          
    {
        this(DEFAULT_CAPACITY);
    }


    @Override
    public boolean isEmpty( ) 
    {    
        return currentSize == 0;
    }

                                             
    /**
     *  Returns true iff the queue cannot contain more elements.
     * @return true iff the queue is full, false otherwise
     */
   public boolean isFull( )  
    {
        return currentSize == array.length;
    }


    @Override
    public int size( )        
    {
        return currentSize;
    }

                                             
    /**
     *  Increments with "wrap around".   
     * @param index - current index
     * @return next index
     */
    protected int nextIndex( int index )
    {
        return ( index + 1 ) % array.length;
    }


    /**
     * Inserts the specified element at the rear of the queue.
     * @param element - element to be added to the end of the queue
     * @throws FullQueueException when queue is full
     */
    public void enqueue( E element ) throws FullQueueException    
    {
        if ( this.isFull() )
            throw new FullQueueException();

        rear = this.nextIndex(rear);       
        array[rear] = element;     
        currentSize++;          
    }


    @Override
    public E dequeue( ) throws EmptyQueueException   
    {
        if ( this.isEmpty() )
            throw new EmptyQueueException();

        E element = array[front];
        array[front] = null;    // For garbage collection.
        front = this.nextIndex(front);     
        currentSize--;          
        return element;            
    }

}
