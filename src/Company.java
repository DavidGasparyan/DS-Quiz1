import java.util.*;

public class Company {
    public ListADT<Employee> employees;

    public Company(String type) {
        // TODO proper type handling should be done here and wrong type exception should be through in case if type is not one of "LL" or "AL"
        employees = new EmployeesLinkedList();
        if (Objects.equals(type, "Array List")) {
            employees = new EmployeesArrayList();
        } else if (Objects.equals(type, "Double Linked List")) {
            employees = new EmployeesDoubleLinkedList();
        }
    }

    private static int empID = 1;
    public enum WorkerType {
        SWEngineer, Accountant, SalesPerson
    }

    // We are using Factory design pattern here. Classic example would be to have getEmployee function which would create
    // and return the given type of employee, based on the input type parameter
    public void addEmployee(String name, WorkerType type) {
        // TODO handle the null type case
        if (type == WorkerType.SWEngineer) {
            employees.add(new SWEngineer(name, "yan", empID++));
        }
        if (type == WorkerType.Accountant) {
            employees.add(new Accountant(name, "yan", empID++));
        }
        if (type == WorkerType.SalesPerson) {
            employees.add(new SalesPerson(name, "yan", empID++));
        }
    }

    // Task 4: Implement recursive function which receives a list of employees and removes all Accountants from the list
    // NOTE: No helper functions should be used for the implementation. Any list function used should be implemented.
    public static void removeAccountantsRecursive(ListADT<Employee> employees) {
        EmployeesLinkedList temp = new EmployeesLinkedList();

        if (employees.first() == null) {
            return;
        }

        if (employees.first() instanceof Accountant) {
            employees.removeFirst();
            removeAccountantsRecursive(employees);
        } else {
            Employee first = employees.first();
            temp.addFirst(first);

            employees.removeFirst();
            removeAccountantsRecursive(employees);

            employees.addFirst(first);
        }
    }
    private static void testTask1(String listType) {
        Company cmp = new Company(listType);
        cmp.employees.add(new SWEngineer("John", "Dias", 1));
        cmp.employees.add(new SWEngineer("Anne", "Frank", 2));
        cmp.employees.add(new SWEngineer("Paruyr", "Sevak", 2));

//        cmp.employees.print();

        System.out.println("\t - Running tests for removeAfter function");
        int s = cmp.employees.size();
        Boolean result = cmp.employees.removeBefore(new Accountant("Sergio", "Woods", 1000));

        System.out.println("\t\t Trying to run function on element not in list");
        if (result == false && cmp.employees.size() == s) {
            System.out.println("\t\t PASS: function did not remove anything");
        } else {
            System.out.println("\t\t FAIL: either wrong element was removed or false positive is returned");
        }

//        cmp.employees.print();

        System.out.println("\t\t Trying to remove element before first");
        s = cmp.employees.size();
        result = cmp.employees.removeBefore(cmp.employees.first());

        if (result == false && cmp.employees.size() == s) {
            System.out.println("\t\t PASS: function did not remove element before first");
        } else {
            System.out.println("\t\t FAIL: either wrong element was removed or false positive is returned");
        }

//        cmp.employees.print();

        System.out.println("\t\t Trying to remove element before last");
        s = cmp.employees.size();
        result = cmp.employees.removeBefore(cmp.employees.last());
        if (result == true && cmp.employees.size() == s - 1) {
            System.out.println("\t\t PASS: element before last was removed");
        } else {
            System.out.println("\t\t FAIL: either false negative is returned or the size of list remained the same");
        }

//        cmp.employees.print();

        System.out.println("\t\t Trying to remove element before last when there are 2 elements in list");
        result = cmp.employees.removeBefore(cmp.employees.last());
        if (result == true && cmp.employees.first() == cmp.employees.last()) {
            System.out.println("\t\t PASS: element before last was removed and now last and first are the same");
        } else {
            System.out.println("\t\t FAIL: either false negative is returned or remove was not performed");
        }
//        cmp.employees.print();
    }
    
