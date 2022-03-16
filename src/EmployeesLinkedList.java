import java.util.Iterator;

public class EmployeesLinkedList implements ListADT<Employee> {
    private class Node<Employee> {
        Employee data;
        Node<Employee> next;

        public Node(Employee e) {
            data = e;
            next = null;
        }

        public Node(Employee e, Node<Employee> next) {
            this.data = e;
            this.next = next;
        }
    }

    private Node<Employee> first;
    private Node<Employee> last;
    private int size;

    public EmployeesLinkedList() {
        first = last = null;
        size = 0;
    }

    // Task 1: The function should remove the employee occurring before the given employee in the list and return true if done.
    public boolean removeBefore(Employee e) {
        int start = 0;
        Node<Employee> pointer = first;

        while(pointer != null) {
            if(pointer.data == e && start >= 1) {
                remove( start - 1);
                return true;
            }

            start ++;
            pointer = pointer.next;
        }

        return false;
    }

    public void remove(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            removeFirst();
            return;
        }

        Node<Employee> beforeNode = first;
        Node<Employee>  currentNode = first.next;

        while (index > 1) {
            beforeNode = currentNode;
            currentNode = currentNode.next;
            index--;
        }

        beforeNode.next = currentNode.next;
        size--;
    }

    // Task 2: The function should add the given employee under the given index, if the index is within the list boundaries and return true if added.
    public boolean addAt(Employee e, int index) {
        if (index < 0 || index > size) {
            return false;
        }

        if (index == 0) {
            addFirst(e);
            return true;
        }

        Node<Employee> previousNode = null;
        Node<Employee> nextNode = first;

        while (index > 0) {
            previousNode = nextNode;
            nextNode = nextNode.next;
            index--;
        }

        size ++;

        previousNode.next = new Node<Employee>(e, nextNode);
        return true;
    }

    // Task 3: Implement reverse iterator which starts the iteration from last element and goes up to the first element in the list
    public Iterator<Employee> reverseIterator() {
        return new ReverseIterator();
    }

    @Override
    public boolean add(Employee e) {
        return addLast(e);
    }

    @Override
    public boolean remove() {
        return false;
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
        Node<Employee> pointer = first;

        while(pointer != null) {
            System.out.println(pointer);
            pointer = pointer.next;
        }
    }

    @Override
    public boolean addFirst(Employee e) {
        Node<Employee> tempNode = new Node<>(e, first);

        if (first == null) {
            first = last = tempNode;
        } else {
            first = tempNode;
        }

        size ++;
        return true;
    }

    @Override
    public boolean addLast(Employee e) {
        if (size == 0) {
            first = last = new Node<Employee>(e);
        } else {
            last.next = new Node<Employee>(e);
            last = last.next;
        }
        size++;
        return true;
    }

    @Override
    public boolean removeFirst() {
        // If the list is empty just return null;
        if (first == null) {
            return false;
        }

        final Node<Employee> next = first.next;

        first = next;

        if (next == null) {
            last = null;
        }

        size--;

        return true;
    }

    @Override
    public boolean removeLast() {
        return false;
    }

    @Override
    public Employee first() {
        if (isEmpty()) {
            return null;
        }
        return first.data;
    }

    @Override
    public Employee last() {
        if (isEmpty()) {
            return null;
        }
        return last.data;
    }

    @Override
    public Iterator<Employee> iterator() {
        return null;
    }

    private class ReverseIterator implements Iterator<Employee> {
        int remainder = size;

        @Override
        public boolean hasNext() {
            return remainder > 0;
        }

        @Override
        public Employee next() {
            assert hasNext();
            int lastIndex = remainder;
            Node<Employee> pointer = first;

            while (lastIndex > 1) {
                lastIndex --;
                pointer = pointer.next;
            }

            remainder --;

            return pointer.data;
        }
    }


}
