public interface QueueADT<T> extends CollectionADT<T> {
    public void enqueue(T e); // pushBack()
    public T deque(); // popFront()
    public T first();
    public T last();
}
