public class SalesPerson extends Employee {
    String[] customerAccounts;

    public SalesPerson(String name, String surname, int id) {
        super(name, surname, id);
        customerAccounts = new String[10];
    }

    @Override
    public void doWork() {

    }
}
