package dataStructures;

public class OrderedListIterator<K,V> implements Iterator<Entry<K,V>> {

    /**
	 * Constant for serialization.
	 */
	private static final long serialVersionUID = 1L;

    /**
     * Current list iterator to be used.
     */
	private Iterator<Entry<K,V>> listIterator;

    /**
     * The whole table to be iterated
     */
    private Dictionary<K,V>[] table;

    /**
     * The current position in the table.
     */
    private int currentPosition;

    /**
     *
     * @param table table to be iterated
     */
    protected OrderedListIterator(Dictionary<K, V>[] table) {
        this.table = table;
        rewind();
    }

    @Override
    public boolean hasNext() {
        return listIterator.hasNext() || searchNextList(currentPosition) != -1;
    }

    @Override
    public Entry<K,V> next() throws NoSuchElementException {
        if(!listIterator.hasNext()) {
            currentPosition = searchNextList(currentPosition);
            listIterator = table[currentPosition].iterator();
        }
        return listIterator.next();
    }

    @Override
    public void rewind() {
        currentPosition = searchNextList(-1);
        listIterator = table[currentPosition].iterator();
    }

    /**
     * Finds the next list to be iterated and returns its position
     * @param pos current position
     * @return next position
     */
    private int searchNextList(int pos){
        for(int i = pos+1; i < table.length; i++)
            if(!table[i].isEmpty())
                return i;
            return -1;
    }
}
