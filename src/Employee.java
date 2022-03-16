public abstract class Employee {
    private String name;
    private String surname;
    private int id;
    public Employee(String name, String surname, int id) {
        this.name = name;
        this.surname = surname;
        this.id = id;
    }
    public abstract void doWork();
}