    private static void testTask2(String listType) {
        Company cmp = new Company(listType);
        cmp.addEmployee("John", WorkerType.SWEngineer );
        cmp.addEmployee("Anna", WorkerType.Accountant );

        System.out.println("\t - Running tests for addAt function");


        int s = cmp.employees.size();
        Boolean result = cmp.employees.addAt(new Accountant("Sergio", "Woods", 1000), -1);
        System.out.println("\t\t Trying to add element at negative position");
        if (result == false && cmp.employees.size() == s) {
            System.out.println("\t\t PASS: element is not added at negative position");
        } else {
            System.out.println("\t\t FAIL: element can not be added at negative position");
        }

        result = cmp.employees.addAt(new Accountant("Sergio", "Woods", 1000), cmp.employees.size()+10);
        System.out.println("\t\t Trying to add element at position out of list boundaries");
        if (result == false && cmp.employees.size() == s) {
            System.out.println("\t\t PASS: element is not added at non valid position");
        } else {
            System.out.println("\t\t FAIL: element can not be added at non valid position");
        }

        s = cmp.employees.size();
        Employee e = new Accountant("Bob", "Marley", 33);
        result = cmp.employees.addAt(e, 0);
        System.out.println("\t\t Trying to add element at position 0");
        if (result == true && cmp.employees.size() == s + 1 && cmp.employees.first() == e) {
            System.out.println("\t\t PASS: element is added at given position");
        } else {
            System.out.println("\t\t FAIL: element is not added at given position");
        }

        s = cmp.employees.size();
        result = cmp.employees.addAt(new SalesPerson("Jim", "Woods", 44), s - 1);
        System.out.println("\t\t Trying to add element at last position");
        if (result == true && cmp.employees.size() == s + 1) {
            System.out.println("\t\t PASS: element is added at given position");
        } else {
            System.out.println("\t\t FAIL: element is not added at given position");
        }
        // TODO using getElementAt function add more test to check the functionality
    }
    
    private static void testTask4(String listType) {
        Company cmp = new Company(listType);
        cmp.addEmployee("John", WorkerType.SWEngineer );
        cmp.addEmployee("Anna", WorkerType.Accountant );
        cmp.addEmployee("Karen", WorkerType.Accountant );
        cmp.addEmployee("Armen", WorkerType.SalesPerson );
        cmp.addEmployee("Ani", WorkerType.SWEngineer );
        cmp.addEmployee("Bob", WorkerType.SalesPerson );
        cmp.addEmployee("Meri", WorkerType.SWEngineer );
        cmp.addEmployee("Jane", WorkerType.Accountant );

        System.out.println("\t - Running tests for removeAccountantsRecorsive function");
        System.out.println("\t\t Create List of 8 employees with 3 accountants");

        removeAccountantsRecursive(cmp.employees);
        if (cmp.employees.size() == 5) {
            System.out.println("\t\t PASS: basic test to check the list size is 5 after remove call");
        } else {
            System.out.println("\t\t FAIL: basic test to check the list size is 5 after remove call");
        }

        // TODO: Add generic check to confirm that number of non account employees matches the size of the list after function call
        // TODO: Go over the elements of the list and print FAIL if there is at least one accountant
    }

    private static void testTasks_1_2_4(String listType) {
        System.out.println("Running task 1, 2, 4 tests for " + listType);

        testTask1(listType);
        testTask2(listType);
        testTask4(listType);

        System.out.println();
    }

    private static void testLinkedListReverseIterator() {
        System.out.println("Running task 3 (reverse iterator) tests for LinkedList ");

        Company cmp = new Company("Linked List");
        cmp.addEmployee("John", WorkerType.SWEngineer );
        cmp.addEmployee("Anna", WorkerType.Accountant );
        cmp.addEmployee("Karen", WorkerType.Accountant );
        cmp.addEmployee("Armen", WorkerType.SalesPerson );
        cmp.addEmployee("Ani", WorkerType.SWEngineer );
        cmp.addEmployee("Bob", WorkerType.SalesPerson );
        cmp.addEmployee("Meri", WorkerType.SWEngineer );
        cmp.addEmployee("Jane", WorkerType.Accountant );
        Iterator<Employee> it = ((EmployeesLinkedList) cmp.employees).reverseIterator();
        if (it == null) {
            System.out.println("\t\t FAIL: list has no reverse iterator");
            return;
        }
        // Confirm that the first element returned by iterator is the last element of the array
        System.out.println("\t\t Checking the value of first element returned by reverse iterator");

        if (it.hasNext() && it.next().equals(cmp.employees.last())) {
            System.out.println("\t\t PASS: reverse iterator begins from last element");
        } else {
            System.out.println("\t\t FAIL: reverse iterator should begin from last element");
        }

        Employee lastIterated = cmp.employees.last();
        while (it.hasNext()) {
            lastIterated = it.next();
        }
        // Confirm that the last element returned by iterator is the first element of the array
        System.out.println("\t\t Checking the value of last element returned by reverse iterator");
        if (lastIterated.equals(cmp.employees.first())) {
            System.out.println("\t\t PASS: reverse iterator ends at first element");
        } else {
            System.out.println("\t\t FAIL: reverse iterator should end at first element");
        }
    }

    public static void main(String[] str) throws Exception {
//        testTasks_1_2_4("Array List");
        testTasks_1_2_4("Linked List");
        testTasks_1_2_4("Double Linked List");
        testLinkedListReverseIterator();
    }
}
