public interface ListADT<T> extends CollectionADT<T> {
    public boolean addFirst(T e);
    public boolean addLast(T e);
    public boolean removeFirst();
    public boolean removeLast();
    public T first();
    public T last();
    public boolean removeBefore(T e);
    public boolean addAt(T e, int index);
}
