import java.util.Iterator;

public class EmployeesArrayQueue implements QueueADT<Employee> {
    private Employee[] arr = new Employee[10];
    private int size = 0;
    private int first = 0;

    // Task 5: Implement addAt function which inserts element at certain position in the queue. If the given position does not exist in the queue, the element should not
    // be added and the function should return false.
    private boolean addAt(Employee e, int index) {
        return false;
    }

    @Override
    public boolean add(Employee e) {
        enqueue(e);
        return true;
    }

    @Override
    public boolean remove() {
        return deque() != null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void empty() {

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void print() {

    }

    @Override
    public void enqueue(Employee e) {

    }

    @Override
    public Employee deque() {
        return null;
    }

    @Override
    public Employee first() {
        return null;
    }

    @Override
    public Employee last() {
        return null;
    }

    @Override
    public Iterator<Employee> iterator() {
        return null;
    }
}
