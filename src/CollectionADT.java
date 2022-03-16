public interface CollectionADT<T> extends Iterable<T> {
    public boolean add(T e);
    public boolean remove();
    public boolean isEmpty();
    public void empty();
    public int size();
    public void print();
}
