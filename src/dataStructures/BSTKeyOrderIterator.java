package dataStructures;

public class BSTKeyOrderIterator<K extends Comparable<K>,V> implements Iterator<Entry<K,V>> {

    private Stack<BSTNode<K,V>> stack;

    private BSTNode<K,V> root;

    protected BSTKeyOrderIterator(BSTNode<K,V> root) {
        this.root = root;
        stack = new StackInList<>();
        rewind();
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Entry<K, V> next() throws NoSuchElementException {
        if(!hasNext())
            throw new NoSuchElementException();
        BSTNode<K,V> node = stack.pop();
        if(node.getRight() == null)
            return node.getEntry();
        stackSubTree(node.getRight());
        return node.getEntry();
    }

    private void stackSubTree(BSTNode<K, V> subRoot) {
        BSTNode<K,V> node = subRoot;
        while(node != null){
            stack.push(node);
            node = node.getLeft();
        }
    }

    @Override
    public void rewind() {
        BSTNode<K,V> node = root;
        stackSubTree(root);
    }
}
