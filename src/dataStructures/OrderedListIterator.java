package dataStructures;

public class OrderedListIterator<K,V> implements Iterator<Entry<K,V>> {

    private Iterator<Entry<K,V>> listIterator;

    private Dictionary<K,V>[] table;

    private int currentPosition;

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
        currentPosition = searchNextList(0);
        listIterator = table[currentPosition].iterator();
    }

    private int searchNextList(int pos){
        for(int i = pos; i < table.length; i++)
            if(!table[i].isEmpty())
                return i;
            return -1;
    }
}
