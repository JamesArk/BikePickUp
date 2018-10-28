package dataStructures;

public class OrderedDoubleList<K extends Comparable<K>,V> implements OrderedDictionary<K,V> {



    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;


    /**
     *  Node at the head of the list.
     */
    protected DListNode<Entry<K,V>> head;

    /**
     * Node at the tail of the list.
     */
    protected DListNode<Entry<K,V>> tail;

    /**
     * Number of elements in the list.
     */
    protected int currentSize;

    /**
     * Constructor of an empty doubly linked list.
     * head and tail are initialized as null.
     * currentSize is initialized as 0.
     */
    public OrderedDoubleList( ) {
        head = null;
        tail = null;
        currentSize = 0;
    }

    @Override
    public Entry<K, V> minEntry() throws EmptyDictionaryException {
        if(this.isEmpty())
            throw new EmptyDictionaryException();
        return head.getElement();
    }

    @Override
    public Entry<K, V> maxEntry() throws EmptyDictionaryException {
        if(this.isEmpty())
            throw new EmptyDictionaryException();
        return tail.getElement();
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public V find(K key) {
        DListNode<Entry<K,V>> node = head;

        while ( node != null && key.compareTo(node.getElement().getKey()) > 0)
            node = node.getNext();

        if(node == null)
            return null;
        else if(node.getElement().getKey().equals(key))
            return node.getElement().getValue();

        return null;
    }

    @Override
    public V insert(K key, V value) {
    	Entry<K,V> entry = new EntryClass<>(key,value);
    	DListNode<Entry<K,V>> node = findNode(key);
    	V oldValue = null;
    	if(head == null || key.compareTo(head.getElement().getKey()) < 0)
    		addFirst(entry);
    	else if(node == null) 
    		addLast(entry);
    	else if(node.getElement().getKey().equals(key)) {
    		oldValue = node.getElement().getValue();
    		node.setElement(entry);
    	} else {
    		DListNode<Entry<K,V>> newNode = new DListNode<>(entry,node.getPrevious(),node);
    		node.getPrevious().setNext(newNode);
    		node.setPrevious(newNode);
    		currentSize++;
    	}
    	return oldValue;
    }

    @Override
    public V remove(K key) {
        DListNode<Entry<K,V>> node = findNode(key);
        if(node == null || !node.getElement().getKey().equals(key))
            return null;

        DListNode<Entry<K,V>> previousNode = node.getPrevious();
        DListNode<Entry<K,V>> nextNode = node.getNext();

        if(previousNode != null)
            previousNode.setNext(nextNode);
        else
            head = nextNode;

        if(nextNode != null)
            nextNode.setPrevious(previousNode);
        else
            tail = previousNode;
        currentSize--;

        return node.getElement().getValue();
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new DoublyLLIterator<>(head, tail);
    }

    private void addFirst(Entry<K, V> entry) {
        DListNode<Entry<K,V>> newNode = new DListNode<>(entry, null, head);
        if (this.isEmpty())
            tail = newNode;
        else
            head.setPrevious(newNode);
        head = newNode;
        currentSize++;
    }
    
    private void addLast( Entry<K,V> element )
    {
        DListNode<Entry<K,V>> newNode = new DListNode<>(element, tail, null);
        if ( this.isEmpty() )
            head = newNode;
        else
            tail.setNext(newNode);
        tail = newNode;
        currentSize++;
    }

    private DListNode<Entry<K,V>> findNode(K key){
        DListNode<Entry<K,V>> node = head;

        while (node != null && key.compareTo(node.getElement().getKey()) > 0)
            node = node.getNext();

        if(node == null)
            return null;

        return node;
    }
}
